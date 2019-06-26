package com.example.akasztofagame_map_3.my_view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.akasztofagame_map_3.R;
import com.example.akasztofagame_map_3.my_controller.my_tool.GameDataTools;
import com.example.akasztofagame_map_3.my_controller.state.FindOutWords;
import com.example.akasztofagame_map_3.my_modell.PictureService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.constans.MY_COLOR;
import controller.constans.MY_STRINGS;


public class MainActivity extends AppCompatActivity {

    private FindOutWords fouw;
    private static AssetManager ASSET_MANAGER;
    private TextView foundWTextView, usedCharTV;
    private EditText inputCharEditText;
    private ImageView imageView;

    private List<Integer> pictureList;
    private int iconCounter;
    private Map.Entry<Object, Object> lastEntry = null;
    private String tippChar;
    private AppCompatSpinner wordsGroupSpinner;
    private LinearLayout mainLOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLOut = findViewById(R.id.main_layout);
        mainLOut.setBackgroundColor(MY_COLOR.DEF_BACK_COLOR);



        pictureList = PictureService.getPictureList();
        ASSET_MANAGER = getAssets();
        createSpinner();
        createFOUW();

        foundWTextView = findViewById(R.id.find_out_text_view);
        inputCharEditText = findViewById(R.id.input_char_editText);
        usedCharTV = findViewById(R.id.used_char_text_view);
        imageView = findViewById(R.id.pictures_view);

        imageViewsetDefault();


        inputCharEditTextInit();
        ftwSetStars();
        usedCharTVDefault();


    }

    private void usedCharTVDefault() {
        usedCharTV.setText(MY_STRINGS.START_USED_CHARS_TEXT);
    }

    private void imageViewsetDefault() {

        imageView.setImageResource(0);
        imageView.setBackgroundColor(Color.WHITE);
        iconCounter = 0;
    }

    private void inputCharEditTextInit() {
        inputCharEditText.setFocusable(true);
        inputCharEditText.setFocusableInTouchMode(true);
        inputCharEditText.requestFocus();


        inputCharEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//


                if (s.length() == 1) {



                    tippChar = inputCharEditText.getText().toString().toLowerCase();

                    if (!verifyAllowedChar(tippChar)){
                        inputCharEditText.setText("");
                        return;
                    }

                    if (verifyDuplicatedTipp(tippChar, usedCharTV.getText())) {
                        inputCharEditText.setText("");
                        return;
                    }
                    List<Integer> resultList = (List<Integer>) GameDataTools.charVerify(fouw.getEntry().getKey().toString(), tippChar);
                    inputCharEditText.setText("");
                    usedCharTV.setText(usedCharTV.getText() + tippChar + ",");

                    if (resultList == null) {
                        badAnswerOperation();
                        System.out.println("bad answer" + resultList);

                    } else {
                        rightAnswerOperation(resultList);
                        System.out.println("right answer" + resultList);

                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    private boolean verifyAllowedChar(String tippChar) {


        Pattern pattern= Pattern.compile("^[a-zíéáűőúöüó]*$");
        return   pattern.matcher(tippChar).matches();



    }

    private boolean verifyDuplicatedTipp(String tippChar, CharSequence usedCharTV) {

        String substring = usedCharTV.toString().substring(MY_STRINGS.START_USED_CHARS_TEXT.length());

        return substring.contains(tippChar);

    }

    private void createFOUW() {
        try {
            fouw = new FindOutWords(lastEntry, wordsGroupSpinner.getSelectedItem().toString(), ASSET_MANAGER);
            lastEntry = fouw.getEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ftwSetStars() {
        foundWTextView.setText(GameDataTools.changeFullString(fouw.getEntry().getKey().toString()));
    }

    private void rightAnswerOperation(List<Integer> resultList) {

        StringBuffer sb = new StringBuffer(foundWTextView.getText().toString());
        for (Integer integer : resultList) {
            sb.replace(integer, integer + 1, Character.toString(((String) fouw.getEntry().getKey()).charAt(integer)));

        }
        foundWTextView.setText(sb.toString());
        if (!foundWTextView.getText().toString().contains("*")) {
            final AlertDialog ad = RoundEndAnswerDialogKt.getRoundEndDialog(true, fouw.getEntry(), MainActivity.this);

            mainLOut.setBackgroundColor(MY_COLOR.WIN_BACK_COLOR);
            PlaySound.playmp3(this, true);
            ad.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    newGameProtocol();
                }
            });

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    ad.show();
                }
            }, 1300);


        }
    }


    private void badAnswerOperation() {
        if (iconCounter == pictureList.size() - 1) {
            imageView.setImageResource(pictureList.get(pictureList.size() - 1));
            final AlertDialog ad = RoundEndAnswerDialogKt.getRoundEndDialog(false, fouw.getEntry(), MainActivity.this);

            ad.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                    newGameProtocol();
                }


            });
            mainLOut.setBackgroundColor(MY_COLOR.LOSE_BACK_COLOR);
            PlaySound.playmp3(MainActivity.this, false);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    ad.show();
                }
            }, 1300);


        } else {
            imageView.setImageResource(pictureList.get(iconCounter));
            iconCounter++;
        }


    }


    private void newGameProtocol() {

        mainLOut.setBackgroundColor(MY_COLOR.DEF_BACK_COLOR);
        imageViewsetDefault();
        usedCharTVDefault();
        createFOUW();
        ftwSetStars();
    }

    private void createSpinner() {

        String[] items = new String[]{MY_STRINGS.wordsGroupAnimal, MY_STRINGS.wordsGroupFlower, MY_STRINGS.wordsGroupFruit};

        wordsGroupSpinner = findViewById(R.id.words_group_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        wordsGroupSpinner.setAdapter(arrayAdapter);
        final int[] recentPosition = {wordsGroupSpinner.getSelectedItemPosition()};
        wordsGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != recentPosition[0]) {
                    recentPosition[0] = position;
                    newGameProtocol();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}

