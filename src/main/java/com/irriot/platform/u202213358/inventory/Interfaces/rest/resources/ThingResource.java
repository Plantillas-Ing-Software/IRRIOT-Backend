package com.irriot.platform.u202213358.inventory.Interfaces.rest.resources;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;

/**
 * Represents the resource returned for a {@link Thing} entity.
 * <p>
 * This record contains the details of an existing {@link Thing}, including its unique identifier,
 * serial number, model, maximum temperature threshold, and minimum humidity threshold.
 * </p>
 */
public record ThingResource(
        Long id,
        String serialNumber,
        String model,
        Double maximumTemperatureThreshold,
        Double minimumHumidityThreshold
) {}
