package helpers;

import org.example.helpers.Orientation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.example.helpers.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {
    @ParameterizedTest
    @MethodSource("turnLeft")
    void shouldTurnLeft(final Orientation initial, final Orientation expected) {
        final var actual = Orientation.turnLeft(initial);
        assertEquals(expected, actual);
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
    void shouldTurnRight(final Orientation initial, final Orientation expected) {
        final var actual = Orientation.turnRight(initial);
        assertEquals(expected, actual);
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
