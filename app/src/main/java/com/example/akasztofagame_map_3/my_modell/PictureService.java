package com.example.akasztofagame_map_3.my_modell;

import android.content.Context;

import com.example.akasztofagame_map_3.R;
import com.example.akasztofagame_map_3.my_view.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PictureService {


    private PictureService() {
    }

    public static List<Integer> getPictureList() {


        List<Integer> resultList = new ArrayList<>();
        resultList.add(R.drawable.p01);
        resultList.add(R.drawable.p02);
        resultList.add(R.drawable.p03);
        resultList.add(R.drawable.p04);
        resultList.add(R.drawable.p05);
        resultList.add(R.drawable.p06);
        resultList.add(R.drawable.p07);
        resultList.add(R.drawable.p08);
        resultList.add(R.drawable.p09);
        resultList.add(R.drawable.p10);
        resultList.add(R.drawable.p11);

        return resultList;
    }


}
