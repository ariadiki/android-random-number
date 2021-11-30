package com.ariadiki.randomnumber;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static int z,min=1,max=1;
    int random;
    Player[] tab;
    long backPressedTime;
    TextView rsp;
    HashMap<Integer,Integer> colors = new HashMap<Integer,Integer>();

     HashMap<Integer,Player> lst = new HashMap<Integer, Player>();
     Player p1,p2,p3;

     MediaPlayer soundButton,soundWrong,soundCorrect;
     Vibrator vibrator;
     RelativeLayout main;

    String level,players;
    TextView score_one,score_two,score_three;
    ImageView total_one,total_two,total_three;
    TextView txt_result;
    ImageView img_guess;
    EditText txt_num;
    Button btn_guess,btn_lb,btn_replay;
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.relative);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        soundButton = MediaPlayer.create(this,R.raw.button_click);
        soundCorrect = MediaPlayer.create(this,R.raw.correct_answer);
        soundWrong = MediaPlayer.create(this,R.raw.wrong_answer);

        p1= new Player(getIntent().getExtras().getString("name1"));
        p2= new Player(getIntent().getExtras().getString("name2"));
        p3= new Player(getIntent().getExtras().getString("name3"));

        colors.put(1,getResources().getColor(R.color.red));
        colors.put(2,getResources().getColor(R.color.yellow));
        colors.put(3,getResources().getColor(R.color.lime));

        rsp = findViewById(R.id.rsp);

        txt_result = findViewById(R.id.txt_result);
        img_guess = findViewById(R.id.img_guess);

        score_one = findViewById(R.id.sone);
        total_one = findViewById(R.id.pone);

        score_two = findViewById(R.id.stwo);
        total_two = findViewById(R.id.ptwo);

        score_three = findViewById(R.id.sthree);
        total_three = findViewById(R.id.pthree);

        txt_num = (EditText)findViewById(R.id.txtguess);
        btn_guess = (Button)findViewById(R.id.btn_guess);

        btn_lb = findViewById(R.id.btn_lb);
        btn_replay = findViewById(R.id.btn_replay);

        //score = Integer.valueOf(txt_num.getText().toString());
        //players.equals(getIntent().getExtras().getString("player"));

        level=String.valueOf(getIntent().getExtras().getString("level"));
        //levels
        switch (level)
        {
            case "easy":
                min=1;
                max=100;
                break;
            case"medium":
                min=1;
                max=300;
                break;
            case "hard":
                min=1;
                max=500;
                break;
            case "expert":
                min=1;
                max=1000;
                break;

            case "specific":
                min=getIntent().getExtras().getInt("min");
                max=getIntent().getExtras().getInt("max");
                break;
        }

        //Number of players
        players = String.valueOf(getIntent().getExtras().getString("player"));
        //players
        switch(players)
        {
            case "p1":
                total_two.setImageResource(R.drawable.playero);
                score_two.setText(String.valueOf(p1.score));
                lst.put(1,p1);
                break;

            case "p2":
                total_one.setImageResource(R.drawable.playero);
                score_one.setText(String.valueOf(p1.score));
                total_three.setImageResource(R.drawable.playert);
                score_three.setText(String.valueOf(p2.score));
                lst.put(1,p1);
                lst.put(2,p2);
                break;

            case "p3":
                total_one.setImageResource(R.drawable.playero);
                score_one.setText(String.valueOf(p1.score));
                total_two.setImageResource(R.drawable.playert);
                score_two.setText(String.valueOf(p2.score));
                total_three.setImageResource(R.drawable.playerth);
                score_three.setText(String.valueOf(p3.score));
                lst.put(1,p1);
                lst.put(2,p2);
                lst.put(3,p3);
                break;
        }

        btn_lb.setEnabled(false); //leaderboard button
        btn_replay.setEnabled(false);

        z=1;
        tab = new Player[4];
        tab[0]=p1;
        tab[1]=p2;
        tab[2]=p3;

        btn_lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                finish();
                Intent intent = new Intent(getApplicationContext(),LeaderBoard.class);
                intent.putExtra("size",lst.size());

                switch(lst.size())
                {
                    case 1:
                        intent.putExtra("first", tab[0].toString());
                        intent.putExtra("ftries", String.valueOf(tab[0].total));
                        break;

                    case 2:
                        tab = sortPlayer();
                        intent.putExtra("first", tab[0].toString());
                        intent.putExtra("second", tab[1].toString());
                        intent.putExtra("ftries", String.valueOf(tab[0].total));
                        intent.putExtra("stries", String.valueOf(tab[1].total));
                        break;

                    case 3:
                        tab = sortPlayer();
                        intent.putExtra("first", tab[0].toString());
                        intent.putExtra("second", tab[1].toString());
                        intent.putExtra("third", tab[2].toString());
                        intent.putExtra("ftries", String.valueOf(tab[0].total));
                        intent.putExtra("stries", String.valueOf(tab[1].total));
                        intent.putExtra("htries", String.valueOf(tab[2].total));
                        break;
                }
                startActivity(intent);
            }
        });
        btn_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundButton.start();
                z=1;
                initialTries();
                randNumber(min, max);
                img_guess.setImageResource(R.drawable.observe);
                txt_result.setTextSize(20);
                txt_result.setText("Enter Number between "+min+" and "+max);
                rsp.setTextSize(35);
                rsp.setText("turn of "+lst.get(z).getName());
                rsp.setTextColor(colors.get(z));
                main.setBackgroundColor(colors.get(z));
                txt_num.setEnabled(true);
                btn_lb.setEnabled(false);
                btn_replay.setEnabled(false);
                btn_guess.setEnabled(true);
            }
        });

        //choose the random number
        randNumber(min,max);
        txt_result.setText("Enter Number between "+min+" and "+max);
        //turn of
        rsp.setText("turn of "+lst.get(z).getName());
        rsp.setTextColor(colors.get(z));
        main.setBackgroundColor(colors.get(z));
    }


    public void guess_click(View view) {
        try {
                if (TextUtils.isEmpty(txt_num.getText().toString()))
                    Toast.makeText(this, "Enter a Number please !", Toast.LENGTH_SHORT).show();
                else {
                    if (Integer.valueOf(txt_num.getText().toString()) <= max && Integer.valueOf(txt_num.getText().toString()) >= min) {
                        if (Integer.valueOf(txt_num.getText().toString()) > random) {
                            soundWrong.start();
                            vibrator.vibrate(500);
                            lst.get(z).tries++;
                            //Toast.makeText(this, "too hight", Toast.LENGTH_SHORT).show();
                            txt_result.setTextSize(25);
                            txt_result.setText("too Hight");
                            img_guess.setImageResource(R.drawable.deny);
                            txt_num.setText("");
                            increment(z);
                        } else if (Integer.valueOf(txt_num.getText().toString()) < random) {
                            soundWrong.start();
                            vibrator.vibrate(500);
                            lst.get(z).tries++;
                            //Toast.makeText(this, "too low", Toast.LENGTH_SHORT).show();
                            txt_result.setTextSize(25);
                            txt_result.setText("too Low");
                            img_guess.setImageResource(R.drawable.deny);
                            txt_num.setText("");
                            increment(z);
                        } else if (Integer.valueOf(txt_num.getText().toString()) == random) {
                            soundCorrect.start();
                            vibrator.vibrate(1000);
                            lst.get(z).score++;
                            lst.get(z).tries++;
                            img_guess.setImageResource(R.drawable.approved);
                            txt_result.setTextSize(30);
                            txt_result.setText("Congratulation "+lst.get(z).getName()+" !");
                            rsp.setTextSize(20);
                            rsp.setText("you found the number in "+lst.get(z).tries+" tries");
                            txt_num.setText("");
                            //Toast.makeText(this, "Congratulation " + lst.get(z).getName() + " ! you found the number " + random + " in " + lst.get(z).tries + " tries your score is " + lst.get(z).score, Toast.LENGTH_LONG).show();
                            saveTries();
                            btn_lb.setEnabled(true);
                            btn_replay.setEnabled(true);
                            txt_num.setEnabled(false);
                            btn_guess.setEnabled(false);
                            //messageDialogue(z);
                            scoreResult(lst.size(), z);
                        }
                    } else {
                        //Toast.makeText(this, "Enter Number between " + min + " and " + max, Toast.LENGTH_SHORT).show();
                        lst.get(z).tries++;
                        img_guess.setImageResource(R.drawable.observe);
                        txt_result.setTextSize(20);
                        txt_result.setText("Enter Number between "+min+" and "+max);
                        txt_num.setText("");
                    }
                }
        } catch (Exception ex) {
                Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed()
    {
        Toast toast = null;
        if(backPressedTime+2000>System.currentTimeMillis()) {
            toast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    public Player[] sortPlayer()
    {
        for(int i=0;i<lst.size()-1;i++)
        {
            for(int j=i+1;j<lst.size();j++)
            {
                if(tab[i].score < tab[j].score)
                {
                    tab[3] = tab[i];
                    tab[i] = tab[j];
                    tab[j] = tab[3];
                }
                else if(tab[i].score == tab[j].score)
                {
                    if(tab[i].total > tab[j].total)
                    {
                        tab[3] = tab[i];
                        tab[i] = tab[j];
                        tab[j] = tab[3];
                    }
                }
            }
        }
        return tab;
    }

    public void increment(int pos)
    {
        if(pos<lst.size())
            z++;
        else
            z=1;

        rsp.setText("turn of "+lst.get(z).getName());
        rsp.setTextSize(35);
        rsp.setTextColor(colors.get(z));
        main.setBackgroundColor(colors.get(z));
    }

    public void scoreResult(int nbr,int pos)
    {
        switch(nbr)
        {
            case 1:
                score_two.setText(String.valueOf(lst.get(pos).score));
                break;

            case 2:
                if(pos == 1)
                score_one.setText(String.valueOf(lst.get(pos).score));
                else
                score_three.setText(String.valueOf(lst.get(pos).score));
                break;

            case 3:
                switch(pos)
                {
                    case 1:
                        score_one.setText(String.valueOf(lst.get(pos).score));
                        break;
                    case 2:
                        score_two.setText(String.valueOf(lst.get(pos).score));
                        break;

                    case 3:
                        score_three.setText(String.valueOf(lst.get(pos).score));
                        break;
                }
            break;
        }
    }

    public void help_click(View view)
    {
        Toast.makeText(this, "The number is : "+random, Toast.LENGTH_SHORT).show();
    }

    public void randNumber(int min,int max)
    {
        r = new Random();
        random = r.nextInt(max - min)+min;
    }
    public void saveTries()
    {
        for(int i=1;i<=lst.size();i++){
            lst.get(i).total += lst.get(i).tries;
        }
    }

    public void initialTries()
    {
        for(int i=1;i<=lst.size();i++){
            lst.get(i).tries=0;
        }
    }
}