package com.irriot.platform.u202213358.observability.Application.Internal.commandservice;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;
import com.irriot.platform.u202213358.observability.Application.Internal.outboundservices.acl.ExternalThingService;
import com.irriot.platform.u202213358.observability.Domain.Model.Aggregate.ThingState;
import com.irriot.platform.u202213358.observability.Domain.Model.Command.CreateThingStateCommand;
import com.irriot.platform.u202213358.observability.Domain.Services.ThingStateCommandService;
import com.irriot.platform.u202213358.observability.Infrastructure.persistence.jpa.repositories.ThingStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementation of the {@link ThingStateCommandService} interface responsible for handling
 * the creation of {@link ThingState} entities.
 * <p>
 * This service validates the provided {@link CreateThingStateCommand}, checks for the existence
 * of the associated {@link Thing}, ensures that the `collectedAt` timestamp is not in the future,
 * and that a duplicate `ThingState` does not already exist with the same serial number and timestamp.
 * Additionally, the service checks the user's permissions using ACL to ensure that they have the
 * necessary rights to perform the operation.
 * If all conditions are met, it creates and saves a new {@link ThingState} and updates the {@link Thing}'s operation mode.
 * </p>
 */
@Service
public class ThingStateCommandServiceImpl implements ThingStateCommandService {

    private final ThingStateRepository thingStateRepository;
    private final ExternalThingService externalThingService;

    /**
     * Constructor for initializing the {@link ThingStateCommandServiceImpl}.
     *
     * @param thingStateRepository The repository used to manage {@link ThingState} entities.
     * @param externalThingService The service used to manage {@link Thing} entities.
     */
    @Autowired
    public ThingStateCommandServiceImpl(ThingStateRepository thingStateRepository, ExternalThingService externalThingService) {
        this.thingStateRepository = thingStateRepository;
        this.externalThingService = externalThingService;
    }

    /**
     * Handles the creation of a {@link ThingState} based on the provided {@link CreateThingStateCommand}.
     * <p>
     * This method performs the following validations:
     * Checks if the {@link Thing} associated with the provided serial number exists.
     * Ensures the `collectedAt` timestamp is not in the future.
     * Verifies that a {@link ThingState} with the same serial number and timestamp does not already exist.
     * Checks if the current user has the necessary permissions to perform the operation using ACL.
     * <p>
     * If all validations pass, a new {@link ThingState} is created and saved, and the {@link Thing}'s
     * operation mode is updated accordingly.
     * </p>
     * @param command The {@link CreateThingStateCommand} containing the data for the new {@link ThingState}.
     * @return An {@link Optional} containing the newly created {@link ThingState} if successful,
     *         or an empty {@link Optional} if the creation failed due to validation errors or permission issues.
     * @throws IllegalArgumentException if the `Thing` does not exist, if the `collectedAt` timestamp
     *                                  is in the future, if a {@link ThingState} already exists
     *                                  with the same serial number and timestamp, or if the user does not have
     *                                  the necessary permissions to create a ThingState.
     */
    @Override
    public Optional<ThingState> handle(CreateThingStateCommand command) {
        Thing thing = externalThingService.fetchThingBySerialNumber(command.thingSerialNumber())
                .orElseThrow(() -> new IllegalArgumentException("Thing not found"));

        if (command.collectedAt().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("CollectedAt timestamp cannot be in the future.");
        }

        if (thingStateRepository.existsByThingSerialNumberAndCollectedAt(
                SerialNumber.from(command.thingSerialNumber()),
                command.collectedAt())) {
            throw new IllegalArgumentException("A ThingState with the same serial number and collectedAt already exists.");
        }

        ThingState thingState = new ThingState(command, thing);

        externalThingService.updateThingOperationMode(
                command.thingSerialNumber(),
                command.currentOperationMode()
        );

        return Optional.of(thingStateRepository.save(thingState));
    }
}