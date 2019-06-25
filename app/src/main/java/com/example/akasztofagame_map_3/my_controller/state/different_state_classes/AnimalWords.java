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


public class AnimalWords implements WordsState {


    @SerializedName("animal")
    private List<String> animals;
    private List<Integer> imageResCode;
    private Map<String, Integer> wordsAndImageMap;


//

    public AnimalWords(List<String> animal) {
        animals = animal;


    }

    public void initElements() {

        imageResCode = new ArrayList<>();
        imageResCode.add(R.drawable.dog1);
        imageResCode.add(R.drawable.cat1);
        imageResCode.add(R.drawable.mouse1);

        wordsAndImageMap = MapFactory.createMap(animals, imageResCode);

    }


    @Override
    public Map.Entry<Object, Object> changeRandomEntry() {

        return MapTools.getEntry(wordsAndImageMap, new Random().nextInt(wordsAndImageMap.size()));


    }
}
