package com.gusmurphy.fun.aoc.year2024.day1;

import com.gusmurphy.fun.aoc.ResourcePath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityScoreTest {

    @Test
    void similarityScoreForExample() {
        long actual = SimilarityScore.ofListsInFile(ResourcePath.toTestResource(2024, 1, "test-list"));
        assertEquals(31, actual);
    }

}