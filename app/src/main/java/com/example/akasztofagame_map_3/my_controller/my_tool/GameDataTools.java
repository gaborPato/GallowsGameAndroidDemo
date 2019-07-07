package com.example.akasztofagame_map_3.my_controller.my_tool;

import java.util.ArrayList;
import java.util.List;



public class GameDataTools {
    private GameDataTools() {
    }

    public static String changeFullString(String wordPass) {
        StringBuilder result = new StringBuilder("");

        for (int i = 0; i < wordPass.length(); ++i) {
            if (wordPass.charAt(i) == ' ') {
                result.append(" ");
            } else if (wordPass.charAt(i) == '-') {
                result.append('-');
            } else {
                result.append("*");
            }
        }

        return result.toString();
    }

    public static List<Integer> charVerify(String wordPass, String aCharacter) {

        if (!wordPass.toLowerCase().contains(aCharacter)) {
            return new ArrayList<>();
        } else {
            List<Integer> result = new ArrayList();
            for (int i = 0; i < wordPass.length(); ++i) {
                if (wordPass.toLowerCase().charAt(i) == aCharacter.charAt(0)) {
                    result.add(i);
                }
            }

            return result;
        }
    }
}
