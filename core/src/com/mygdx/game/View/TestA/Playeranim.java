package com.mygdx.game.View.TestA;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.objects.ch1;

/**
 * Created by CooL on 16.02.2018.
 */

public class Playeranim implements com.mygdx.game.View.TestA.Animation {

    private Texture atlasch1;
    private Texture atlasch2;
    private TextureRegion currentFrame;
    private TextureRegion[] stayFrames;
    private Animation stayAnimation;
    private TextureRegion[] walkFrames;
    private Animation walkAnimation;
    private TextureRegion[] jumpFrames;
    private Animation jumpAnimation;
    private TextureRegion[] rjumpFrames;
    private Animation rjumpAnimation;
    private TextureRegion[] runFrames;
    private Animation runAnimation;
    private TextureRegion[] runattackFrames;
    private Animation runattackAnimation;
    private TextureRegion[] punchFrames;
    private Animation punchAnimation;
    private TextureRegion[] jumpatFrames;
    private Animation jumpatAnimation;
    private TextureRegion[] rjumpatFrames;
    private Animation rjumpatAnimation;
    private TextureRegion[] defendFrames;
    private Animation defendAnimation;
    private TextureRegion[] crouchFrames;
    private Animation crouchAnimation;
    private TextureRegion[] injFrames;
    private Animation injAnimation;
    private TextureRegion[] juglerFrames;
    private Animation juglerAnimation;
    private TextureRegion[] juglelFrames;
    private Animation juglelAnimation;
    private TextureRegion[] fallFrames;
    private Animation fallAnimation;
    private TextureRegion[] skill1Frames;
    private Animation skill1Animation;
    private float stateTime;
    private float stateTimep;
    private float starttime=0;
    private Vector3 pos;
    private boolean rview;
    private boolean rviewf;
    private boolean start;
    private boolean startf;
    private boolean dead=false;
    private boolean runattack;
    private boolean attack;
    private boolean rjattack;
    private boolean rjugle;
    private int State;
    private int id=144;



    public Playeranim() {
        pos = new Vector3();
        loadtexture();
    }

    public Playeranim(Vector3 position,boolean rv,int st) {
        pos = new Vector3();
        loadtexture();
        pos.x=position.x;
        pos.y=position.y;
        pos.z=position.z;
        rview=rv;
        State=st;
    }

    public void updatepos(ch1 character1){
        pos.x=character1.getPosition().x;
        pos.y=character1.getPosition().y;
        pos.z=character1.getPosition().z;
        rview=character1.getRview();
        State=character1.getState();
        dead=character1.isDead();
        runattack=character1.isRunattack();
        attack=character1.isAttack();
        rjattack=character1.isRjump();
        rjugle=character1.isJugler();
    }

    public void update(Vector3 position,boolean rv,int st,boolean attackview){
        pos.x=position.x;
        pos.y=position.y;
        pos.z=position.z;
        rview=rv;
        State=st;
        rjugle=attackview;
    }



