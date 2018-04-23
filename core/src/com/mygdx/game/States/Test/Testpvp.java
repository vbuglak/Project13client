package com.mygdx.game.States.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Network.Packets;
import com.mygdx.game.Network.Connect;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.Gamescreen1;
import com.mygdx.game.States.State;
import com.mygdx.game.View.TestA.Animation;
import com.mygdx.game.View.TestA.Playeranim;
import com.mygdx.game.View.TestA.Shadows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.mygdx.game.Network.Connect.cnl;

/**
 * Created by CooL on 18.03.2018.
 */

public class Testpvp extends State {




    private Texture back;
    public ArrayList<Animation> animlist;
    public ArrayList<Animation> finallist;
    public ArrayList<Shadows> shadowlist;
    public boolean Keyup=false;
    public boolean Keydown=false;
    public boolean Keyleft=false;
    public boolean Keyright=false;
    public boolean KeyA=false;
    public boolean KeyJ=false;
    public boolean KeyD=false;
    public Packets.Packet14Playerbuttons butpack = new Packets.Packet14Playerbuttons();
    public Timer timer;
    public Timer.Task tasktimer;




    public Testpvp(GameStateManager gsm) {
        super(gsm);
        finallist=new ArrayList<Animation>();
        timer= new Timer();
        tasktimer = new Timer.Task() {
            @Override
            public void run() {
                Packets.Packet11Lobyback p = new Packets.Packet11Lobyback();
                Connect.client.sendTCP(p);
            }
        };
        timer.scheduleTask(tasktimer,2);
        InputProcessor input = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.W) {
                    Keyup = true;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.A) {
                    Keyleft = true;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.S) {
                    Keydown = true;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.D) {
                    Keyright = true;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.G) {
                    KeyA = true;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.H) {
                    KeyJ = true;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.J) {
                    KeyD = true;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.W) {
                    Keyup = false;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.A) {
                    Keyleft = false;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.S) {
                    Keydown = false;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.D) {
                    Keyright = false;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.G) {
                    KeyA = false;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.H) {
                    KeyJ = false;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                if (keycode == Input.Keys.J) {
                    KeyD = false;
                    butpack.Keyup = Keyup;
                    butpack.Keydown = Keydown;
                    butpack.Keyleft = Keyleft;
                    butpack.Keyright = Keyright;
                    butpack.KeyA = KeyA;
                    butpack.KeyJ = KeyJ;
                    butpack.KeyD = KeyD;
                    Connect.client.sendUDP(butpack);
                }
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        };
        Gdx.input.setInputProcessor(input);
        camera = new OrthographicCamera() ;
        camera.setToOrtho(false,1280,720);
        butpack.Keyup=Keyup;
        butpack.Keydown=Keydown;
        butpack.Keyleft=Keyleft;
        butpack.Keyright=Keyright;
        butpack.KeyA=KeyA;
        butpack.KeyJ=KeyJ;
        butpack.KeyD=KeyD;
        back = new Texture("test.png");
        shadowlist= new ArrayList<Shadows>();
        animlist = new ArrayList<Animation>();
        for (int i = 0; i < cnl.playersinfo.size(); i++) {
            shadowlist.add(new Shadows());
        }
        for (int i = 0; i < cnl.playersinfo.size(); i++) {
            animlist.add(new Playeranim(cnl.playersinfo.get(i).position,cnl.playersinfo.get(i).rview,cnl.playersinfo.get(i).state));
        }

    }

    private void updateanimlist(){ //сортируем для правильной отрисовки по Y
        finallist.clear();
        for (int i = 0; i < cnl.playersinfo.size(); i++) {
            animlist.get(i).update(cnl.playersinfo.get(i).position,cnl.playersinfo.get(i).rview,cnl.playersinfo.get(i).state,cnl.playersinfo.get(i).attackview);
            finallist.add(animlist.get(i));
        }

        Collections.sort(finallist, new Comparator<Animation>() {
            @Override
            public int compare(Animation o1, Animation o2) {
                return compareTo(o1.getY(),o2.getY());
            }
        });}

    public int compareTo(float o1,float o2){
        int res=0;
        if (o1<o2)
            res=1;
        if (o1==o2)
            res=0;
        if (o1>o2)
            res=-1;
        return res;
    }

    private void updateshadowlist(){ //сортируем для правильной отрисовки по Y
        for (int i = 0; i < cnl.playersinfo.size(); i++) {
            shadowlist.get(i).update(cnl.playersinfo.get(i).position,cnl.playersinfo.get(i).rview,cnl.playersinfo.get(i).state,cnl.playersinfo.get(i).attackview);
        }
        Collections.sort(shadowlist, new Comparator<Animation>() {
            @Override
            public int compare(Animation o1, Animation o2) {
                return compareTo(o1.getY(),o2.getY());
            }
        });}




    @Override
    protected void handleInput() {

    }



    @Override
    public void update(float dt) {
        handleInput();
        for (int i = 0; i < cnl.playersinfo.size(); i++) {
           if(cnl.playersinfo.get(i).cid==cnl.clientid){
               camupdate(cnl.playersinfo.get(i).position.x,cnl.playersinfo.get(i).position.y);
           }
        }
        updateanimlist();
        updateshadowlist();
        if (cnl.sessionend){
            cnl.sessionend=false;
            gsm.set(new Gamescreen1(gsm));
        }
    }

    public void camupdate(float x,float y){
        if (x<640-80/2){
            camera.position.x = 640;
        }
        if ((x>640-80/2)&&(x<back.getWidth()-640)) {
            camera.position.x = x+80/2;
        }
        if (x>back.getWidth()-640-80/2){
            camera.position.x = back.getWidth()-640;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(back, 0, 0);
        for (int i = 0; i < shadowlist.size(); i++) {
            shadowlist.get(i).finalrender(sb);
        }
        for (int i = 0; i < finallist.size(); i++) {
            finallist.get(i).finalrender(sb);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
