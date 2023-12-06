package ru.project.game;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        var result = new ArrayList<Integer>(list.size());
        boolean canMerge = false;
        for (var element : list) {
            if (element == null) {
                continue;
            }
            if (result.isEmpty() || !element.equals(result.get(result.size() - 1)) || !canMerge) {
                result.add(element);
                canMerge = true;
            } else {
                result.set(result.size() - 1, element * 2);
                canMerge = false;
            }
        }
        while (result.size() < list.size()) {
            result.add(null);
        }
        return result;
    }
}
