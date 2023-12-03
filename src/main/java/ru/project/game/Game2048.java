package ru.project.game;

import ru.project.board.Board;
import ru.project.board.Key;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Game2048 implements Game {
    private final GameHelper helper = new GameHelper();
    private final Board board;
    private final Random random = new Random();

    public Game2048(Board board) {
        this.board = board;
    }

    @Override
    public void init() {
        for (int i = 0; i < 2; i++) {
            addItem();
        }
    }

    @Override
    public boolean canMove() {
        if (board.hasValue(null)) {
            return true;
        }
        for (int j = 0; j < board.getWidth(); j++) {
            List<Key> columnKeys = board.getColumn(j);
            if (hasEqualNeighbours(columnKeys)) {
                return true;
            }
        }
        for (int i = 0; i < board.getHeight(); i++) {
            List<Key> rowKeys = board.getRow(i);
            if (hasEqualNeighbours(rowKeys)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        if (!canMove()) {
            return false;
        }
        switch (direction) {
            case UP: {
                return moveUp();
            }
            case DOWN: {
                return moveDown();
            }
            case LEFT: {
                return moveLeft();
            }
            case RIGHT: {
                return moveRight();
            }
        }
        return false;
    }

    @Override
    public void addItem() {
        List<Key> keys = board.availableSpace();
        Collections.shuffle(keys);
        if (random.nextFloat() > 0.9) {
            board.addItem(keys.get(1), 2);
        } else {
            board.addItem(keys.get(1), 4);
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

    private boolean moveUp() {
        boolean result = false;
        for (int j = 0; j < board.getWidth(); j++) {
            List<Integer> newElements = helper.moveAndMergeEqual(board.getValues(board.getColumn(j)));
            for (int i = 0; i < newElements.size(); i++) {
                Key key = new Key(i, j);
                result = result || !Objects.equals(board.getValue(key), newElements.get(i));
                board.addItem(new Key(i, j), newElements.get(i));
            }
        }
        return result;
    }

    private boolean moveDown() {
        boolean result = false;
        for (int j = 0; j < board.getWidth(); j++) {
            List<Key> keys = board.getColumn(j);
            Collections.reverse(keys);
            List<Integer> newElements = helper.moveAndMergeEqual(board.getValues(keys));
            Collections.reverse(newElements);
            for (int i = 0; i < newElements.size(); i++) {
                Key key = new Key(i, j);
                result = result || !Objects.equals(board.getValue(key), newElements.get(i));
                board.addItem(new Key(i, j), newElements.get(i));
            }
        }
        return result;
    }

    private boolean moveLeft() {
        boolean result = false;
        for (int i = 0; i < board.getHeight(); i++) {
            List<Integer> newElements = helper.moveAndMergeEqual(board.getValues(board.getRow(i)));
            for (int j = 0; j < newElements.size(); j++) {
                Key key = new Key(i, j);
                result = result || !Objects.equals(board.getValue(key), newElements.get(j));
                board.addItem(key, newElements.get(j));
            }
        }
        return result;
    }

    private boolean moveRight() {
        boolean result = false;
        for (int i = 0; i < board.getHeight(); i++) {
            List<Key> keys = board.getRow(i);
            Collections.reverse(keys);
            List<Integer> newElements = helper.moveAndMergeEqual(board.getValues(keys));
            Collections.reverse(newElements);
            for (int j = 0; j < newElements.size(); j++) {
                Key key = new Key(i, j);
                result = result || !Objects.equals(board.getValue(key), newElements.get(j));
                board.addItem(key, newElements.get(j));
            }
        }
        return result;
    }
}
