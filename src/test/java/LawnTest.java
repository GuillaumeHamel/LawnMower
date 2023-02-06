import org.example.Lawn;
import org.example.LawnMower;
import org.example.helpers.Action;
import org.example.helpers.Coords;
import org.example.helpers.Dimensions;
import org.example.helpers.Orientation;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.example.helpers.Action.*;
import static org.example.helpers.Action.Forward;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LawnTest {
    static final List<Action> EMPTY = Collections.emptyList();

    @Test
    void shouldExecuteAllActions() {
        final var boundaries = new Dimensions(5, 5);

        final var initialState = new Lawn(
                boundaries,
                List.of(
                        new LawnMower(
                                new Coords(1, 2),
                                Orientation.North,
                                List.of(Left, Forward, Left, Forward, Left, Forward, Left, Forward, Forward)),
                        new LawnMower(
                                new Coords(3, 3),
                                Orientation.East,
                                List.of(Forward, Forward, Right, Forward, Forward, Right, Forward, Right, Right, Forward)),
                        new LawnMower(
                                new Coords(0, 0),
                                Orientation.South,
                                List.of(Forward, Forward, Right, Forward, Right, Forward, Right, Forward)),
                        new LawnMower(
                                new Coords(5, 5),
                                Orientation.North,
                                List.of(Forward, Forward, Right, Forward, Right, Forward, Right, Forward))
                )
        );

        final var expectedEndState = new Lawn(
                boundaries,
                List.of(
                        new LawnMower(new Coords(1, 3), Orientation.North, EMPTY),
                        new LawnMower(new Coords(5, 1), Orientation.East, EMPTY),
                        new LawnMower(new Coords(1, 1), Orientation.East, EMPTY),
                        new LawnMower(new Coords(4, 4), Orientation.West, EMPTY)
                )
        );

        final var actualState = initialState.mow();

        assertEquals(expectedEndState, actualState);
    }

}
