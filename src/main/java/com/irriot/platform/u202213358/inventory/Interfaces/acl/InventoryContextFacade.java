package com.irriot.platform.u202213358.inventory.Interfaces.acl;

import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.EOperationMode;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;
import com.irriot.platform.u202213358.inventory.Infrastructure.persistence.jpa.repositories.ThingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Facade for the Inventory context.
 *
 * <p>
 * This facade is used by other contexts to interact with the Inventory context.
 * It is implemented as part of an anti-corruption layer (ACL) to maintain context boundaries.
 * </p>
 */
@Service
public class InventoryContextFacade {
    private final ThingRepository thingRepository;

    public InventoryContextFacade(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    /**
     * Fetches a Thing by its serial number
     *
     * @param serialNumber the serial number to search for
     * @return the Thing if found
     */
    public Optional<Thing> fetchThingBySerialNumber(String serialNumber) {
        return thingRepository.findBySerialNumber(SerialNumber.from(serialNumber));
    }

    /**
     * Updates the operation mode of a Thing
     *
     * @param serialNumber  the serial number of the Thing
     * @param operationMode the new operation mode
     */
    public void updateThingOperationMode(String serialNumber, Integer operationMode) {
        thingRepository.findBySerialNumber(SerialNumber.from(serialNumber))
                .map(thing -> {
                    thing.setOperationMode(EOperationMode.fromValue(operationMode));
                    thingRepository.save(thing);
                    return true;
                });
    }
}