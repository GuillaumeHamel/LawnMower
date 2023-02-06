package org.example.fileUtils;

import org.example.Lawn;
import org.example.helpers.Orientation;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.example.fileUtils.LawnParser.ORIENTATION_MAP;

public class MowerFormatter {
    public static List<String> mowerToString(final Lawn lawn) {
        return lawn
                .lawnMowers()
                .stream()
                .map(lawnMower -> String.format("%d %d %s", lawnMower.coords().x(), lawnMower.coords().y(), getKeysByValue(lawnMower.orientation())))
                .collect(Collectors.toList());
    }

    private static String getKeysByValue(final Orientation value) {
        final var result = ORIENTATION_MAP.entrySet()
                .stream()
                .filter(entry -> (Objects.equals(entry.getValue(), value)))
                .map(Map.Entry::getKey)
                .toList();

        if (result.size() != 1) throw new IllegalStateException();
        return result.get(0);
    }

}
