package ru.project.game;

import ru.project.board.Board;
import ru.project.board.Key;
import ru.project.board.NotEnoughSpace;
import ru.project.board.SquareBoard;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class Game2048 implements Game<Key, Integer> {
    public static final int GAME_SIZE = 4;
    private Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    private static final int INITIAL_COUNT = 2;
    private final GameHelper helper = new GameHelper();
    private final Random random = new Random();

    public Game2048(Board<Key, Integer> board) {
        this.board = board;
    }

    public Game2048() {
    }

    @Override
    public void init() {
        board.clearBoard();
        for (int i = 0; i < INITIAL_COUNT; i++) {
            try {
                addItem();
            } catch (NotEnoughSpace notEnoughSpace) {
                System.out.println(notEnoughSpace.getMessage());
                notEnoughSpace.printStackTrace();
            }
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
        boolean result = false;
        switch (direction) {
            case UP: {
                result = move(Axis.VERTICAL, true);
                break;
            }
            case DOWN: {
                result = move(Axis.VERTICAL, false);
                break;
            }
            case LEFT: {
                result = move(Axis.HORIZONTAL, true);
                break;
            }
            case RIGHT: {
                result = move(Axis.HORIZONTAL, false);
                break;
            }
        }
        if (result) {
            try {
                addItem();
            } catch (NotEnoughSpace notEnoughSpace) {
                System.out.println(notEnoughSpace.getMessage());
                notEnoughSpace.printStackTrace();
            }

        }
        return result;
    }

    @Override
    public void addItem() throws NotEnoughSpace {
        var keys = board.availableSpace();
        if (keys.size() < 1) {
            throw new NotEnoughSpace("На поле нет свободного места!");
        }
        Collections.shuffle(keys);
        if (random.nextFloat() > 0.9) {
            board.addItem(keys.get(0), 4);
        } else {
            board.addItem(keys.get(0), 2);
        }
    }


    @Override
    public Board<Key,Integer> getGameBoard() {
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
    private boolean move(Axis axis, boolean isForward) {
        boolean result = false;
        var firstDimensionSize = axis.equals(Axis.VERTICAL) ? board.getWidth() : board.getHeight();
        for (int i = 0; i < firstDimensionSize; i++) {
            var keys = axis.equals(Axis.VERTICAL) ? board.getColumn(i) : board.getRow(i);
            if (!isForward) {
                Collections.reverse(keys);
            }
            var newElements = helper.moveAndMergeEqual(board.getValues(keys));
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