    public void loadtexture() {
        int i, j, index = 0;
        atlasch1 = new Texture("atlasch1.png");
        TextureRegion tmp[][] = TextureRegion.split(atlasch1, atlasch1.getWidth() / 10, atlasch1.getHeight() / 7);
        atlasch2 = new Texture("atlasch2.png");
        TextureRegion tmp2[][] = TextureRegion.split(atlasch2, atlasch2.getWidth() / 10, atlasch2.getHeight() / 7);
        stayFrames = new TextureRegion[4 * 1];
        for (i = 0; i < 1; i++) {
            for (j = 0; j < 4; j++) {
                stayFrames[index++] = tmp[i][j];
            }
        }
        stayAnimation = new Animation(0.3f, stayFrames);
        walkFrames = new TextureRegion[4 * 1];
        index = 0;
        for (i = 0; i < 1; i++) {
            for (j = 4; j < 8; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.3f, walkFrames);

        jumpFrames = new TextureRegion[5 * 1];
        index = 0;
        for (i = 6; i < 7; i++) {
            for (j = 0; j < 3; j++) {
                jumpFrames[index++] = tmp[6][2];
            }
        }
        for (i = 6; i < 7; i++) {
            for (j = 1; j >= 0; j--) {
                jumpFrames[index++] = tmp[6][2];
            }
        }
        jumpAnimation = new Animation(0.1f, jumpFrames);

        runFrames = new TextureRegion[3 * 1];
        index = 0;
        for (i = 2; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                runFrames[index++] = tmp[i][j];
            }
        }
        runAnimation = new Animation(0.2f, runFrames);

        rjumpFrames = new TextureRegion[3 * 1];
        rjumpFrames[0] = tmp[6][3];
        rjumpAnimation = new Animation(1f/12f, rjumpFrames);

        punchFrames = new TextureRegion[12 * 1];
        index = 0;
        for (i = 1; i < 2; i++) {
            for (j = 0; j < 8; j++) {
                punchFrames[index++] = tmp[i][j];
            }
        }
        punchFrames[index++] = tmp[1][9];
        punchFrames[index++] = tmp[2][9];
        punchFrames[index++] = tmp[3][9];
        punchFrames[index++] = tmp[4][9];

        punchAnimation = new Animation(1f/12f, punchFrames);

        runattackFrames = new TextureRegion[7 * 1];
        index = 0;
        for (i = 3; i < 4; i++) {
            for (j = 0; j < 7; j++) {
                runattackFrames[index++] = tmp2[i][j];
            }
        }
        runattackAnimation = new Animation(1f/7f, runattackFrames);

        jumpatFrames = new TextureRegion[4 * 1];
        index = 0;
        for (i = 0; i < 1; i++) {
            for (j = 0; j < 4; j++) {
                jumpatFrames[index++] = tmp2[i][j];
            }
        }
        jumpatAnimation = new Animation(1f/24f, jumpatFrames);

        rjumpatFrames = new TextureRegion[6 * 1];
        index = 0;
        for (i = 6; i < 7; i++) {
            for (j = 2; j < 8; j++) {
                rjumpatFrames[index++] = tmp2[i][j];
            }
        }
        rjumpatAnimation = new Animation(1f/24f, rjumpatFrames);
        defendFrames = new TextureRegion[2 * 1];
        index = 0;
        for (i = 5; i < 6; i++) {
            for (j = 6; j < 8; j++) {
                defendFrames[index++] = tmp[i][j];
            }
        }

        crouchFrames = new TextureRegion[3 * 1];
        index = 0;
        for (i = 5; i < 6; i++) {
            for (j = 8; j < 10; j++) {
                crouchFrames[index++] = tmp[i][j];
            }
        }
        crouchFrames[2]=tmp[6][9];
        crouchAnimation = new Animation(1f/6f, crouchFrames);
        injFrames = new TextureRegion[6 * 1];
        index = 0;
        for (i = 5; i < 6; i++) {
            for (j = 0; j < 5; j++) {
                injFrames[index++] = tmp2[i][j];
            }
        }
        injFrames[5]=tmp[5][4];
        injAnimation = new Animation(1f/3f, injFrames);
        juglerFrames = new TextureRegion[4 * 1];
        index = 0;
        for (i = 3; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                juglerFrames[index++] = tmp[i][j];
            }
        }
        juglerAnimation = new Animation(1f/8f, juglerFrames);

        juglelFrames = new TextureRegion[4 * 1];
        index = 0;
        for (i = 4; i < 5; i++) {
            for (j = 0; j < 4; j++) {
                juglelFrames[index++] = tmp[i][j];
            }
        }
        juglelAnimation = new Animation(1f/8f, juglelFrames);

        fallFrames = new TextureRegion[2 * 1];
        fallFrames[0]=tmp[3][4];
        fallFrames[1]=tmp[4][4];

        skill1Frames = new TextureRegion[8 * 1];
        index = 0;
        skill1Frames[index++] = tmp[6][7];
        skill1Frames[index++] = tmp[6][8];
        skill1Frames[index++] = tmp[0][8];
        skill1Frames[index++] = tmp[0][9];
        skill1Frames[index++] = tmp[1][9];
        skill1Frames[index++] = tmp[2][9];
        skill1Frames[index++] = tmp[3][9];
        skill1Frames[index++] = tmp[4][9];

