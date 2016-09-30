package app;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    public ApplicationConfig() {
        SwaggerConfig.initialize();
    }

    @Override
    public final Set<Class<?>> getClasses() {
        return SwaggerConfig.getClasses();
    }
}
