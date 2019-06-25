package com.example.akasztofagame_map_3.my_controller.state.different_state_classes;


import com.example.akasztofagame_map_3.R;
import com.example.akasztofagame_map_3.my_controller.state.different_state_classes.mapFactory.MapFactory;
import com.example.akasztofagame_map_3.my_controller.state.intfaces.WordsState;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import myMapTool.MapTools;


public class FruitWords implements WordsState {

    @SerializedName("fruit")
    private List<String> fruits;
    private List<Integer> imageResCode;
    private Map<String, Integer> wordsAndImageMap;


    public FruitWords(List<String> fruit) {
        this.fruits = fruit;

    }

    @Override
    public Map.Entry<Object, Object> changeRandomEntry() {
        return MapTools.getEntry(wordsAndImageMap, new Random().nextInt(wordsAndImageMap.size()));

    }

    @Override
    public void initElements() {

        imageResCode = new ArrayList<>();
        imageResCode.add(R.drawable.apple1);
        imageResCode.add(R.drawable.plum1);

        wordsAndImageMap = MapFactory.createMap(fruits, imageResCode);
    }
}
