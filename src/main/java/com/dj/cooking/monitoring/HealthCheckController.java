package com.dj.cooking.monitoring;


import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/healthcheck")
public class HealthCheckController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HealthCheckResponse getTestMsg() {
        return new HealthCheckResponse(true);
    }

    public static class HealthCheckResponse {
        private final boolean isHealthy;

        @JsonCreator
        public HealthCheckResponse(@JsonProperty("healthy") boolean isHealthy) {
            this.isHealthy = isHealthy;
        }

        public boolean isHealthy() {
            return isHealthy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            HealthCheckResponse that = (HealthCheckResponse) o;

            return isHealthy == that.isHealthy;

        }

        @Override
        public int hashCode() {
            return (isHealthy ? 1 : 0);
        }
    }
}