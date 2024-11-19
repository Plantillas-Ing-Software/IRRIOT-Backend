package com.irriot.platform.u202213358.observability.Interfaces.rest;

import com.irriot.platform.u202213358.observability.Domain.Services.ThingStateCommandService;
import com.irriot.platform.u202213358.observability.Interfaces.rest.resources.CreateThingStateResource;
import com.irriot.platform.u202213358.observability.Interfaces.rest.resources.ThingStateResource;
import com.irriot.platform.u202213358.observability.Interfaces.rest.transform.CreateThingStateCommandFromResourceAssembler;
import com.irriot.platform.u202213358.observability.Interfaces.rest.transform.ThingStateResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing ThingState resources.
 * <p>
 * This controller provides endpoints for creating and managing ThingState objects. It exposes RESTful API
 * endpoints to allow clients to interact with the ThingState entities, such as creating a new ThingState.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/thing-states", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "ThingStates", description = "Thing State Management Endpoints")
public class ThingStateController {

    private final ThingStateCommandService thingStateCommandService;

    public ThingStateController(ThingStateCommandService thingStateCommandService) {
        this.thingStateCommandService = thingStateCommandService;
    }

    /**
     * Creates a new ThingState using the provided data.
     *
     * @param resource The resource containing the ThingState data to be created
     * @return A ResponseEntity containing the ThingStateResource if successful, or a 400 Bad Request if not
     */
    @Operation(summary = "Create a Thing State", description = "Creates a Thing State with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Thing State created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<ThingStateResource> createThingState(@RequestBody CreateThingStateResource resource) {
        var createThingStateCommand = CreateThingStateCommandFromResourceAssembler.toCommandFromResource(resource);
        var thingState = thingStateCommandService.handle(createThingStateCommand);

        if (thingState.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var thingStateResource = ThingStateResourceFromEntityAssembler.toResourceFromEntity(thingState.get());
        return new ResponseEntity<>(thingStateResource, HttpStatus.CREATED);
    }
}

