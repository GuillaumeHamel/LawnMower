package helpers;

import org.example.helpers.Action;
import org.example.helpers.Coords;
import org.example.helpers.Dimensions;
import org.example.helpers.Orientation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.example.helpers.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTest {
    static final Dimensions CANVAS = new Dimensions(6, 6);
    static final Coords CENTER = new Coords(3, 3);

    @ParameterizedTest
    @MethodSource("moveForward")
    void shouldMoveInTheRightDirection(Orientation orientation, Coords expected) {
        final var actual = Action.moveForward(orientation, CENTER, CANVAS);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> moveForward() {
        return Stream.of(
                Arguments.of(North, new Coords(3, 4)),
                Arguments.of(South, new Coords(3, 2)),
                Arguments.of(East, new Coords(4, 3)),
                Arguments.of(West, new Coords(2, 3))
        );
    }

    @ParameterizedTest
    @MethodSource("moveOutsideBoundaries")
    void shouldNotMoveOutsideBoundaries(Orientation orientation, Coords initial) {
        final var actual = Action.moveForward(orientation, initial, CANVAS);
        assertEquals(initial, actual);
    }

    private static Stream<Arguments> moveOutsideBoundaries() {
        return Stream.of(
                Arguments.of(North, new Coords(0, 6)),
                Arguments.of(South, new Coords(0, 0)),
                Arguments.of(East, new Coords(6, 0)),
                Arguments.of(West, new Coords(0,0))
        );
    }

}
