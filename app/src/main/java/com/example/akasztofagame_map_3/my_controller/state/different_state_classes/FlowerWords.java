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


public class FlowerWords implements WordsState {

    @SerializedName("flower")
    private List<String> flowers;
    private List<Integer> imageResCode;
    private Map<String, Integer> wordsAndImageMap;


    public FlowerWords(List<String> flower) {
        this.flowers = flower;
    }

    @Override
    public Map.Entry<Object, Object> changeRandomEntry() {
        return MapTools.getEntry(wordsAndImageMap, new Random().nextInt(wordsAndImageMap.size()));

    }

    @Override
    public void initElements() {


        imageResCode = new ArrayList<>();
        imageResCode.add(R.drawable.poppy1);
        imageResCode.add(R.drawable.tulip1);
        wordsAndImageMap = MapFactory.createMap(flowers, imageResCode);

    }


}