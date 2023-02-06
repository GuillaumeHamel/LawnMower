package org.example;

import org.example.helpers.Dimensions;

import java.util.List;
import java.util.stream.Collectors;

public record Lawn(Dimensions boundaries, List<LawnMower> lawnMowers) {
    public Lawn mow() {
        return new Lawn(
                boundaries,
                lawnMowers.stream()
                        .map(lawnMower -> lawnMower.fireActions(boundaries))
                        .collect(Collectors.toList())
        );
    }

}
