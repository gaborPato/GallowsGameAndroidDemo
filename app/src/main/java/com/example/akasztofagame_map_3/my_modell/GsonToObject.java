package com.example.akasztofagame_map_3.my_modell;

import android.content.res.AssetManager;

import com.example.akasztofagame_map_3.my_controller.state.different_state_classes.AnimalWords;
import com.example.akasztofagame_map_3.my_controller.state.different_state_classes.FlowerWords;
import com.example.akasztofagame_map_3.my_controller.state.different_state_classes.FruitWords;
import com.example.akasztofagame_map_3.my_controller.state.intfaces.WordsState;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import controller.constans.MY_STRINGS;

public class GsonToObject {

    private static String jsonCommand;
    private static final Gson GSON = new Gson();

    private GsonToObject() {
    }


    public static WordsState readGsonfile(String wordsGroup, AssetManager am) throws IOException {


        Class recentClass = wordsGroup.equals(MY_STRINGS.wordsGroupAnimal) ? AnimalWords.class
                : wordsGroup.equals(MY_STRINGS.wordsGroupFruit) ? FruitWords.class
                : wordsGroup.equals(MY_STRINGS.wordsGroupFlower) ? FlowerWords.class
                : null;

        InputStream jsonFileIs = am.open("my_words.json");
        byte[] buffer = new byte[jsonFileIs.available()];
        jsonFileIs.read(buffer);
        jsonFileIs.close();
        jsonCommand = new String(buffer, "UTF-8");
        WordsState ws = (WordsState) GSON.fromJson(jsonCommand, recentClass);
        return ws;

    }
}
