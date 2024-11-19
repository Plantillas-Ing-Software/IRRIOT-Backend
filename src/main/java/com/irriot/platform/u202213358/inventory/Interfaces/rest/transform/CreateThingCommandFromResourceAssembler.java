package com.irriot.platform.u202213358.inventory.Interfaces.rest.transform;

import com.irriot.platform.u202213358.inventory.Domain.Model.Command.CreateThingCommand;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;
import com.irriot.platform.u202213358.inventory.Interfaces.rest.resources.CreateThingResource;

/**
 * Assembler class responsible for converting a CreateThingResource to a CreateThingCommand.
 */
public class CreateThingCommandFromResourceAssembler {

    /**
     * Converts a CreateThingResource to a CreateThingCommand.
     *
     * @param resource The CreateThingResource containing the Thing data
     * @return A CreateThingCommand containing the Thing's details
     */
    public static CreateThingCommand toCommandFromResource(CreateThingResource resource) {
        return new CreateThingCommand(
                SerialNumber.from(String.valueOf(resource.serialNumber())),
                resource.model(),
                resource.maximumTemperatureThreshold(),
                resource.minimumHumidityThreshold()
        );
    }
}
