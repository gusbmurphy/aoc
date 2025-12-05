package com.gusmurphy.fun.aoc.year2025.day5;

public class IngredientListSolver {
    public static long countFreshIngredientsInFile(String absolutePath) {
        var ingredientList = IngredientListParser.parseFile(absolutePath);
        return ingredientList.freshIngredientCount();
    }
}
