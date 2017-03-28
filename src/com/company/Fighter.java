package com.company;

import java.util.Random;

/**
 * Created by Cyborg on 3/27/2017.
 */
public class Fighter {
    private int id;
    private int strength;
    private int dexterity;
    private int intuition;
    private int health = 100;
    private int countWins;

    private double str, dex, intu;

    private Random random = new Random();

    public Fighter(int id){
        this.id = id;
        initParams();
    }

    public void initParams(){

        strength = random.nextInt(100)+1;
        dexterity = random.nextInt(100)+1;
        intuition = random.nextInt(100)+1;

        double sum = strength + dexterity + intuition;
        double koef = (50/sum);

        str = strength*koef;
        dex = dexterity*koef;
        intu = intuition*koef;

        strength = (int) Math.round(str);
        dexterity = (int) Math.round(dex);
        intuition = (int) Math.round(intu);

        checkForZero();

        if ((strength+dexterity+intuition) > 50){
            floorBiggest();
        }
        if ((strength+dexterity+intuition) > 50){
            floorBiggest();
        }

        System.out.println(toString());
    }

    public void checkForZero(){
        if (strength==0){
            strength = 1;
        }else if (dexterity == 0){
            dexterity = 1;
        }else if (intuition == 0){
            intuition = 1;
        }
    }

    public void floorBiggest(){
        if (strength > dexterity && strength > intuition){
            strength = (int) Math.floor(str);
        }else if (dexterity > intuition && dexterity > strength){
            dexterity = (int) Math.floor(dex);
        }else {
            intuition = (int) Math.floor(intu);
        }
    }

    public int hit(){
        if ((random.nextInt(100)+1) < intuition)
            return strength*2;
        else {
            return strength;
        }
    }

    public boolean bias(){
        if ((random.nextInt(100)+1) < dexterity)
            return true;
        else
            return false;
    }

    public void refreshHealth(){
        this.health = 100;
        countWins++;
    }

    public void damage(int hit){

        if (!bias()){
            health -= hit;
        } else {
            System.out.println("Вы увернулись");
        }

        //System.out.println("hit = " + hit + " to fighter id = " + id);
    }

    public int getId() {
        return id;
    }

    public int getCountWins() {
        return countWins;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "Fighter id = " + id + " strength = " + strength + " dexterity = " + dexterity + " intuition = " + intuition;
    }
}