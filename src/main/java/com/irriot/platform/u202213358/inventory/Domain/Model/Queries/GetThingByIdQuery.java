package com.irriot.platform.u202213358.inventory.Domain.Model.Queries;

/**
 * Query object representing a request to retrieve a Thing by its ID.
 * This query is used to fetch the details of a specific Thing from the repository.
 */
public record GetThingByIdQuery(Long ThingId) {
}
