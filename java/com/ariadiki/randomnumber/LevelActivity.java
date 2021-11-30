package com.ariadiki.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends AppCompatActivity {
    Button easy,medium,hard,expert,specific;
    String p,namep1,namep2,namep3;
    MediaPlayer soundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);
        expert = findViewById(R.id.expert);
        specific= findViewById(R.id.specific);
        soundButton = MediaPlayer.create(this,R.raw.button_click);

        p = getIntent().getExtras().getString("player");
        namep1 = getIntent().getExtras().getString("name1");
        namep2 = getIntent().getExtras().getString("name2");
        namep3 = getIntent().getExtras().getString("name3");

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("level","easy");
                intent.putExtra("player",p);
                intent.putExtra("name1",namep1);
                intent.putExtra("name2",namep2);
                intent.putExtra("name3",namep3);
                startActivity(intent);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("level","medium");
                intent.putExtra("player",p);
                intent.putExtra("name1",namep1);
                intent.putExtra("name2",namep2);
                intent.putExtra("name3",namep3);
                startActivity(intent);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("level","hard");
                intent.putExtra("player",p);
                intent.putExtra("name1",namep1);
                intent.putExtra("name2",namep2);
                intent.putExtra("name3",namep3);
                startActivity(intent);
            }
        });

        expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("level","expert");
                intent.putExtra("player",p);
                intent.putExtra("name1",namep1);
                intent.putExtra("name2",namep2);
                intent.putExtra("name3",namep3);
                startActivity(intent);
            }
        });

        specific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),SpecificNum.class);
                intent.putExtra("level","specific");
                intent.putExtra("player",p);
                intent.putExtra("name1",namep1);
                intent.putExtra("name2",namep2);
                intent.putExtra("name3",namep3);
                startActivity(intent);
            }
        });
    }
}