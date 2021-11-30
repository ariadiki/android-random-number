package com.ariadiki.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class PlayersActivity extends AppCompatActivity {
    Button one_player,two_players,three_players;
    MediaPlayer soundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        one_player = findViewById(R.id.oneplayer);
        two_players = findViewById(R.id.twoplayers);
        three_players = findViewById(R.id.threeplayers);
        soundButton = MediaPlayer.create(this,R.raw.button_click);
        one_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),NameOfPlayers.class);
                intent.putExtra("player","p1");
                startActivity(intent);
            }
        });

        two_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),NameOfPlayers.class);
                intent.putExtra("player","p2");
                startActivity(intent);
            }
        });

        three_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),NameOfPlayers.class);
                intent.putExtra("player","p3");
                startActivity(intent);
            }
        });
    }
}