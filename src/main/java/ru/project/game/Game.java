package ru.project.game;

import ru.project.board.Board;

public interface Game {
    void init();

    boolean canMove();

    boolean move(Direction direction);

    void addItem();

    Board getGameBoard();

    boolean hasWin();

}
