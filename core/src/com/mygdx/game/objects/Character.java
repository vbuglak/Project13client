package com.mygdx.game.objects;


import com.badlogic.gdx.math.Vector3;

/**
 * Created by CooL on 14.02.2018.
 */

public abstract class Character {
    protected Vector3 position;
    protected Vector3 speed;
    protected float hp;
    protected float mp;
    protected float armor;
    protected int ap ;
    protected int id;
    protected int state;
    protected boolean rview;

    public abstract void updatech(float dt,ch1 att) ;
    public abstract void handleInput(float dt);
    public abstract void jump(float dt,ch1 att);
    public abstract void walk();
    public abstract void stay();
    public abstract void run();
    public abstract void attack(float dt,ch1 att);





}
