package com.irriot.platform.u202213358.inventory.Application.Internal.queryservice;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.Queries.GetThingByIdQuery;
import com.irriot.platform.u202213358.inventory.Domain.Services.ThingQueryService;
import com.irriot.platform.u202213358.inventory.Infrastructure.persistence.jpa.repositories.ThingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThingQueryServiceImpl implements ThingQueryService {

    /**
     * Constructor for the ThingQueryServiceImpl class.
     */
    private final ThingRepository thingRepository;
    public ThingQueryServiceImpl(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    /**
     * Handles the query to get a Thing by its ID.
     * This method retrieves a Thing from the repository based on the provided ID.
     *
     * @param query The query containing the ID of the Thing to retrieve.
     * @return An Optional containing the Thing if found, or an empty Optional
     * if no Thing with the provided ID exists.
     */
    @Override
    public Optional<Thing> handle(GetThingByIdQuery query) {
        return thingRepository.findById(query.ThingId());
    }
}
