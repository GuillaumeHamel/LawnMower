import org.example.LawnMower;
import org.example.helpers.Action;
import org.example.helpers.Coords;
import org.example.helpers.Dimensions;
import org.example.helpers.Orientation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.example.helpers.Action.*;
import static org.example.helpers.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LawnMowerTest {
    static final List<Action> FORWARD = List.of(Forward);
    static final List<Action> LEFT = List.of(Left);
    static final List<Action> RIGHT = List.of(Right);
    static final List<Action> EMPTY = Collections.emptyList();
    static final Dimensions CANVAS = new Dimensions(6, 6);
    static final Coords CENTER = new Coords(3, 3);

    @ParameterizedTest
    @MethodSource("moveForward")
    void shouldMoveForward(final Orientation initialOrientation, final Coords expected) {
        final var initialLawnMower = new LawnMower(CENTER, initialOrientation, FORWARD);
        final var expectedLawnMower = new LawnMower(expected, initialOrientation, EMPTY);

        final var actualLawnMower = initialLawnMower.fireActions(CANVAS);

        assertEquals(expectedLawnMower, actualLawnMower);
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
    void shouldNotExceedBoundaries(final Orientation initialOrientation, final Coords initial) {
        final var initialLawnMower = new LawnMower(initial, initialOrientation, FORWARD);
        final var expectedLawnMower = new LawnMower(initial, initialOrientation, EMPTY);

        final var actualLawnMower = initialLawnMower.fireActions(CANVAS);

        assertEquals(expectedLawnMower, actualLawnMower);
    }

    private static Stream<Arguments> moveOutsideBoundaries() {
        return Stream.of(
                Arguments.of(North, new Coords(0, 6)),
                Arguments.of(South, new Coords(0, 0)),
                Arguments.of(East, new Coords(6, 0)),
                Arguments.of(West, new Coords(0,0))
        );
    }

    @ParameterizedTest
    @MethodSource("turnLeft")
    void shouldTurnLeft(final Orientation initialOrientation, final Orientation expectedOrientation) {
        final var initialLawnMower = new LawnMower(CENTER, initialOrientation, LEFT);
        final var expectedLawnMower = new LawnMower(CENTER, expectedOrientation, EMPTY);

        final var actualLawnMower = initialLawnMower.fireActions(CANVAS);

        assertEquals(expectedLawnMower, actualLawnMower);
    }

    private static Stream<Arguments> turnLeft() {
        return Stream.of(
                Arguments.of(North, West),
                Arguments.of(South, East),
                Arguments.of(East, North),
                Arguments.of(West, South)
        );
    }

    @ParameterizedTest
    @MethodSource("turnRight")
    void shouldTurnRight(final Orientation initialOrientation, final Orientation expectedOrientation) {
        final var initialLawnMower = new LawnMower(CENTER, initialOrientation, RIGHT);
        final var expectedLawnMower = new LawnMower(CENTER, expectedOrientation, EMPTY);

        final var actualLawnMower = initialLawnMower.fireActions(CANVAS);

        assertEquals(expectedLawnMower, actualLawnMower);
    }

    private static Stream<Arguments> turnRight() {
        return Stream.of(
                Arguments.of(North, East),
                Arguments.of(South, West),
                Arguments.of(East, South),
                Arguments.of(West, North)
        );
    }

}
