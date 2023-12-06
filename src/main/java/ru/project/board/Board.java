package ru.project.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public abstract class Board<K, V> {
    protected int width;
    protected int height;
    protected Map<K, V> board = new HashMap<>();

    protected Board(int width, int height) {
        this.width = width;
        this.height = height;
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
        Board<?, ?> board1 = (Board<?, ?>) o;
        return width == board1.width && height == board1.height && board.equals(board1.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, board);
    }

    public abstract void clearBoard();
    public abstract List<List<K>> getColumns();

    public abstract List<List<K>> getRows();

    public abstract void fillBoard(List<V> list);

    public abstract List<K> availableSpace();

    public abstract void addItem(K key, V value);

    public abstract K getKey(int i, int j);

    public abstract V getValue(K key);

    public abstract List<K> getColumn(int j);

    public abstract List<K> getRow(int i);

    public abstract boolean hasValue(V value);

    public abstract List<V> getValues(List<K> keys);

}
