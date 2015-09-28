package com.dj.cooking.monitoring;

import com.dj.cooking.jerseytestutils.StandaloneJsonControllerTest;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class HealthCheckControllerTest extends StandaloneJsonControllerTest {

    @Override
    public Object getController() {
        return new HealthCheckController();
    }

    @Test
    public void testHealthCheck() {
        final Invocation.Builder healthCheckRequest = target("healthcheck").request();
        final Response response = healthCheckRequest.get();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
        assertThat(healthCheckRequest.get(HealthCheckController.HealthCheckResponse.class)).isEqualTo(new HealthCheckController.HealthCheckResponse(true));

    }
}