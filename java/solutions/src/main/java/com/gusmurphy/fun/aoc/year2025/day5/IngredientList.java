package com.gusmurphy.fun.aoc.year2025.day5;

import java.util.List;
import java.util.Objects;

public class IngredientList {
    private final List<IngredientIdRange> idRangeList;
    private final List<Long> freshIngredientIdList;

    public IngredientList(List<IngredientIdRange> idRangeList, List<Long> freshIngredientIdList) {
        this.idRangeList = idRangeList;
        this.freshIngredientIdList = freshIngredientIdList;
    }

    public long freshIngredientCount() {
        return freshIngredientIdList.stream()
                .filter(id -> idRangeList.stream().anyMatch(range -> range.includes(id)))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IngredientList that = (IngredientList) o;
        return Objects.equals(idRangeList, that.idRangeList) && Objects.equals(freshIngredientIdList, that.freshIngredientIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRangeList, freshIngredientIdList);
    }
}
