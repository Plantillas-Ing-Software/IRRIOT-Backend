package com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

/**
 * Represents a serial number for a device.
 * <p>
 * The serial number is a unique identifier used to distinguish individual devices.
 * It cannot be null or blank and must be unique within the system.
 * </p>
 */
@Getter
@Embeddable
public class SerialNumber {

    @Column(nullable = false, unique = true, updatable = false)
    private String value;

    protected SerialNumber() {}

    /**
     * Private constructor to create a {@link SerialNumber} from the given string value.
     * <p>
     * Throws an {@link IllegalArgumentException} if the value is null or blank.
     * </p>
     *
     * @param value The serial number value.
     */
    private SerialNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Serial number cannot be null or blank.");
        }
        this.value = value;
    }

    /**
     * Creates a new {@link SerialNumber} from the given string value.
     *
     * @param value The serial number value.
     * @return A new {@link SerialNumber} instance.
     */
    public static SerialNumber from(String value) {
        return new SerialNumber(value);
    }

    /**
     * Generates a new {@link SerialNumber} by creating a random UUID and using it as the value.
     *
     * @return A new {@link SerialNumber} instance with a generated value.
     */
    public static SerialNumber generate() {
        return new SerialNumber(UUID.randomUUID().toString());
    }

}
