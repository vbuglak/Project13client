package com.mygdx.game.View.TestA;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.objects.ch1;

/**
 * Created by CooL on 18.02.2018.
 */

public interface Animation {
    void loadtexture();
    void finalrender(SpriteBatch sb);
    float getY();
    boolean isDead();
    int getId();
    void setDead(boolean dead);
    void setX(float x);
    void setY(float y);
    void setZ(float z);
    void update(Vector3 position, boolean rv, int st,boolean attackview);
}
