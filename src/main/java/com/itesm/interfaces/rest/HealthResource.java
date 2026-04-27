package com.itesm.interfaces.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.Instant;
import java.util.Map;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * HealthResource
 */
@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {
    @ConfigProperty(name = "app.name", defaultValue = "todo-list") String appName;

    @ConfigProperty(name = "app.version", defaultValue = "0.0.3") String appVersion;

    @GET
    public Response status() {
        return Response
            .ok(Map.of("status", "UP", "name", appName, "version", appVersion, "timestamp",
                Instant.now().toString()))
            .build();
    }
}
