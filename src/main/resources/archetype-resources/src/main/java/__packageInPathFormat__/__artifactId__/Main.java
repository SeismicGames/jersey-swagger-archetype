package ${package}.${artifactId};

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;

import ${package}.${artifactId}.app.Application;

public class Main {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.test package
        final Application app = new Application("${package}.${artifactId}");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), app);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        CLStaticHttpHandler staticHttpHandler = new CLStaticHttpHandler(Main.class.getClassLoader(), "swagger-ui/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/docs");

        logger.info("${package}.${artifactId} starting on port 8080... Ctrl-C to cancel");

        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                server.shutdown();
            }
        });

        Thread.currentThread();
    }
}

