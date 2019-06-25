package com.example.akasztofagame_map_3.my_controller.state.different_state_classes.mapFactory;

import java.util.LinkedHashMap;
import java.util.List;

public class MapFactory {
    private MapFactory() {
    }


    public static LinkedHashMap<String, Integer> createMap(List<String> wordsList, List<Integer> imageResCode) {


        LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < imageResCode.size(); i++) {

            result.put(wordsList.get(i), imageResCode.get(i));
        }

        return result;
    }



   /* public static Map.Entry<String, Integer> getRandomEntry(Map<String, Integer> wordsAndImageMap) {


        Set<Map.Entry<String, Integer>> entriSet = wordsAndImageMap.entrySet();
        ArrayList<Map.Entry<String, Integer>> entryArrayList = new ArrayList<>(entriSet);

        return entryArrayList.get(new Random().nextInt(entryArrayList.size()));

    }*/
}
