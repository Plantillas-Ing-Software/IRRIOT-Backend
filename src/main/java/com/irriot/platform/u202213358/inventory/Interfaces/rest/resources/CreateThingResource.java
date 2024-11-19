package com.irriot.platform.u202213358.inventory.Interfaces.rest.resources;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;

/**
 * Represents the resource used to create a new {@link Thing}.
 * <p>
 * This record contains the necessary information to create a new {@link Thing} entity,
 * including the serial number, model, maximum temperature threshold, and minimum humidity threshold.
 * </p>
 */
public record CreateThingResource(
        SerialNumber serialNumber,
        String model,
        Double maximumTemperatureThreshold,
        Double minimumHumidityThreshold
) {}
