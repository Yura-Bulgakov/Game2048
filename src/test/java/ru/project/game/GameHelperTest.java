package ru.project.game;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class GameHelperTest {
    private GameHelper gameHelper;

    @BeforeEach
    void setUp() {
        gameHelper = new GameHelper();
    }

    @Test
    void moveAndMergeEqual() {
        @AllArgsConstructor
        class Testcase {
            String name;
            List<Integer> inList;
            List<Integer> expectedOut;

            void run() {
                Assertions.assertArrayEquals(expectedOut.toArray(), gameHelper.moveAndMergeEqual(inList).toArray(), name);
            }
        }
        List<Testcase> testcases = Arrays.asList(
                new Testcase("1", Arrays.asList(1, 2, null, 3), Arrays.asList(1, 2, 3, null)),
                new Testcase("2", Arrays.asList(2, 2, null, 3), Arrays.asList(4, 3, null, null)),
                new Testcase("3", Arrays.asList(2, 2, 4, 4), Arrays.asList(4, 8, null, null)),
                new Testcase("4", Arrays.asList(2, 2, 2, 3), Arrays.asList(4, 2, 3, null)),
                new Testcase("5", Arrays.asList(2, null, null, 2), Arrays.asList(4, null, null, null)),
                new Testcase("6", Arrays.asList(null, null, null, null), Arrays.asList(null, null, null, null)),
                new Testcase("7", Arrays.asList(null, null, null, 2), Arrays.asList(2, null, null, null)),
                new Testcase("8", Arrays.asList(null, null, 2, 2), Arrays.asList(4, null, null, null))
        );

        for (Testcase test: testcases) {
            test.run();
        }
    }
}