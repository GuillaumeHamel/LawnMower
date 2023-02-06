package fileUtils;

import org.example.Lawn;
import org.example.LawnMower;
import org.example.fileUtils.MowerFormatter;
import org.example.helpers.Action;
import org.example.helpers.Coords;
import org.example.helpers.Dimensions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.example.helpers.Orientation.East;
import static org.example.helpers.Orientation.North;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerFormatterTest {
    static final List<Action> EMPTY = Collections.emptyList();

    @Test
    public void mowerToString_shouldProduceCorrectFormat() {
        final var input = new Lawn(
                new Dimensions(5, 5),
                List.of(
                        new LawnMower(new Coords(1, 3), North, EMPTY),
                        new LawnMower(new Coords(5, 1), East, EMPTY)
                )
        );

        final var expected = List.of("1 3 N", "5 1 E");
        final var actual = MowerFormatter.mowerToString(input);

        assertEquals(expected, actual);
    }

    @Test
    public void mowerToString_shouldProduceEmptyStringWithNoMowers() {
        final var input = new Lawn(
                new Dimensions(5, 5),
                Collections.emptyList()
        );

        final var expected = Collections.emptyList();
        final var actual = MowerFormatter.mowerToString(input);

        assertEquals(expected, actual);
    }

}