        skill1Animation = new Animation(1f/8f, skill1Frames);
    }

    public void finalrender(SpriteBatch sb) {

        stateTime += Gdx.graphics.getDeltaTime();
        if (State==1) {
            start=false;
            startf=false;
            currentFrame = (TextureRegion) stayAnimation.getKeyFrame(stateTime, true);
            sb.draw(currentFrame, !rview ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==2) {
            start=false;
            startf=false;
            currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
            sb.draw(currentFrame, !rview ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==3) {
            if ((!attack) && (!rjattack)) {
                currentFrame = (TextureRegion) jumpAnimation.getKeyFrame(stateTime, true);
                sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
            /*if ((!attack) && (rjattack)) {
                currentFrame = rjumpFrames[0];
                sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
            if ((attack)&&(!rjattack)){
                if (!start) {
                    stateTimep=0;
                }
                stateTimep+=Gdx.graphics.getDeltaTime();
                start=true;
                currentFrame = (TextureRegion) jumpatAnimation.getKeyFrame(stateTimep, false);
                sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
            if ((attack)&&(rjattack)){
                if (!start) {
                    stateTimep=0;
                }
                stateTimep+=Gdx.graphics.getDeltaTime();
                start=true;
                currentFrame = (TextureRegion) rjumpatAnimation.getKeyFrame(stateTimep, false);
                sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }*/

        }
        if (State==4) {
            currentFrame = (TextureRegion) runAnimation.getKeyFrame(stateTime, true);
            sb.draw(currentFrame, !rview ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==5) {
            if (!start) {
                starttime = TimeUtils.nanoTime();
                stateTimep=0;
            }
            start=true;

            //if (!runattack){ ////stayattack
                stateTimep += Gdx.graphics.getDeltaTime();
                currentFrame = (TextureRegion) punchAnimation.getKeyFrame(stateTimep, true);
                sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
          //  }
            /*if (runattack) { /////runattack
                stateTimep += Gdx.graphics.getDeltaTime();
                currentFrame = (TextureRegion) runattackAnimation.getKeyFrame(stateTimep, true);
                sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }*/
        }
        if (State==6) {
            currentFrame = rjumpFrames[0];
            sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==7){
            if (!start) {
                stateTimep=0;
            }
            stateTimep+=Gdx.graphics.getDeltaTime();
            start=true;
            currentFrame = (TextureRegion) jumpatAnimation.getKeyFrame(stateTimep, false);
            sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==8) {
            if (!start) {
                starttime = TimeUtils.nanoTime();
                stateTimep=0;
            }
            start=true;/////runattack
            stateTimep += Gdx.graphics.getDeltaTime();
            currentFrame = (TextureRegion) runattackAnimation.getKeyFrame(stateTimep, true);
            sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==9){
            if (!start) {
                stateTimep=0;
            }
            stateTimep+=Gdx.graphics.getDeltaTime();
            start=true;
            currentFrame = (TextureRegion) rjumpatAnimation.getKeyFrame(stateTimep, false);
            sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==10) {
            currentFrame = defendFrames[0];
            sb.draw(currentFrame, !rview ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==11) {
            currentFrame = (TextureRegion) crouchAnimation.getKeyFrame(stateTime, true);
            sb.draw(currentFrame, !rview ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==12) {
            currentFrame = (TextureRegion) injAnimation.getKeyFrame(stateTime, true);
            sb.draw(currentFrame, !rview ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y+pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==13) {
            if (!start) {
                starttime = TimeUtils.nanoTime();
                stateTimep=0;
            }
            start=true;
            stateTimep += Gdx.graphics.getDeltaTime();
            if (rjugle) {
                currentFrame = (TextureRegion) juglerAnimation.getKeyFrame(stateTimep, false);
                sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
            else {
                currentFrame = (TextureRegion) juglelAnimation.getKeyFrame(stateTimep, false);
                sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
        }
        if (State==14) {
            if (!startf&&rview){
                rviewf=true;
                startf=true;
            }
            else if(!startf){
                rviewf=false;
                startf=true;
            }
            if (rjugle){
            currentFrame = fallFrames[0];
            sb.draw(currentFrame, !rviewf ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rviewf ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
            else {
                currentFrame = fallFrames[1];
                sb.draw(currentFrame, !rviewf ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rviewf ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
        }
        if (State==15) {
            if (!start) {
                stateTimep = 0;
            }
            stateTimep += Gdx.graphics.getDeltaTime();
            start = true;
            currentFrame = (TextureRegion) skill1Animation.getKeyFrame(stateTimep, true);
            sb.draw(currentFrame, !rview ? pos.x + (1 * atlasch1.getWidth() / 10) : pos.x, pos.y + pos.z, !rview ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
        }
        if (State==99) {
            if (!startf&&rview){
                rviewf=true;
                startf=true;
            }
            else if(!startf){
                rviewf=false;
                startf=true;
            }
            if (rjugle){
                currentFrame = fallFrames[0];
                sb.draw(currentFrame, !rviewf ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rviewf ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
            else {
                currentFrame = fallFrames[1];
                sb.draw(currentFrame, !rviewf ? pos.x+(1 * atlasch1.getWidth() / 10) : pos.x, pos.y, !rviewf ? -(1 * atlasch1.getWidth() / 10) : (1 * atlasch1.getWidth() / 10), (1 * atlasch1.getHeight() / 7));
            }
        }
    }

    public float getY() {
        return pos.y;
    }
    public int getId() {
        return id;
    }
    public boolean isDead() {
        return dead;
    }
    @Override
    public void setX(float x) {

    }
    @Override
    public void setY(float y) {

    }
    @Override
    public void setZ(float z) {

    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }
}

