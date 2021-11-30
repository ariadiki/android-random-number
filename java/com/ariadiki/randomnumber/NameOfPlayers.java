package com.ariadiki.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NameOfPlayers extends AppCompatActivity {

    ImageView img1,img2,img3;
    EditText edit1,edit2,edit3;
    Button btn_save;
    String p,namep1,namep2,namep3;
    MediaPlayer soundButton;
    long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_of_players);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit3 = (EditText) findViewById(R.id.edit3);

        soundButton = MediaPlayer.create(this,R.raw.button_click);

        btn_save = (Button) findViewById(R.id.btn_save);

        //recover the number of players
        p=String.valueOf(getIntent().getExtras().getString("player"));

        switch(p)
        {
            case "p1":
                //design of one player
                edit1.setVisibility(View.INVISIBLE);
                edit3.setVisibility(View.INVISIBLE);
                edit2.setHint("Player 1 enter your name");
                img2.setImageResource(R.drawable.playero);
                break;

            case "p2":
                //design of two players
                edit3.setVisibility(View.INVISIBLE);
                img1.setImageResource(R.drawable.playero);
                img2.setImageResource(R.drawable.playert);
                break;

            case "p3":
                //design of three players
                img1.setImageResource(R.drawable.playero);
                img2.setImageResource(R.drawable.playert);
                img3.setImageResource(R.drawable.playerth);
                break;
        }
    }
    public void onBackPressed()
    {
        if(backPressedTime+2000>System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }
        else{
            Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    public void save_click(View view) {
        soundButton.start();
        switch(p)
        {
            case "p1":
                if(TextUtils.isEmpty(edit2.getText()))
                    Toast.makeText(NameOfPlayers.this, "Enter your name please !", Toast.LENGTH_SHORT).show();
                else{
                    if(edit2.getText().toString().trim().length()>2) {
                        namep1 = edit2.getText().toString();
                        finish();
                        Intent intent = new Intent(NameOfPlayers.this, LevelActivity.class);
                        intent.putExtra("name1", namep1);
                        intent.putExtra("player", p);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(this, "the name must be more than two letters", Toast.LENGTH_LONG).show();

                }
                break;

            case "p2":
                if(TextUtils.isEmpty(edit1.getText()) || TextUtils.isEmpty(edit2.getText()))
                {
                    if(TextUtils.isEmpty(edit1.getText()))
                        Toast.makeText(NameOfPlayers.this, "Player 1 enter your name please !", Toast.LENGTH_SHORT).show();
                    if(TextUtils.isEmpty(edit2.getText()))
                        Toast.makeText(NameOfPlayers.this, "Player 2 enter your name please !", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(edit1.getText().toString().trim().length()>2 && edit2.getText().toString().trim().length()>2) {
                        if (edit1.getText().toString().equals(edit2.getText().toString()))
                            Toast.makeText(NameOfPlayers.this, "don't enter the same name", Toast.LENGTH_SHORT).show();
                        else {
                            finish();
                            namep1 = edit1.getText().toString();
                            namep2 = edit2.getText().toString();
                            Intent intent = new Intent(NameOfPlayers.this, LevelActivity.class);
                            intent.putExtra("name1", namep1);
                            intent.putExtra("name2", namep2);
                            intent.putExtra("player", p);
                            startActivity(intent);
                        }
                    }
                    else
                        Toast.makeText(this, "the name must be more than two letters", Toast.LENGTH_LONG).show();
                }
                break;

            case "p3":
                if(TextUtils.isEmpty(edit1.getText()) || TextUtils.isEmpty(edit2.getText()) || TextUtils.isEmpty(edit3.getText()))
                {
                    if(TextUtils.isEmpty(edit1.getText()))
                        Toast.makeText(NameOfPlayers.this, "Player 1 enter your name please !", Toast.LENGTH_SHORT).show();
                    if(TextUtils.isEmpty(edit2.getText()))
                        Toast.makeText(NameOfPlayers.this, "Player 2 enter your name please !", Toast.LENGTH_SHORT).show();
                    if(TextUtils.isEmpty(edit3.getText()))
                        Toast.makeText(NameOfPlayers.this, "Player 3 enter your name please !", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(edit1.getText().toString().trim().length()>2 && edit2.getText().toString().trim().length()>2 && edit3.getText().toString().trim().length()>2) {
                        if (edit1.getText().toString().equals(edit2.getText().toString()) || edit1.getText().toString().equals(edit3.getText().toString()) || edit2.getText().toString().equals(edit3.getText().toString()))
                            Toast.makeText(NameOfPlayers.this, "don't enter the same name", Toast.LENGTH_SHORT).show();
                        else {
                            namep1 = edit1.getText().toString();
                            namep2 = edit2.getText().toString();
                            namep3 = edit3.getText().toString();

                            finish();
                            Intent intent = new Intent(NameOfPlayers.this, LevelActivity.class);
                            intent.putExtra("name1", namep1);
                            intent.putExtra("name2", namep2);
                            intent.putExtra("name3", namep3);
                            intent.putExtra("player", p);
                            startActivity(intent);
                        }
                    }
                    else
                        Toast.makeText(this, "the name must be more than two letters", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}