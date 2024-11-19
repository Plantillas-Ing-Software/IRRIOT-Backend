package com.irriot.platform.u202213358.inventory.Interfaces.rest;

import com.irriot.platform.u202213358.inventory.Domain.Model.Queries.GetThingByIdQuery;
import com.irriot.platform.u202213358.inventory.Domain.Services.ThingCommandService;
import com.irriot.platform.u202213358.inventory.Domain.Services.ThingQueryService;
import com.irriot.platform.u202213358.inventory.Interfaces.rest.resources.CreateThingResource;
import com.irriot.platform.u202213358.inventory.Interfaces.rest.resources.ThingResource;
import com.irriot.platform.u202213358.inventory.Interfaces.rest.transform.CreateThingCommandFromResourceAssembler;
import com.irriot.platform.u202213358.inventory.Interfaces.rest.transform.ThingResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Things.
 */
@RestController
@RequestMapping(value = "/api/v1/things", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Things", description = "Thing Management Endpoints")
public class ThingController {

    private final ThingCommandService thingCommandService;
    private final ThingQueryService thingQueryService;

    public ThingController(ThingCommandService thingCommandService, ThingQueryService thingQueryService) {
        this.thingCommandService = thingCommandService;
        this.thingQueryService = thingQueryService;
    }

    /**
     * Creates a new Thing using the provided data.
     *
     * @param resource The resource containing the Thing data to be created
     * @return A ResponseEntity containing the ThingResource if successful, or a 400 Bad Request if not
     */
    @Operation(summary = "Create a Thing", description = "Creates a Thing with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Thing created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<ThingResource> createThing(@RequestBody CreateThingResource resource) {
        var createThingCommand = CreateThingCommandFromResourceAssembler.toCommandFromResource(resource);
        var thing = thingCommandService.handle(createThingCommand);

        if (thing.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var thingResource = ThingResourceFromEntityAssembler.toResourceFromEntity(thing.get());
        return new ResponseEntity<>(thingResource, HttpStatus.CREATED);
    }

    /**
     * Gets a Thing by ID.
     *
     * @param id The ID of the Thing to retrieve
     * @return A ResponseEntity containing the ThingResource if found, or a 404 Not Found if not
     */
    @Operation(summary = "Get a Thing by ID", description = "Retrieves a Thing by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thing retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Thing not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ThingResource> getThingById(@PathVariable Long id) {
        var getThingByIdQuery = new GetThingByIdQuery(id);
        var thing = thingQueryService.handle(getThingByIdQuery);
        if (thing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var thingResource = ThingResourceFromEntityAssembler.toResourceFromEntity(thing.get());
        return ResponseEntity.ok(thingResource);
    }
}