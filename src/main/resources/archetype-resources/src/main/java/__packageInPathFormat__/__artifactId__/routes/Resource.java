package ${package}.${artifactId}.routes;

import io.swagger.annotations.*;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.${artifactId}.pojos.*;

/**
 * Root resource (exposed at "resource" path)
 */
@Path("resource")
@Api(value = "/resource", description = "Resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(Resource.class);

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return ExampleResponse that will be returned as a application/json response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "A test operation", notes = "More notes about this method", response = ExampleResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got it"),
            @ApiResponse(code = 500, message = "Server is down!")
    })
    public ExampleResponse get() {
        return new ExampleResponse();
    }

    /**
     * Method handling HTTP POST requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return ExampleResponse that will be returned as a application/json response.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "A test operation", notes = "More notes about this method", response = ExampleResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got it"),
            @ApiResponse(code = 500, message = "Server is down!")
    })
    public ExampleResponse post(ExampleRequest request) {
        logger.info("Server receieved: "+new Gson().toJson(request));

        return new ExampleResponse();
    }
}
