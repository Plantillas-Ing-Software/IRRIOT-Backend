package com.irriot.platform.u202213358.inventory.Domain.Model.Command;


import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;

/**
 * Command for creating a new Thing.
 *
 * @param serialNumber Serial Number autogenerated
 * @param model the model of the Thing (required)
 * @param maximumTemperatureThreshold the maximum temperature threshold (required)
 * @param minimumHumidityThreshold    the minimum humidity threshold (required)
 */
public record CreateThingCommand(
        SerialNumber serialNumber,
        String model,
        Double maximumTemperatureThreshold,
        Double minimumHumidityThreshold
) {
}
