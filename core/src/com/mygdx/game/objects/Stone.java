package com.mygdx.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.View.TestA.Animation;
import com.mygdx.game.View.TestA.Objectanim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CooL on 18.02.2018.
 */

public class Stone implements Objects{
    private Vector3 position;
    private Vector3 speed;
    private float hp;
    private Rectangle body;
    private int bodytype = 200;
    private int DEPTH  = 10;
    private int id = 0;
    private boolean dead = false;
    public Objectanim bodyanim;




    public Stone(float x, float y, float z, float hp, int bid) {
       position = new Vector3(x,y,z);
       this.hp = hp;
       body = new Rectangle(x,y,56.875f,60);
       id=bid;
    }

    public void update() {
        if (hp<=0){
            dead=true;
        }
        if (position.z>=0){
            position.add(0,0,-2);
        }
        body.x = position.x;
        body.y = position.y;

    }

    public void updateanim(Animation oa){
        oa.setDead(dead);
        oa.setX(position.x);
        oa.setY(position.y);
        oa.setZ(position.z);
    }

    public List<Animation> upanimlist(List<Animation> animlist,int i){
        bodyanim = new Objectanim(i);
        animlist.add(bodyanim);
        return animlist;
    }

    public int getId() {
        return id;
    }

    public Rectangle getBody() {
        return body;
    }

    public void setPosition(float x, float y,float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.y = z;
    }

    public int getBodytype() {
        return bodytype;
    }

    public void setHp(float hp) {
        this.hp = this.hp - hp;
    }

    public int getDEPTH() {
        return DEPTH;
    }

    public Vector3 getPosition() {
        return position;
    }
    public Vector3 getPosition(int bodytype) {
        return position;
    }

    @Override
    public float getDz() {
        return 0;
    }

    @Override
    public boolean getRview() {
        return false;
    }

    @Override
    public boolean isDamage() {
        return false;
    }

    @Override
    public void setDamage(boolean damage) {

    }
    public boolean isDead() {
        return dead;
    }

    @Override
    public void setAttid(int id) {

    }

    @Override
    public ArrayList<Integer> getAttid() {
        return null;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public void setLaunch(boolean launch) {
    }

    @Override
    public void setJugler(boolean jugler) {

    }

    @Override
    public void setDz(float dz) {

    }

    @Override
    public float getKx() {
        return 0;
    }

    @Override
    public void setKx(float kx) {

    }
}
