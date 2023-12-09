package ru.project.game;

import ru.project.board.Board;
import ru.project.board.NotEnoughSpace;

public interface Game<K, V> {
    void init();

    boolean canMove();

    boolean move(Direction direction);

    void addItem() throws NotEnoughSpace;

    Board<K, V> getGameBoard();

    boolean hasWin();

}
