package ru.project.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.project.board.Board;
import ru.project.board.Key;
import ru.project.board.SquareBoard;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;


class Game2048Test {
    private Game2048 game2048;

    @BeforeEach
    void setUp() {
        game2048 = new Game2048(new SquareBoard<Integer>(2));
        game2048.getGameBoard().clearBoard();
    }

    @Test
    void init() {
        game2048.init();
        int freeSpace = game2048.getGameBoard().availableSpace().size();
        Assertions.assertEquals(2, freeSpace, "Initialization 2 cell test");
    }

    @Test
    void canMove() {

        class Testcase{
            final String name;
            final List<Integer> inList;
            final boolean expectedResult;

            public Testcase(String name, List<Integer> inList, boolean expectedResult) {
                this.name = name;
                this.inList = inList;
                this.expectedResult = expectedResult;
            }

            void run(){
                Game2048 game20481 = new Game2048(new SquareBoard(2));
                game20481.getGameBoard().fillBoard(inList);
                Assertions.assertEquals(expectedResult, game20481.canMove(), name);
            }
        }

        Stream.of(
                new Testcase("1" , asList(1,2,3,4), false),
                new Testcase("2" , asList(null,2,3,4), true),
                new Testcase("3" , asList(null,null,null,null), true),
                new Testcase("4" , asList(2,2,3,4), true),
                new Testcase("5" , asList(2,3,2,3), true)
        ).forEach(Testcase::run);
    }

    @Test
    void move() {

        class Testcase{
            final String name;
            final List<Integer> inList;
            final Direction direction;
            final boolean expectedResult;

            public Testcase(String name, List<Integer> inList, Direction direction, boolean expectedResult) {
                this.name = name;
                this.inList = inList;
                this.direction = direction;
                this.expectedResult = expectedResult;
            }

            void run(){
                Game2048 game20481 = new Game2048(new SquareBoard(2));
                game20481.getGameBoard().fillBoard(inList);
                Assertions.assertEquals(expectedResult, game20481.move(direction), name);
            }
        }

        Stream.of(
                new Testcase("1" , asList(2,2,3,4), Direction.UP, false),
                new Testcase("2" , asList(2,2,3,4), Direction.RIGHT, true),
                new Testcase("3" , asList(2,2,3,4), Direction.LEFT, true),
                new Testcase("4" , asList(2,2,3,4), Direction.DOWN, false),
                new Testcase("5" , asList(null,2,3,4), Direction.UP, true),
                new Testcase("6" , asList(null,2,3,4), Direction.LEFT, true),
                new Testcase("7" , asList(null,2,3,4), Direction.RIGHT, false),
                new Testcase("8" , asList(null,2,3,4), Direction.DOWN, false),
                new Testcase("9" , asList(2,2,4,null), Direction.UP, false),
                new Testcase("10" , asList(2,2,4,null), Direction.RIGHT, true),
                new Testcase("11" , asList(2,2,4,null), Direction.LEFT, true),
                new Testcase("12" , asList(2,2,4,null), Direction.DOWN, true),
                new Testcase("13" , asList(2,3,4,null), Direction.LEFT, false),
                new Testcase("14" , asList(2,3,2,null), Direction.UP, true),
                new Testcase("15" , asList(null,null,null,null), Direction.UP, false),
                new Testcase("16" , asList(null,null,null,null), Direction.DOWN, false),
                new Testcase("17" , asList(null,null,null,null), Direction.LEFT, false),
                new Testcase("18" , asList(null,null,null,null), Direction.RIGHT, false)
        ).forEach(Testcase::run);
    }

    @Test
    void addItem() {
        game2048.addItem();
        int freeSpace = game2048.getGameBoard().availableSpace().size();
        Assertions.assertEquals(3, freeSpace, "Add 1 item in game");
    }

    @Test
    void getGameBoard() {
        Board<Key,Integer> board = new SquareBoard<Integer>(2);
        board.clearBoard();
        Board<Key,Integer> notEqualBoard = new SquareBoard<Integer>(3);
        notEqualBoard.clearBoard();
        Assertions.assertEquals(game2048.getGameBoard(), board, "Equal board test");
        Assertions.assertNotEquals(game2048.getGameBoard(), notEqualBoard, "Non equal board test");
    }

    @Test
    void hasWin() {
        Assertions.assertFalse(game2048.hasWin(), "Non Win");
        game2048.getGameBoard().addItem(new Key(0,0), 2048);
        Assertions.assertTrue(game2048.hasWin(), "Win");
    }

    @Test
    void gameTest() {
        var game = new Game2048();
        Board<Key, String> b2 = new SquareBoard<>(1);
        b2.fillBoard(asList("hello"));
        if (!"hello".equals(b2.getValue(b2.getKey(0 ,0)))) throw new RuntimeException("board not work =(");
        if (!b2.hasValue("hello")) throw new RuntimeException("board not work =(");
        Board<String, Double> b3 = new Board<>(1,1) {
            @Override
            public void clearBoard() {

            }

            @Override
            public List<List<String>> getColumns() {
                return null;
            }

            @Override
            public List<List<String>> getRows() {
                return null;
            }

            @Override public void fillBoard(List<Double> list) {

            }
            @Override public List<String> availableSpace() {
                return null;
            }
            @Override public void addItem(String key, Double value) {

            }
            @Override public String getKey(int i, int j) {
                return null;
            }
            @Override public Double getValue(String key) {
                return null;
            }
            @Override public List<String> getColumn(int j) {
                return null;
            }
            @Override public List<String> getRow(int i) {
                return null;
            }
            @Override public boolean hasValue(Double value) {
                return false;
            }
            @Override public List<Double> getValues(List<String> keys) {
                return null;
            }
        };
        Board<Key, Integer> b = game.getGameBoard();
        if (!b.availableSpace().isEmpty()) throw new RuntimeException("Game board must be empty before initialize");
        b.fillBoard(asList(2,null,null,8, 2,2,8,8, 2,null,2,2, 4,2,4,2048));
        if (!game.hasWin()) throw new RuntimeException("hasWin not work =(");
        game.move(Direction.LEFT);
        if (b.availableSpace().size() != 5) throw new RuntimeException("move must be add item");
        Assertions.assertEquals(b.getValues(b.getRow(0)).subList(0,2), asList(2, 8));
        Assertions.assertEquals(b.getValues(b.getRow(1)).subList(0,2), asList(4, 16));
        Assertions.assertEquals(b.getValues(b.getRow(2)).subList(0,2), asList(4, 2));
        Assertions.assertEquals(b.getValues(b.getRow(3)), asList(4, 2, 4, 2048));
        game.move(Direction.DOWN);
        Assertions.assertEquals(b.getValues(b.getColumn(0)).subList(1,4), asList(2, 4, 8));
        Assertions.assertEquals(b.getValues(b.getColumn(1)).subList(1,4), asList(8, 16, 4));

        game.init();
        if (b.availableSpace().size() != 14) throw new RuntimeException("init must be add 2 item");
        if (!game.canMove()) throw new RuntimeException("canMove not work =(");
        game.addItem();
        if (b.availableSpace().size() != 13) throw new RuntimeException("addItem must be add 1 item");
    }

}