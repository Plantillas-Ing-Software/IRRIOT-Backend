package com.irriot.platform.u202213358.observability.Interfaces.rest.resources;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.observability.Domain.Model.Aggregate.ThingState;

import java.time.LocalDateTime;

/**
 * Represents a resource that encapsulates the state of a {@link Thing}.
 * <p>
 * This record is used to represent the state of a specific {@link Thing}, including details such as
 * the {@link Thing}'s serial number, operation mode, current temperature, humidity, and the timestamp
 * when the state was collected. It is typically used as a response object in APIs.
 * </p>
 *
 * @param id The unique identifier of the {@link ThingState} entity.
 * @param thingSerialNumber The serial number of the {@link Thing} associated with this state.
 * @param currentOperationMode The operation mode of the {@link Thing} (e.g., Schedule-driven, Temperature-driven).
 * @param currentTemperature The current temperature value recorded for the {@link Thing}.
 * @param currentHumidity The current humidity value recorded for the {@link Thing}.
 * @param collectedAt The timestamp when the state was collected.
 */
public record ThingStateResource(
        Long id,
        String thingSerialNumber,
        Integer currentOperationMode,
        Double currentTemperature,
        Double currentHumidity,
        LocalDateTime collectedAt
) {}