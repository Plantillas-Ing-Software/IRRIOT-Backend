package com.irriot.platform.u202213358.observability.Application.Internal.outboundservices.acl;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Interfaces.acl.InventoryContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ExternalThingService
 *
 * <p>
 * This class is an outbound service used by the Observability Context to interact with the Inventory Context.
 * It is implemented as part of an anti-corruption layer (ACL) to decouple the Observability Context from the Inventory Context.
 * </p>
 */
@Service
public class ExternalThingService {

    private final InventoryContextFacade inventoryContextFacade;

    public ExternalThingService(InventoryContextFacade inventoryContextFacade) {
        this.inventoryContextFacade = inventoryContextFacade;
    }

    /**
     * Fetch Thing by serial number
     *
     * @param serialNumber the serial number to search for
     * @return Thing if found, empty otherwise
     */
    public Optional<Thing> fetchThingBySerialNumber(String serialNumber) {
        return inventoryContextFacade.fetchThingBySerialNumber(serialNumber);
    }

    /**
     * Update Thing operation mode
     *
     * @param serialNumber  the serial number of the Thing
     * @param operationMode the new operation mode
     */
    public void updateThingOperationMode(String serialNumber, Integer operationMode) {
        inventoryContextFacade.updateThingOperationMode(serialNumber, operationMode);
    }
}
