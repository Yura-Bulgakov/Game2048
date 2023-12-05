package ru.project.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public abstract class Board {
    protected int width;
    protected int height;
    protected Map<Key, Integer> board = new HashMap<>();

    protected Board(int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                addItem(new Key(i, j), null);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return width == board1.width && height == board1.height && Objects.equals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, board);
    }

    public abstract void fillBoard(List<Integer> list);

    public abstract List<Key> availableSpace();

    public abstract void addItem(Key key, Integer value);

    public abstract Key getKey(int i, int j);

    public abstract Integer getValue(Key key);

    public abstract List<Key> getColumn(int j);

    public abstract List<Key> getRow(int i);

    public abstract boolean hasValue(Integer value);

    public abstract List<Integer> getValues(List<Key> keys);

}
