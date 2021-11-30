package com.ariadiki.randomnumber;

public class Player{
    private String name;
    public int score=0;
    public int tries=0;
    public int total=0;


    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Player(String name) {
        this.name=name;
    }

    @Override
    public String toString() {
        if(this.name.length()<9) {
            for (int i = this.name.length(); i < 9; i++) {
                this.name += " ";
            }
        }
        return  this.name +" |  Score: " + score;
    }
}


