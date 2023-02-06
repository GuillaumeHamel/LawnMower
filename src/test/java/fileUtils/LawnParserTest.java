package fileUtils;

import org.example.Lawn;
import org.example.LawnMower;
import org.example.fileUtils.LawnParser;
import org.example.helpers.Coords;
import org.example.helpers.Dimensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.example.helpers.Action.*;
import static org.example.helpers.Orientation.East;
import static org.example.helpers.Orientation.North;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LawnParserTest {
    @Test
    public void readLawnFromFile_shouldReadNewLawn() {
        final var input = List.of("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");
        final var expected = new Lawn(
                new Dimensions(5, 5),
                List.of(
                        new LawnMower(new Coords(1, 2), North, List.of(Left, Forward, Left, Forward, Left, Forward, Left, Forward, Forward)),
                        new LawnMower(new Coords(3, 3), East, List.of(Forward, Forward, Right, Forward, Forward, Right, Forward, Right, Right, Forward))
                )
        );

        final var actual = LawnParser.parseToLawn(input);

        assertEquals(expected, actual);
    }

    @Test
    public void readLawnFromFile_shouldReadNewLawnWithEmptyLawnMowerList() {
        final var input = List.of("5 5");
        final var expected = new Lawn(new Dimensions(5, 5), List.of());

        final var actual = LawnParser.parseToLawn(input);

        assertEquals(expected, actual);
    }

    @Test
    public void readLawnFromFile_shouldReadNewLawnMowerWithEmptyActionList() {
        final var input = List.of("5 5", "1 2 N", "");
        final var expected = new Lawn(new Dimensions(5, 5), List.of(new LawnMower(new Coords(1, 2), North, List.of())));

        final var actual = LawnParser.parseToLawn(input);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("invalidInputs")
    public void validateInvalidInput_shouldReturnFalse(final List<String> invalidInput) {
        assertThrows(
                IllegalArgumentException.class,
                () -> LawnParser.parseToLawn(invalidInput),
                "Expected parseInput() to throw"
        );
    }

    private static Stream<Arguments> invalidInputs() {
        return Stream.of(
                // Empty lines
                Arguments.of(List.of()),

                // Even line count
                Arguments.of(List.of("5 5", "1 2 N")),

                // Invalid Lawn boundaries
                Arguments.of(List.of("5 d", "1 2 N", "GAGAGAGAA")),
                Arguments.of(List.of("5 4 3", "1 2 N", "GAGAGAGAA")),
                Arguments.of(List.of("1", "1 2 N", "GAGAGAGAA")),

                // Invalid Lawn format
                Arguments.of(List.of("5 5", "1 2 3", "GAGAGAGAA")),
                Arguments.of(List.of("5 5", "1 2 3", "GAGAGAGAA")),
                Arguments.of(List.of("5 5", "1 N W", "GAGAGAGAA")),
                Arguments.of(List.of("5 5", "1 2 A", "GAGAGAGAA")),

                // Invalid action list
                Arguments.of(List.of("5 5", "1 2 N", "GA GA")),
                Arguments.of(List.of("5 5", "1 2 N", "FLR")),
                Arguments.of(List.of("5 5", "1 2 N", "5"))
        );
    }

}
