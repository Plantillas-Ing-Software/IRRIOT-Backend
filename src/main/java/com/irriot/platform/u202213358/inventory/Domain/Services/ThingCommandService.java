package com.irriot.platform.u202213358.inventory.Domain.Services;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.Command.CreateThingCommand;

import java.util.Optional;

/**
 * Service interface for handling commands related to the {@link Thing} entity.
 * <p>
 * This interface defines the methods for processing commands that mutate the state of a {@link Thing},
 * such as creating a new Thing. It provides an abstraction for the logic of handling command actions.
 * </p>
 */
public interface ThingCommandService {

    /**
     * Handles the creation of a new {@link Thing} based on the provided {@link CreateThingCommand}.
     *
     * @param command The command containing the details for creating a new Thing.
     * @return An {@link Optional} containing the created {@link Thing} if successful,
     *         or an empty {@link Optional} if the creation failed (e.g., due to validation errors).
     */
    Optional<Thing> handle(CreateThingCommand command);
}