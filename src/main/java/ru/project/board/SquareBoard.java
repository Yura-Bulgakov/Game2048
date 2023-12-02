package ru.project.board;

import java.util.*;
import java.util.stream.Collectors;

public class SquareBoard extends Board {
    private final int size;

    public SquareBoard(int size) {
        super(size, size);
        this.size = size;
    }

    @Override
    public void fillBoard(List<Integer> list) {
        Random random = new Random();
        for (Integer element : list) {
            this.board.put(new Key(
                            random.nextInt(this.size - 1), this.size - 1),
                    element);
        }
    }

    @Override
    public List<Key> availableSpace() {
        return this.board.keySet().stream()
                .filter(Objects::isNull)
                .collect(Collectors.toList());
    }

    @Override
    public void addItem(Key key, Integer value) {
        this.board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        return this.board.keySet().stream()
                .filter(key -> key.equals(new Key(i, j)))
                .findAny().orElseThrow();
    }

    @Override
    public Integer getValue(Key key) {
        return this.board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        return this.board.keySet().stream()
                .filter(column -> column.getJ() == j)
                .collect(Collectors.toList());
    }

    @Override
    public List<Key> getRow(int i) {
        return this.board.keySet().stream()
                .filter(row -> row.getI() == i)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasValue(Integer value) {
        return this.board.containsValue(value);
    }

    @Override
    public List<Integer> getValues(List<Key> keys) {
        return new ArrayList<Integer>(this.board.values());
    }

}
