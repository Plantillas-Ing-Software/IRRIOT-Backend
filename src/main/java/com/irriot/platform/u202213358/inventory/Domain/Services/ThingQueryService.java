package com.irriot.platform.u202213358.inventory.Domain.Services;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.inventory.Domain.Model.Queries.GetThingByIdQuery;

import java.util.Optional;

/**
 * Service interface for handling queries related to the {@link Thing} entity.
 * <p>
 * This interface defines the methods for retrieving data related to the {@link Thing},
 * such as fetching a {@link Thing} by its ID.
 * </p>
 */
public interface ThingQueryService {

    /**
     * Handles the retrieval of a {@link Thing} based on the provided {@link GetThingByIdQuery}.
     *
     * @param query The query containing the ID of the Thing to be fetched.
     * @return An {@link Optional} containing the found {@link Thing} if present,
     *         or an empty {@link Optional} if no Thing with the given ID is found.
     */
    Optional<Thing> handle(GetThingByIdQuery query);
}