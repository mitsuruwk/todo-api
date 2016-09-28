package app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Path;

import org.reflections.Reflections;

import io.swagger.jaxrs.config.BeanConfig;
import resource.TodoResource;

public final class SwaggerConfig {

    private static final String RESOURCE_PACKAGE = TodoResource.class.getPackage().getName();

    public static void initialize() {
        // Swagger の設定
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Todo API");
        beanConfig.setDescription("A simple JAX-RS project.");
        beanConfig.setVersion("0.0.1");
//        beanConfig.setSchemes(new String[] { "http" });
//        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage(RESOURCE_PACKAGE);
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }

    public static Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        // JAX-RS のリソースクラスの登録
        new Reflections(RESOURCE_PACKAGE).getTypesAnnotatedWith(Path.class).forEach(resources::add);

        // Swagger の有効化
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return resources;
    }

    private SwaggerConfig() {
    }
}
