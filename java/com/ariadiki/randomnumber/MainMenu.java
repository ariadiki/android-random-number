package com.ariadiki.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    Button play,info,share,exit;
    long backPressedTime;
    MediaPlayer soundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        play = findViewById(R.id.play);
        info = findViewById(R.id.info);
        share = findViewById(R.id.share);
        exit = findViewById(R.id.exit);
        soundButton = MediaPlayer.create(this,R.raw.button_click);

        //play button
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(MainMenu.this,PlayersActivity.class);
                MainMenu.this.startActivity(intent);
            }
        });

        //exit button
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finishAffinity();
            }
        });

        //info button
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                Intent intent = new Intent(MainMenu.this,Info.class);
                startActivity(intent);
            }
        });

        //share button
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                shareintent.putExtra(Intent.EXTRA_SUBJECT,"Download Now !");
                shareintent.putExtra(Intent.EXTRA_TEXT,"Guess Number App created by Ariadiki");
                Intent.createChooser(shareintent,"Share using");
                startActivity(shareintent);
            }
        });
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
}