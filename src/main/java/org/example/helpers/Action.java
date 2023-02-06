package org.example.helpers;

public enum Action {
    Right, Left, Forward;

    public static Coords moveForward(final Orientation orientation, final Coords currentCoords, final Dimensions boundaries) {
        return switch (orientation) {
            case North -> currentCoords.y() == boundaries.length() ? currentCoords : new Coords(currentCoords.x(), currentCoords.y() + 1);
            case East -> currentCoords.x() == boundaries.width() ? currentCoords : new Coords(currentCoords.x() + 1, currentCoords.y());
            case West -> currentCoords.x() == 0 ? currentCoords : new Coords(currentCoords.x() - 1, currentCoords.y());
            case South -> currentCoords.y() == 0 ? currentCoords : new Coords(currentCoords.x(), currentCoords.y() -1);
        };
    }

}
