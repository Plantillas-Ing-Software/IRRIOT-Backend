package com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate;

import com.irriot.platform.u202213358.inventory.Domain.Model.Command.CreateThingCommand;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.EOperationMode;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;
import com.irriot.platform.u202213358.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Thing extends AuditableAbstractAggregateRoot<Thing> {

    @Embedded
    private SerialNumber serialNumber;

    @NotBlank(message = "The model cannot be blank")
    @Column(nullable = false)
    private String model;

    /**
     * The operation mode of the Thing.
     * It uses an enum to define different operation modes, stored as an ordinal value.
     */
    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EOperationMode operationMode;


    @NotNull(message = "The maximum temperature threshold cannot be null")
    @DecimalMin(value = "-40.00", inclusive = true, message = "The maximum temperature threshold must be at least -40.00")
    @DecimalMax(value = "85.00", inclusive = true, message = "The maximum temperature threshold must not exceed 85.00")
    @Column(nullable = false)
    private Double maximumTemperatureThreshold;

    @NotNull(message = "The minimum humidity threshold cannot be null")
    @DecimalMin(value = "0.00", inclusive = true, message = "The minimum humidity threshold must be at least 0.00")
    @DecimalMax(value = "100.00",inclusive = true, message = "The minimum humidity threshold must not exceed 100.00")
    @Column(nullable = false)
    private Double minimumHumidityThreshold;

    /**
     * Default constructor for JPA.
     * Protected to prevent direct instantiation outside JPA context.
     */
    protected Thing() {}

    /**
     * Constructor to initialize a new thing based on the command {@link CreateThingCommand}.
     *
     * @param command Command containing the details of the Thing.
     */
    public Thing(CreateThingCommand command) {
        this.serialNumber = SerialNumber.generate();
        this.model = command.model();
        this.operationMode = EOperationMode.SCHEDULE_DRIVEN;
        this.maximumTemperatureThreshold = command.maximumTemperatureThreshold();
        this.minimumHumidityThreshold = command.minimumHumidityThreshold();
    }

}
