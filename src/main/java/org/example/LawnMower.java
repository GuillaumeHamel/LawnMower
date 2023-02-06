package org.example;

import org.example.helpers.Action;
import org.example.helpers.Coords;
import org.example.helpers.Dimensions;
import org.example.helpers.Orientation;

import java.util.Collections;
import java.util.List;

public record LawnMower(Coords coords, Orientation orientation, List<Action> actions) {
    public LawnMower fireActions(final Dimensions dimensions) {
        if (actions.isEmpty()) return this;

        var currentCoords = coords;
        var currentOrientation = orientation;

        for (final var action : actions) {
            switch (action) {
                case Forward -> currentCoords = Action.moveForward(currentOrientation, currentCoords, dimensions);
                case Left -> currentOrientation = Orientation.turnLeft(currentOrientation);
                case Right -> currentOrientation = Orientation.turnRight(currentOrientation);
            }
        }

        return new LawnMower(currentCoords, currentOrientation, Collections.emptyList());
    }

}
