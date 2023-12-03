package ru.project.game;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.project.board.Board;
import ru.project.board.Key;
import ru.project.board.SquareBoard;
import java.util.Arrays;
import java.util.List;


class Game2048Test {
    private Game2048 game2048;

    @BeforeEach
    void setUp() {
        game2048 = new Game2048(new SquareBoard(2));
    }

    @Test
    void init() {
        game2048.init();
        int freeSpace = game2048.getGameBoard().availableSpace().size();
        Assertions.assertEquals(2, freeSpace, "Initialization 2 cell test");
    }

    @Test
    void canMove() {
        @AllArgsConstructor
        class Testcase{
            String name;
            List<Integer> inList;
            boolean expectedResult;

            void run(){
                Game2048 game20481 = new Game2048(new SquareBoard(2));
                game20481.getGameBoard().fillBoard(inList);
                Assertions.assertEquals(expectedResult, game20481.canMove(), name);
            }
        }

        List<Testcase> testcases = Arrays.asList(
                new Testcase("1" , Arrays.asList(1,2,3,4), false),
                new Testcase("2" , Arrays.asList(null,2,3,4), true),
                new Testcase("3" , Arrays.asList(null,null,null,null), true),
                new Testcase("4" , Arrays.asList(2,2,3,4), true),
                new Testcase("5" , Arrays.asList(2,3,2,3), true)
        );
        for (Testcase test: testcases) {
            test.run();
        }

    }

    @Test
    void move() {
        @AllArgsConstructor
        class Testcase{
            String name;
            List<Integer> inList;
            Direction direction;
            boolean expectedResult;

            void run(){
                Game2048 game20481 = new Game2048(new SquareBoard(2));
                game20481.getGameBoard().fillBoard(inList);
                Assertions.assertEquals(expectedResult, game20481.move(direction), name);
            }
        }

        List<Testcase> testcases = Arrays.asList(
                new Testcase("1" , Arrays.asList(2,2,3,4), Direction.UP, false),
                new Testcase("2" , Arrays.asList(2,2,3,4), Direction.RIGHT, true),
                new Testcase("3" , Arrays.asList(2,2,3,4), Direction.LEFT, true),
                new Testcase("4" , Arrays.asList(2,2,3,4), Direction.DOWN, false),
                new Testcase("5" , Arrays.asList(null,2,3,4), Direction.UP, true),
                new Testcase("6" , Arrays.asList(null,2,3,4), Direction.LEFT, true),
                new Testcase("7" , Arrays.asList(null,2,3,4), Direction.RIGHT, false),
                new Testcase("8" , Arrays.asList(null,2,3,4), Direction.DOWN, false),
                new Testcase("9" , Arrays.asList(2,2,4,null), Direction.UP, false),
                new Testcase("10" , Arrays.asList(2,2,4,null), Direction.RIGHT, true),
                new Testcase("11" , Arrays.asList(2,2,4,null), Direction.LEFT, true),
                new Testcase("12" , Arrays.asList(2,2,4,null), Direction.DOWN, true),
                new Testcase("13" , Arrays.asList(2,3,4,null), Direction.LEFT, false),
                new Testcase("14" , Arrays.asList(2,3,2,null), Direction.UP, true),
                new Testcase("15" , Arrays.asList(null,null,null,null), Direction.UP, false),
                new Testcase("16" , Arrays.asList(null,null,null,null), Direction.DOWN, false),
                new Testcase("17" , Arrays.asList(null,null,null,null), Direction.LEFT, false),
                new Testcase("18" , Arrays.asList(null,null,null,null), Direction.RIGHT, false)
        );
        for (Testcase test: testcases) {
            test.run();
        }
    }

    @Test
    void addItem() {
        game2048.addItem();
        int freeSpace = game2048.getGameBoard().availableSpace().size();
        Assertions.assertEquals(3, freeSpace, "Add 1 item in game");
    }

    @Test
    void getGameBoard() {
        Board board = new SquareBoard(2);
        Board notEqualBoard = new SquareBoard(3);
        Assertions.assertEquals(game2048.getGameBoard(), board, "Equal board test");
        Assertions.assertNotEquals(game2048.getGameBoard(), notEqualBoard, "Non equal board test");
    }

    @Test
    void hasWin() {
        Assertions.assertFalse(game2048.hasWin(), "Non Win");
        game2048.getGameBoard().addItem(new Key(0,0), 2048);
        Assertions.assertTrue(game2048.hasWin(), "Win");
    }
}