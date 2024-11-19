package com.irriot.platform.u202213358.observability.Interfaces.rest.resources;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.observability.Domain.Model.Aggregate.ThingState;

import java.time.LocalDateTime;

/**
 * Represents a resource for creating a new {@link ThingState}.
 * <p>
 * This record is used to encapsulate the necessary information for creating a new {@link ThingState} entity,
 * such as the serial number of the associated {@link Thing}, its current operation mode, temperature, humidity,
 * and the timestamp when the data was collected.
 * </p>
 *
 * @param thingSerialNumber The serial number of the {@link Thing} to which the state belongs.
 * @param currentOperationMode The operation mode of the {@link Thing} (e.g., Schedule-driven, Temperature-driven).
 * @param currentTemperature The current temperature value of the {@link Thing}.
 * @param currentHumidity The current humidity value of the {@link Thing}.
 * @param collectedAt The timestamp indicating when the state was collected.
 */
public record CreateThingStateResource(
        String thingSerialNumber,
        Integer currentOperationMode,
        Double currentTemperature,
        Double currentHumidity,
        LocalDateTime collectedAt
) {}