package org.example;

import org.example.helpers.Dimensions;

import java.util.List;

public record Lawn(Dimensions boundaries, List<LawnMower> lawnMowers) { }
