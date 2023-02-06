package org.example.helpers;

public enum Orientation {
    North, East, West, South;

    public static Orientation turnLeft(final Orientation initialOrientation) {
        return switch (initialOrientation) {
            case North -> West;
            case East -> North;
            case West -> South;
            case South -> East;
        };
    }

    public static Orientation turnRight(final Orientation initialOrientation) {
        return switch (initialOrientation) {
            case North -> East;
            case East -> South;
            case West -> North;
            case South -> West;
        };
    }

}
