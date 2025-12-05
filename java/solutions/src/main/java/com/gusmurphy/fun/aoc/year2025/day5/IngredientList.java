package com.gusmurphy.fun.aoc.year2025.day5;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class IngredientList {
    private final JaggedIdRange idRange;
    private final List<Long> freshIngredientIdList;

    public IngredientList(List<JaggedIdRange.IdRange> idRangeList, List<Long> freshIngredientIdList) {
        this.idRange = idRangeList.stream()
                .sorted(Comparator.comparingLong(JaggedIdRange.IdRange::lower))
                .map(JaggedIdRange::new)
                .reduce(JaggedIdRange::mergeWith)
                .orElseThrow();

        this.freshIngredientIdList = freshIngredientIdList;
    }

    public long freshIngredientCount() {
        return freshIngredientIdList.stream()
                .filter(idRange::includes)
                .count();
    }

    public long possibleFreshIngredientCount() {
        return idRange.rangeLength();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IngredientList that = (IngredientList) o;
        return Objects.equals(idRange, that.idRange) && Objects.equals(freshIngredientIdList, that.freshIngredientIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRange, freshIngredientIdList);
    }
}
