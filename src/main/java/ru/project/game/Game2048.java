package ru.project.game;

import ru.project.board.Board;

import java.util.Random;

public class Game2048 implements Game{
    private final GameHelper helper = new GameHelper();
    private final Board board;
    private final Random random = new Random();

    public Game2048(Board board) {
        this.board = board;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        return false;
    }

    @Override
    public void addItem() {

    }

    @Override
    public Board getGameBoard() {
        return null;
    }

    @Override
    public boolean hasWin() {
        return false;
    }
}
