package ${package}.${artifactId};

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
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

    // Dropwizard Metrics
    public static final MetricRegistry METRIC_REGISTRY = new MetricRegistry();

    // Base URI parameters the Grizzly HTTP server will listen on
    private static String host;
    private static String port;

    public static String getBaseUri()
    {
        return String.format("http://%s:%s/", host, port);
    }

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.test package
        final Application app = new Application("${package}.${artifactId}", getHost(), getPort());

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(getBaseUri()), app);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        host = System.getProperty("host.ip", "0.0.0.0");
        port = System.getProperty("host.port", "8080");

        final HttpServer server = startServer();

        final JmxReporter reporter = JmxReporter.forRegistry(METRIC_REGISTRY).build();
        reporter.start();

        CLStaticHttpHandler staticHttpHandler = new CLStaticHttpHandler(Main.class.getClassLoader(), "swagger-ui/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/docs");

        logger.info("${package}.${artifactId} starting on port 8080... Ctrl-C to cancel");

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        Thread.currentThread();
    }
}

