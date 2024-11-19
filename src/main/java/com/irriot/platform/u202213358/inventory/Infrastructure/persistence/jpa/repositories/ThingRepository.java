package com.irriot.platform.u202213358.inventory.Infrastructure.persistence.jpa.repositories;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.ValueObject.SerialNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThingRepository extends JpaRepository<Thing, Long> {

    /**
     * Finds a Thing by its unique serial number.
     *
     * @param serialNumber the serial number of the Thing
     * @return an {@link Optional} containing the {@link Thing} if found, or empty if not found
     */
    Optional<Thing> findBySerialNumber(SerialNumber serialNumber);
}
