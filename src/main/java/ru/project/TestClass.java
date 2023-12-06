package ru.project;

import ru.project.board.Board;
import ru.project.board.SquareBoard;
import ru.project.game.Game;
import ru.project.game.Game2048;

public class TestClass {
    public static void main(String[] args) {
        var board = new SquareBoard<Integer>(4);
        Game game2048 = new Game2048();
        System.out.println(game2048.canMove());
    }
}
