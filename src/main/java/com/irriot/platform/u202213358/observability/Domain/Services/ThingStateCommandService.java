package com.irriot.platform.u202213358.observability.Domain.Services;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.observability.Domain.Model.Aggregate.ThingState;
import com.irriot.platform.u202213358.observability.Domain.Model.Command.CreateThingStateCommand;

import java.util.Optional;

/**
 * Service interface for handling commands related to the creation of {@link ThingState} entities.
 * <p>
 * This interface defines the contract for services that handle the creation of a new {@link ThingState}.
 * Implementations of this service will process a {@link CreateThingStateCommand} and persist the resulting
 * {@link ThingState} entity to the repository.
 * </p>
 */
public interface ThingStateCommandService {

    /**
     * Handles the creation of a new {@link ThingState} based on the provided command.
     * <p>
     * This method validates the input command, ensures the corresponding {@link Thing} exists,
     * checks for any conflicts (such as duplicate timestamps), and then creates and persists
     * a new {@link ThingState}.
     * </p>
     *
     * @param command The {@link CreateThingStateCommand} containing the data for the new {@link ThingState}.
     * @return An {@link Optional} containing the created {@link ThingState} if successful, or empty if no state was created.
     * @throws IllegalArgumentException If the {@link Thing} does not exist, or if the collected timestamp is in the future,
     *                                  or if a duplicate state already exists for the same thing and timestamp.
     */
    Optional<ThingState> handle(CreateThingStateCommand command);
}