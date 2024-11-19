package com.irriot.platform.u202213358.inventory.Application.Internal.commandservice;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.Command.CreateThingCommand;
import com.irriot.platform.u202213358.inventory.Domain.Services.ThingCommandService;
import com.irriot.platform.u202213358.inventory.Infrastructure.persistence.jpa.repositories.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ThingCommandServiceImpl implements ThingCommandService {

    private final ThingRepository thingRepository;

    /**
     * Constructor for the ThingCommandServiceImpl class.
     *
     * @param thingRepository The repository for accessing Thing entities.
     */
    @Autowired
    public ThingCommandServiceImpl(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    /**
     * Handles the creation of a new Thing based on the provided command.
     * Checks if a Thing with the given serial number already exists.
     * If it does, throws an IllegalArgumentException indicating that the serial number must be unique.
     * Otherwise, creates and saves a new Thing.
     *
     * @param command The command containing the details for creating a new Thing.
     * @return An Optional containing the saved Thing if successful.
     * @throws IllegalArgumentException if a Thing with the same serial number already exists.
     */
    @Override
    public Optional<Thing> handle(CreateThingCommand command) {

        if (thingRepository.findBySerialNumber(command.serialNumber()).isPresent()) {
            throw new IllegalArgumentException("Serial number must be unique.");
        }

        Thing thing = new Thing(command);
        return Optional.of(thingRepository.save(thing));
    }
}
