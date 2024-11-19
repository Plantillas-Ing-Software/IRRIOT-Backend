package com.irriot.platform.u202213358.inventory.Interfaces.rest.transform;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Interfaces.rest.resources.ThingResource;

/**
 * Assembler class responsible for converting a Thing entity to a ThingResource.
 */
public class ThingResourceFromEntityAssembler {

    /**
     * Converts a Thing entity to a ThingResource.
     *
     * @param entity The Thing entity to be converted
     * @return A ThingResource containing the Thing's details
     */
    public static ThingResource toResourceFromEntity(Thing entity) {
        return new ThingResource(
                entity.getId(),
                entity.getSerialNumber().getValue(),
                entity.getModel(),
                entity.getMaximumTemperatureThreshold(),
                entity.getMinimumHumidityThreshold()
        );
    }
}
