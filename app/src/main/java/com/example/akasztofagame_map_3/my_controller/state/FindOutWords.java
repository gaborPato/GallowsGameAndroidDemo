package com.example.akasztofagame_map_3.my_controller.state;

import android.content.res.AssetManager;

import com.example.akasztofagame_map_3.my_controller.state.intfaces.WordsState;
import com.example.akasztofagame_map_3.my_modell.GsonToObject;

import java.io.IOException;
import java.util.Map;




public class FindOutWords {


    private Map.Entry<Object, Object> entry;
    private WordsState wordsState;
    private Map.Entry<Object, Object> lastEntry;

    public FindOutWords(Map.Entry<Object, Object> lastEntry, String wordGroup, AssetManager am) throws IOException {
        this.lastEntry = lastEntry;
        setWordGroup(wordGroup, am);
        generateNewEntry();
    }

    public Map.Entry<Object, Object> getEntry() {
        return entry;
    }

    public void generateNewEntry() {

        wordsState.initElements();


        while ((entry = wordsState.changeRandomEntry()).equals(lastEntry)) ;


    }


    private void setWordGroup(String wordGroup, AssetManager am) throws IOException {
        wordsState = GsonToObject.readGsonfile(wordGroup, am);


    }


}
