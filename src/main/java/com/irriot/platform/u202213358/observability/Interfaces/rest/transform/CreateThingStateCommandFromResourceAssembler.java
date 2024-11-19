package com.irriot.platform.u202213358.observability.Interfaces.rest.transform;

import com.irriot.platform.u202213358.inventory.Domain.Model.Aggregate.Thing;
import com.irriot.platform.u202213358.observability.Domain.Model.Command.CreateThingStateCommand;
import com.irriot.platform.u202213358.observability.Interfaces.rest.resources.CreateThingStateResource;

/**
 * Assembler class responsible for converting a {@link CreateThingStateResource} into a {@link CreateThingStateCommand}.
 * <p>
 * This class provides the functionality to transform a resource object into a command object, which can then
 * be processed by the application service layer. The transformation typically occurs when receiving data from
 * the client, such as an API request, and turning it into a command for business logic processing.
 * </p>
 */
public class CreateThingStateCommandFromResourceAssembler {

    /**
     * Converts a {@link CreateThingStateResource} to a {@link CreateThingStateCommand}.
     *
     * @param resource The resource containing the state data of a {@link Thing}.
     * @return A {@link CreateThingStateCommand} containing the data from the provided resource.
     */
    public static CreateThingStateCommand toCommandFromResource(CreateThingStateResource resource) {
        return new CreateThingStateCommand(
                resource.thingSerialNumber(),
                resource.currentOperationMode(),
                resource.currentTemperature(),
                resource.currentHumidity(),
                resource.collectedAt()
        );
    }
}