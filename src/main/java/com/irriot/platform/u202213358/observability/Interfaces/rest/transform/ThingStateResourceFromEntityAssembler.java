package com.irriot.platform.u202213358.observability.Interfaces.rest.transform;

import com.irriot.platform.u202213358.observability.Domain.Model.Aggregate.ThingState;
import com.irriot.platform.u202213358.observability.Interfaces.rest.resources.ThingStateResource;

/**
 * Assembler class responsible for converting a {@link ThingState} entity into a {@link ThingStateResource}.
 * <p>
 * This class provides the functionality to transform a {@link ThingState} entity (usually fetched from the database)
 * into a resource object. The resulting resource is typically used for returning data in API responses, making it suitable
 * for client consumption.
 * </p>
 */
public class ThingStateResourceFromEntityAssembler {

    /**
     * Converts a {@link ThingState} entity to a {@link ThingStateResource}.
     *
     * @param entity The {@link ThingState} entity to be converted.
     * @return A {@link ThingStateResource} containing data extracted from the provided entity.
     */
    public static ThingStateResource toResourceFromEntity(ThingState entity) {
        return new ThingStateResource(
                entity.getId(),
                entity.getThingSerialNumber().getValue(),
                entity.getCurrentOperationMode(),
                entity.getCurrentTemperature(),
                entity.getCurrentHumidity(),
                entity.getCollectedAt()
        );
    }
}