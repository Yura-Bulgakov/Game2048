package ru.project.board;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SquareBoard extends Board {

    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<Integer> list) {
        List<Key> availableKeys = availableSpace();
        Collections.shuffle(availableKeys);
        for (int i = 0; i < availableKeys.size() && i < list.size(); i++) {
            addItem(availableKeys.get(i), list.get(i));
        }
    }

    @Override
    public List<Key> availableSpace() {
        return this.board.entrySet().stream()
                .filter(x -> x.getValue() == null)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Key getKey(int i, int j) {
        return this.board.keySet().stream()
                .filter(key -> key.getI() == i && key.getJ() == j)
                .findAny().orElseThrow();
    }

    @Override
    public Integer getValue(Key key) {
        return this.board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        return this.board.keySet().stream()
                .filter(key -> key.getJ() == j)
                .collect(Collectors.toList());
    }

    @Override
    public List<Key> getRow(int i) {
        return this.board.keySet().stream()
                .filter(key -> key.getI() == i)
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
