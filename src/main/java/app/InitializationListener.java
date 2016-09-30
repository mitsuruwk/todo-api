package app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitializationListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
        this.logger.debug("contextInitialized");

        System.getenv().entrySet().stream().forEach(e -> {
            this.logger.debug(e.getKey() + ":" + e.getValue());
        });
    }

    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {
        this.logger.debug("contextDestroyed");
    }
}
