package ru.project.game;

import ru.project.board.Board;
import ru.project.board.Key;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class Game2048 implements Game {
    private static final int INITIAL_COUNT = 2;
    private final GameHelper helper = new GameHelper();
    private final Board board;
    private final Random random = new Random();

    public Game2048(Board board) {
        this.board = board;
    }

    @Override
    public void init() {
        for (int i = 0; i < INITIAL_COUNT; i++) {
            addItem();
        }
    }

    @Override
    public boolean canMove() {
        if (board.hasValue(null)) {
            return true;
        }
        return Stream.concat(board.getColumns().stream(), board.getRows().stream())
                .anyMatch(this::hasEqualNeighbours);
    }

    @Override
    public boolean move(Direction direction) {
        if (!canMove()) {
            return false;
        }
        switch (direction) {
            case UP: {
                return moveImpl(Axis.VERTICAL, true);
            }
            case DOWN: {
                return moveImpl(Axis.VERTICAL, false);
            }
            case LEFT: {
                return moveImpl(Axis.HORIZONTAL, true);
            }
            case RIGHT: {
                return moveImpl(Axis.HORIZONTAL, false);
            }
        }
        return false;
    }

    @Override
    public void addItem() {
        List<Key> keys = board.availableSpace();
        Collections.shuffle(keys);
        if (random.nextFloat() > 0.9) {
            board.addItem(keys.get(0), 4);
        } else {
            board.addItem(keys.get(0), 2);
        }
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return this.board.hasValue(2048);
    }

    private boolean hasEqualNeighbours(List<Key> list) {
        for (int i = 1; i < list.size(); i++) {
            if (board.getValue(list.get(i)).equals(board.getValue(list.get(i - 1)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Позволяет двигать элементы по доске в зависимости от направления. Движение определяется осью
     * (вертикальное, горизонтальное) и направлением (прямое, обратное).
     * Например, движение влево - это прямое движение по горизонтальной оси, движение вверх - это прямое движение по
     * вертикальной оси.
     *
     * @param axis      ось
     * @param isForward направление (true - прямое, false - обратное)
     * @return произошло движение (true - произошло, false - нет)
     */
    private boolean moveImpl(Axis axis, boolean isForward) {
        boolean result = false;
        var firstDimensionSize = axis.equals(Axis.VERTICAL) ? board.getWidth() : board.getHeight();
        for (int i = 0; i < firstDimensionSize; i++) {
            List<Key> keys = axis.equals(Axis.VERTICAL) ? board.getColumn(i) : board.getRow(i);
            if (!isForward) {
                Collections.reverse(keys);
            }
            List<Integer> newElements = helper.moveAndMergeEqual(board.getValues(keys));
            if (!isForward) {
                Collections.reverse(newElements);
            }
            for (int j = 0; j < newElements.size(); j++) {
                var key = axis.equals(Axis.VERTICAL) ? new Key(j, i) : new Key(i, j);
                result = result || !Objects.equals(board.getValue(key), newElements.get(j));
                board.addItem(key, newElements.get(j));
            }
        }
        return result;
    }
}
