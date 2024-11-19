package com.irriot.platform.u202213358.observability.Domain.Model.Aggregate;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;
import com.irriot.platform.u202213358.observability.Domain.Model.Command.CreateThingStateCommand;
import com.irriot.platform.u202213358.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents the state of a {@link Thing} at a specific point in time.
 * <p>
 * This entity contains information about the current operation mode, temperature, and humidity of
 * the {@link Thing}, as well as the timestamp when the data was collected. Each {@link ThingState}
 * is associated with a specific {@link Thing}, identified by the serial number and timestamp.
 * </p>
 * <p>
 * A {@link UniqueConstraint} ensures that each combination of {@link #thingSerialNumber} and
 * {@link #collectedAt} is unique, preventing the creation of duplicate states for the same thing
 * at the same time.
 * </p>
 */
@Entity
@Getter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"thingSerialNumber", "collectedAt"})})
public class ThingState extends AuditableAbstractAggregateRoot<ThingState> {

    /**
     * The serial number of the associated {@link Thing}.
     * This is an embedded object representing the unique serial number for the thing.
     */
    @Embedded
    private SerialNumber thingSerialNumber;

    @NotNull(message = "The current operation mode cannot be null")
    @Min(value = 0, message = "The current operation mode must be at least 0")
    @Max(value = 2, message = "The current operation mode must not exceed 2")
    @Column(nullable = false)
    private Integer currentOperationMode;

    @NotNull(message = "The current temperature cannot be null")
    @DecimalMin(value = "-40.00", message = "The current temperature must be at least -40.00")
    @DecimalMax(value = "85.00", message = "The current temperature must not exceed 85.00")
    @Column(nullable = false)
    private Double currentTemperature;

    @NotNull(message = "The current humidity cannot be null")
    @DecimalMin(value = "0.00", message = "The current humidity must be at least 0.00")
    @DecimalMax(value = "100.00", message = "The current humidity must not exceed 100.00")
    @Column(nullable = false)
    private Double currentHumidity;

    @NotNull(message = "The collectedAt timestamp cannot be null")
    @PastOrPresent(message = "The collectedAt timestamp must not be in the future")
    @Column(nullable = false)
    private LocalDateTime collectedAt;

    /**
     * The {@link Thing} associated with this state.
     * This is a many-to-one relationship where each {@link ThingState} is linked to a specific {@link Thing}.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thing_id", nullable = false)
    private Thing thing;

    protected ThingState() {}

    /**
     * Constructor to initialize a new {@link ThingState} based on the provided command.
     *
     * @param command The {@link CreateThingStateCommand} containing the state data.
     * @param thing The associated {@link Thing}.
     */
    public ThingState(CreateThingStateCommand command, Thing thing) {
        this.thingSerialNumber = thing.getSerialNumber();
        this.currentOperationMode = (command.currentOperationMode());
        this.currentTemperature = command.currentTemperature();
        this.currentHumidity = command.currentHumidity();
        this.collectedAt = command.collectedAt();
        this.thing = thing;
    }
}