package ru.project.board;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SquareBoard extends Board {

    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public List<List<Key>> getColumns() {
        return IntStream.range(0, getWidth()).mapToObj(this::getColumn).collect(Collectors.toList());
    }

    @Override
    public List<List<Key>> getRows() {
        return IntStream.range(0, getHeight()).mapToObj(this::getRow).collect(Collectors.toList());
    }

    @Override
    public void fillBoard(List<Integer> list) {
        List<Key> availableKeys = availableSpace().stream()
                .sorted(Comparator.comparing(Key::getI).thenComparing(Key::getJ))
                .collect(Collectors.toList());
        for (int i = 0; i < availableKeys.size() && i < list.size(); i++) {
            addItem(availableKeys.get(i), list.get(i));
        }
    }

    @Override
    public List<Key> availableSpace() {
        return board.entrySet().stream()
                .filter(x -> x.getValue() == null)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        return board.keySet().stream()
                .filter(key -> key.getI() == i && key.getJ() == j)
                .findAny().orElse(null);
    }

    @Override
    public Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        return board.keySet().stream()
                .filter(key -> key.getJ() == j)
                .sorted(Comparator.comparing(Key::getI))
                .collect(Collectors.toList());
    }

    @Override
    public List<Key> getRow(int i) {
        return board.keySet().stream()
                .filter(key -> key.getI() == i)
                .sorted(Comparator.comparing(Key::getJ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasValue(Integer value) {
        return board.containsValue(value);
    }

    @Override
    public List<Integer> getValues(List<Key> keys) {
        return keys.stream()
                .map(this::getValue)
                .collect(Collectors.toList());
    }

}
