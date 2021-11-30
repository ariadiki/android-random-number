package com.ariadiki.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SpecificNum extends AppCompatActivity {
    EditText txt_min,txt_max;
    Button btn_apply;
    String p,level,name1,name2,name3;
    MediaPlayer soundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_num);

        txt_min = findViewById(R.id.minput);
        txt_max = findViewById(R.id.maxput);
        btn_apply = findViewById(R.id.apply);
        soundButton = MediaPlayer.create(this,R.raw.button_click);
        p = getIntent().getExtras().getString("player");
        level= getIntent().getExtras().getString("level");
        name1 = getIntent().getExtras().getString("name1");
        name2 = getIntent().getExtras().getString("name2");
        name3 = getIntent().getExtras().getString("name3");
    }

    public void apply_click(View view) {
        soundButton.start();
        if(TextUtils.isEmpty(txt_min.getText()) || TextUtils.isEmpty(txt_max.getText()))
        {
            Toast.makeText(this, "fill up the zones", Toast.LENGTH_SHORT).show();
        }
        else{
            if(Integer.valueOf(txt_min.getText().toString())< Integer.valueOf(txt_max.getText().toString()))
            {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("level",level);
                intent.putExtra("player",p);
                intent.putExtra("name1",name1);
                intent.putExtra("name2",name2);
                intent.putExtra("name3",name3);
                intent.putExtra("min",Integer.valueOf(txt_min.getText().toString()));
                intent.putExtra("max",Integer.valueOf(txt_max.getText().toString()));
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "the min must be small", Toast.LENGTH_SHORT).show();
            }
        }
    }
}