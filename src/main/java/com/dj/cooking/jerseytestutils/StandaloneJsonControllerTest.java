package com.dj.cooking.jerseytestutils;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public abstract class StandaloneJsonControllerTest extends JerseyTest {
    public abstract Object  getController();

    @Override
    protected Application configure() {
        if(getController() == null) {
            throw new ImproperTestSetupException("You must override getController and return your controller instance");
        }

        final HashSet<Object> singletons = new HashSet<Object>();
        singletons.add(getController());

        final Application application = new Application() {
            @Override
            public Set<Object> getSingletons() {
                return singletons;
            }
        };
        final ResourceConfig resourceConfig = ResourceConfig.forApplication(application);
        resourceConfig.register(JacksonFeature.class);
        return resourceConfig;
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(JacksonFeature.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
        jacksonProvider.setMapper(mapper);

        config.register(jacksonProvider);
    }


    public static class ImproperTestSetupException extends RuntimeException {
        public ImproperTestSetupException(String message) {
            super(message);
        }
    }
}
