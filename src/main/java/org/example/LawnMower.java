package org.example;

import org.example.helpers.Action;
import org.example.helpers.Coords;
import org.example.helpers.Orientation;

import java.util.List;

public record LawnMower(Coords coords, Orientation orientation, List<Action> actions) { }
