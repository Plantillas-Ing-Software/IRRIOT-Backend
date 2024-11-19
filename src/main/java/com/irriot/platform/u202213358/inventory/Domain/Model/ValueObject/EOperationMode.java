package com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject;

import lombok.Getter;

/**
 * Represents the operation mode of a device.
 * - ScheduleDriven: 0
 * - TemperatureDriven: 1
 * - HumidityDriven: 2
 */
@Getter
public enum EOperationMode {
    SCHEDULE_DRIVEN(0),
    TEMPERATURE_DRIVEN(1),
    HUMIDITY_DRIVEN(2);

    private final int value;

    /**
     * Constructor to initialize the operation mode with its corresponding integer value.
     *
     * @param value The integer value representing the operation mode.
     */
    EOperationMode(int value) {
        this.value = value;
    }

    /**
     * Returns the {@link EOperationMode} corresponding to the given integer value.
     *
     * @param value The integer value representing the operation mode.
     * @return The {@link EOperationMode} corresponding to the value.
     * @throws IllegalArgumentException if the value does not match any operation mode.
     */
    public static EOperationMode fromValue(int value) {
        for (EOperationMode mode : values()) {
            if (mode.getValue() == value) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Invalid operation mode value: " + value);
    }

}
