package org.example.fileUtils;

import org.example.Lawn;
import org.example.LawnMower;
import org.example.helpers.Action;
import org.example.helpers.Coords;
import org.example.helpers.Dimensions;
import org.example.helpers.Orientation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class LawnParser {
    static final Pattern VALID_LAWN_DIMENSION = Pattern.compile("\\d+ \\d+");
    static final Pattern VALID_MOWER_DIMENSION = Pattern.compile("\\d+ \\d+ [NSEW]");
    static final Pattern VALID_ACTION_LIST = Pattern.compile("[ADG]*");

    static final Map<String, Orientation> ORIENTATION_MAP = Map.of(
            "N", Orientation.North,
            "E", Orientation.East,
            "W", Orientation.West,
            "S", Orientation.South
    );

    static final Map<String, Action> ACTION_MAP = Map.of(
            "A", Action.Forward,
            "G", Action.Left,
            "D", Action.Right
    );

    public static Lawn parseToLawn(final List<String> lines) {
        if (!validate(lines)) throw new IllegalArgumentException();

        return new Lawn(
                parseDimensions(lines.get(0)),
                parseLawnMowers(lines.subList(1, lines.size()))
        );
    }

    private static boolean validate(final List<String> lines) {
        return (lines != null
                && !lines.isEmpty()
                && lines.size() % 2 == 1
                && VALID_LAWN_DIMENSION.matcher(lines.get(0)).matches()
                && isValidLawnMower(lines)
                && isValidActionList(lines)
        );
    }

    private static boolean isValidLawnMower(final List<String> lines) {
        return IntStream.range(1, lines.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(lines::get)
                .allMatch(s -> VALID_MOWER_DIMENSION.matcher(s).matches());
    }

    private static boolean isValidActionList(final List<String> lines) {
        return IntStream.range(2, lines.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(lines::get)
                .allMatch(s -> VALID_ACTION_LIST.matcher(s).matches());
    }

    private static List<LawnMower> parseLawnMowers(final List<String> lawnMowers) {
        return IntStream.range(0, lawnMowers.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> parseLawnMower(lawnMowers.get(i), lawnMowers.get(i + 1)))
                .toList();
    }

    private static Dimensions parseDimensions(final String line) {
        final var dimensions = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        return new Dimensions(dimensions[0], dimensions[1]);
    }

    private static LawnMower parseLawnMower(final String lineOne, final String lineTwo) {
        return new LawnMower(
                parseCoords(lineOne),
                parseOrientation(lineOne.split(" ")[2]),
                parseActions(lineTwo));
    }

    private static Coords parseCoords(final String firstLine) {
        final var positions = Arrays.stream(firstLine.split(" ")).limit(2).mapToInt(Integer::parseInt).toArray();
        return new Coords(positions[0], positions[1]);
    }

    private static Orientation parseOrientation(final String orientation) {
        return ORIENTATION_MAP.get(orientation);
    }

    private static List<Action> parseActions(final String actionList) {
        if (actionList.length() == 0) return List.of();
        return Arrays.stream(actionList.split("")).map(ACTION_MAP::get).toList();
    }

}
