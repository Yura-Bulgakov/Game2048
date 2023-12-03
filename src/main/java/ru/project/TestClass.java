package ru.project;

import ru.project.board.Board;
import ru.project.board.SquareBoard;
import ru.project.game.Game;
import ru.project.game.Game2048;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048(board);
        System.out.println(game2048.canMove());
    }
}
