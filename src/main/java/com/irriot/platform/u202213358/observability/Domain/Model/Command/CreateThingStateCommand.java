package com.irriot.platform.u202213358.observability.Domain.Model.Command;

import java.time.LocalDateTime;

/**
 * Command for creating a new ThingState.
 *
 * @param thingSerialNumber the serial number of the Thing (required)
 * @param currentOperationMode the current operation mode as an integer (required, 0-2)
 * @param currentTemperature the current temperature of the Thing (required)
 * @param currentHumidity the current humidity of the Thing (required)
 * @param collectedAt the timestamp when the state was collected (required, not in the future)
 */
public record CreateThingStateCommand(
        String thingSerialNumber,
        Integer currentOperationMode,
        Double currentTemperature,
        Double currentHumidity,
        LocalDateTime collectedAt
) {}