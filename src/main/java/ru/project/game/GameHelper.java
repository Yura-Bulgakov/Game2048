package ru.project.game;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list){
        List<Integer> result = new ArrayList<>(list.size());
        boolean canMerge = false;
        for (Integer integer : list) {
            if (integer == null){
                continue;
            }
            if (result.isEmpty() || !integer.equals(result.get(result.size() - 1)) || !canMerge) {
                result.add(integer);
                canMerge = true;
            } else{
                result.set(result.size() - 1, integer * 2);
                canMerge = false;
            }
        }
        while (result.size() < list.size()){
            result.add(null);
        }
        return result;
    }
}
