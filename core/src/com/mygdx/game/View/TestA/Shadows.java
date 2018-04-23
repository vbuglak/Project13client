package com.mygdx.game.View.TestA;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by CooL on 22.02.2018.
 */

public class Shadows implements Animation {
    private Texture shadow;
    private Vector3 pos;
    private boolean dead=false;


    public Shadows() {
        pos = new Vector3();
        loadtexture();
    }

    public void loadtexture() {
        shadow = new Texture("s.png");
    }

    @Override
    public void update(Vector3 position, boolean rv, int st,boolean attackview) {
        pos=position;
    }

    public void finalrender(SpriteBatch sb) {
        sb.draw(shadow, pos.x+20 , pos.y, shadow.getWidth(), shadow.getHeight());
    }


    public float getY() {
        return pos.y;
    }

    public boolean isDead() {
        return dead;
    }

    public void setX(float x) {
        this.pos.x = x;
    }

    public void setY(float y) {
        this.pos.y = y;
    }

    public void setZ(float z) {
        this.pos.z = z;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public int getId() {
        return 0;
    }
}