package ru.project.game;

import ru.project.board.Board;

public interface Game<K, V> {
    void init();

    boolean canMove();

    boolean move(Direction direction);

    void addItem();

    Board<K, V> getGameBoard();

    boolean hasWin();

}
