package com.irriot.platform.u202213358.observability.Application.Internal.commandservice;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.EOperationMode;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;
import com.irriot.platform.u202213358.inventory.Infrastructure.persistence.jpa.repositories.ThingRepository;
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
 * If all conditions are met, it creates and saves a new {@link ThingState} along with updating
 * the {@link Thing}'s operation mode.
 * </p>
 */
@Service
public class ThingStateCommandServiceImpl implements ThingStateCommandService {

    private final ThingStateRepository thingStateRepository;
    private final ThingRepository thingRepository;

    /**
     * Constructor for initializing the {@link ThingStateCommandServiceImpl}.
     *
     * @param thingStateRepository The repository used to manage {@link ThingState} entities.
     * @param thingRepository The repository used to manage {@link Thing} entities.
     */
    @Autowired
    public ThingStateCommandServiceImpl(ThingStateRepository thingStateRepository, ThingRepository thingRepository) {
        this.thingStateRepository = thingStateRepository;
        this.thingRepository = thingRepository;
    }

    /**
     * Handles the creation of a {@link ThingState} based on the provided {@link CreateThingStateCommand}.
     * <p>
     * This method performs the following validations:
     * <ul>
     *     <li>Checks if the {@link Thing} associated with the provided serial number exists.</li>
     *     <li>Ensures the `collectedAt` timestamp is not in the future.</li>
     *     <li>Verifies that a {@link ThingState} with the same serial number and timestamp does not already exist.</li>
     * </ul>
     * If all validations pass, a new {@link ThingState} is created and saved, and the {@link Thing}'s
     * operation mode is updated accordingly.
     * </p>
     *
     * @param command The {@link CreateThingStateCommand} containing the data for the new {@link ThingState}.
     * @return An {@link Optional} containing the newly created {@link ThingState} if successful,
     *         or an empty {@link Optional} if the creation failed due to validation errors.
     * @throws IllegalArgumentException if the `Thing` does not exist, if the `collectedAt` timestamp
     *                                  is in the future, or if a {@link ThingState} already exists
     *                                  with the same serial number and timestamp.
     */
    @Override
    public Optional<ThingState> handle(CreateThingStateCommand command) {
        Thing thing = thingRepository.findBySerialNumber(SerialNumber.from(command.thingSerialNumber()))
                .orElseThrow(() -> new IllegalArgumentException("Thing not found"));

        if (command.collectedAt().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("CollectedAt timestamp cannot be in the future.");
        }

        if (thingStateRepository.existsByThingSerialNumberAndCollectedAt(SerialNumber.from(command.thingSerialNumber()), command.collectedAt())) {
            throw new IllegalArgumentException("A ThingState with the same serial number and collectedAt already exists.");
        }

        EOperationMode operationMode = EOperationMode.fromValue(command.currentOperationMode());

        ThingState thingState = new ThingState(command, thing);

        thing.setOperationMode(operationMode);

        thingStateRepository.save(thingState);
        thingRepository.save(thing);

        return Optional.of(thingState);
    }
}