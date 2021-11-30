package com.ariadiki.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeaderBoard extends AppCompatActivity {
    String firstPlayer,secondPlayer,thirdPlayer;
    int[] images;
    String[] text_player;
    String[] text_tries;
    private ListView listView;
    Button btn_menu;
    long backPressedTime;
    MediaPlayer soundButton;
    int sizePlayers;
    String ftries,stries,htries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        listView = findViewById(R.id.lst_board);
        btn_menu = findViewById(R.id.btn_menu);
        soundButton = MediaPlayer.create(this,R.raw.button_click);

        //retrieve the size
            sizePlayers = getIntent().getExtras().getInt("size");
        //retrieve the players
            firstPlayer = getIntent().getExtras().getString("first");
            secondPlayer = getIntent().getExtras().getString("second");
            thirdPlayer = getIntent().getExtras().getString("third");
        //retrieve tries of players
            ftries = getIntent().getExtras().getString("ftries");
            stries = getIntent().getExtras().getString("stries");
            htries = getIntent().getExtras().getString("htries");


        //display the medals compared to the size
        switch (sizePlayers)
        {
            case 1:
                images = new int[]{R.drawable.first_medal};
                text_player = new String[]{firstPlayer};
                text_tries = new String[]{"tries: "+ftries};
                break;

            case 2:
                images = new int[]{R.drawable.first_medal,R.drawable.second_medal};
                text_player = new String[]{firstPlayer,secondPlayer};
                text_tries = new String[]{"tries: "+ftries, "tries: "+stries};
                break;

            case 3:
                images = new int[]{R.drawable.first_medal,R.drawable.second_medal,R.drawable.third_medal};
                text_player = new String[]{firstPlayer,secondPlayer,thirdPlayer};
                text_tries = new String[]{"tries: "+ftries, "tries: "+stries, "tries: "+htries};

                break;
        }
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),images,text_player,text_tries);
        listView.setAdapter(listAdapter);

    }

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000>System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }
        else{
            Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    public void menu_click(View view) {
        soundButton.start();
        finish();
        Intent intent = new Intent(getApplicationContext(),MainMenu.class);
        startActivity(intent);
    }
}