package com.mygdx.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.View.TestA.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CooL on 19.02.2018.
 */

public interface Objects {
     Rectangle getBody();
     Vector3 getPosition();
     int getBodytype();
     int getDEPTH();
     int getId();
     void setPosition(float x, float y,float z);
     void setHp(float hp);
     void setDamage(boolean damage);
     void update();
     void updateanim(Animation oa);
     boolean isDead();
     boolean isDamage();
     List<Animation> upanimlist(List<Animation> animlist,int i);
     void setAttid(int id);
     ArrayList<Integer> getAttid();
     void setLaunch(boolean launch);
     void setJugler(boolean jugler);
     boolean getRview();
     void setDz(float dz);
     float getDz();
     float getKx();
     void setKx(float kx);
}
