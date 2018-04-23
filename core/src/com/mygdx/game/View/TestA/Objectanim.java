package com.mygdx.game.View.TestA;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by CooL on 18.02.2018.
 */

public class Objectanim implements com.mygdx.game.View.TestA.Animation {
    private Texture stone;
    private Texture stone2;
    private TextureRegion currentFrame;
    private TextureRegion[] stoneFrames;
    private Animation stoneAnimation;
    private TextureRegion[] deadFrames;
    private Animation deadAnimation;
    private float stateTime;
    private float starttime=0;
    private Vector3 pos;
    private boolean deadobj=false;
    private boolean dead=false;
    private boolean start=false;
    private int id;

    public Objectanim(int bid) {
        pos = new Vector3();
        id = bid;
        loadtexture();
    }

    @Override
    public void update(Vector3 position, boolean rv, int st, boolean attackview) {

    }

    public void loadtexture() {
        int i, j, index = 0;
        stone = new Texture("stone.png");
        TextureRegion tmp[][] = TextureRegion.split(stone, stone.getWidth() / 6, stone.getHeight());
        stone2 = new Texture("broke.png");
        TextureRegion tmp2[][] = TextureRegion.split(stone2, stone2.getWidth() / 10, stone2.getHeight()/10);
        stoneFrames = new TextureRegion[6 * 1];
        for (i = 0; i < 1; i++) {
            for (j = 0; j < 6; j++) {
                stoneFrames[index++] = tmp[i][j];
            }
        }
        index = 0;
        deadFrames = new TextureRegion[8 * 1];
        for (i = 0; i < 1; i++) {
            for (j = 0; j < 8; j++) {
                deadFrames[index++] = tmp2[i][j];
            }
        }
        deadAnimation = new Animation(1f/32f, deadFrames);
    }

    public void finalrender(SpriteBatch sb) {
        stateTime += Gdx.graphics.getDeltaTime();
        if (!deadobj) {
            currentFrame = stoneFrames[1];
            sb.draw(currentFrame, pos.x, pos.y + pos.z, stone.getWidth() / 6, stone.getHeight());
        } else {
            if (!start) {
                starttime = TimeUtils.nanoTime();
                stateTime=0;
            }
            start=true;
            if (((TimeUtils.nanoTime()-starttime<1000000000/4)&&(start))) {

                currentFrame = (TextureRegion) deadAnimation.getKeyFrame(stateTime, true);
                sb.draw(currentFrame, pos.x - 10, pos.y + pos.z + 30, stone2.getWidth() / 10, stone2.getHeight()/10);
                sb.draw(currentFrame, pos.x - 10, pos.y + pos.z, stone2.getWidth() / 10, stone2.getHeight()/10);
                sb.draw(currentFrame, pos.x + 30, pos.y + pos.z + 30, stone2.getWidth() / 10, stone2.getHeight()/10);
                sb.draw(currentFrame, pos.x + 30, pos.y + pos.z, stone2.getWidth() / 10, stone2.getHeight()/10);
            } else {
                dead = deadobj;
            }

        }
    }

    public int getId() {
        return id;
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
        this.deadobj = dead;
    }
}
