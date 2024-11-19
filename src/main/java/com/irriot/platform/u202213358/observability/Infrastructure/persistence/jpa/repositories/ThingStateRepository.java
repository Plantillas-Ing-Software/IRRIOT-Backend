package com.irriot.platform.u202213358.observability.Infrastructure.persistence.jpa.repositories;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;
import com.irriot.platform.u202213358.observability.Domain.Model.Aggregate.ThingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Repository interface for accessing and managing {@link ThingState} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations for {@link ThingState} entities.
 * Additionally, it defines custom query methods for checking the existence of {@link ThingState} entities
 * based on specific conditions.
 * </p>
 */
@Repository
public interface ThingStateRepository extends JpaRepository<ThingState, Long> {

    /**
     * Checks whether a {@link ThingState} already exists for a given {@link SerialNumber} and {@link LocalDateTime}.
     * <p>
     * This method is useful for ensuring that no duplicate states exist for the same {@link Thing} at a specific time.
     * </p>
     *
     * @param thingSerialNumber The {@link SerialNumber} of the {@link Thing} to check.
     * @param collectedAt The timestamp of the collected data to check.
     * @return {@code true} if a {@link ThingState} exists with the given {@link SerialNumber} and {@link LocalDateTime},
     *         {@code false} otherwise.
     */
    boolean existsByThingSerialNumberAndCollectedAt(SerialNumber thingSerialNumber, LocalDateTime collectedAt);
}