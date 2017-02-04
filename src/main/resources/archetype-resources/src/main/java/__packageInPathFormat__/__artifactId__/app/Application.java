package ${package}.${artifactId}.app;

import ${package}.${artifactId}.providers.GsonProvider;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application(String myPackage) {
        super();

        String myPackages = String.format("%s, io.swagger.resources", myPackage);

        packages(myPackage);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage(myPackages);
        beanConfig.setScan(true);

        registerClasses(ApiListingResource.class);
        registerClasses(SwaggerSerializers.class);
        registerClasses(GsonProvider.class);
    }
}