package org.koweg.demo.rest;

import java.util.Set;

import javax.ws.rs.core.Configurable;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class BaseJerseyApplicationConfig extends ResourceConfig {

    private boolean initialised;

    public BaseJerseyApplicationConfig() {
        initialise();
    }

    private void initialise() {
        if (!initialised) {
            initialise(this);
            initialised = true;
        }
    }

    protected void initialise(final Configurable<?> config) {
        // config.register(JacksonConfig.class);
        // config.register(JaxbConfig.class);
        
        config.register(ValidationConfigurationContextResolver.class);

        registerResources(config);

        // Validation.
        config.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        config.property(ServerProperties.WADL_FEATURE_DISABLE, true); // Disable WADL

        // Autodiscovery
//        config.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
    }

    protected void registerResources(final Configurable<?> config) {
    }

}
