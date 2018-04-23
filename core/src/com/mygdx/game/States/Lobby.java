package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Network.Connect;
import com.mygdx.game.Network.Packets;
import com.mygdx.game.Project13class;
import com.mygdx.game.States.Test.Testpvp;

import static com.mygdx.game.Network.Connect.cnl;

/**
 * Created by CooL on 15.03.2018.
 */

public class Lobby extends State {
    private Texture background;
    private Stage stage;
    public  Skin skin;
    public  Label  creator;
    public  Label otherplayers;
    private TextButton button1;
    private TextButton button2;


    public Lobby(GameStateManager gsm,int id,boolean iscreator){
        super(gsm);
        cnl.lobbycreated = false;
        Packets.Packet10Givelinfo p = new Packets.Packet10Givelinfo();
        p.id=id;
        Connect.client.sendTCP(p);
        background = new Texture("back.png");
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        creator = new Label("Lobbycreator ID "+ Integer.toString(id),skin);
        creator.setPosition(0,600);
        otherplayers = new Label("Players IDS ",skin);
        otherplayers.setPosition(0,300);
        if (iscreator) {
            button1 = new TextButton("Start", skin, "default");
            button1.setHeight(50);
            button1.setWidth(500);
            button1.setPosition(0,0,Align.bottomLeft);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Packets.Packet12Gamesessionstart p = new Packets.Packet12Gamesessionstart();
                    Connect.client.sendTCP(p);
                }
            });
            stage.addActor(button1);
        }
        button2 = new TextButton("back", skin, "default");
        button2.setHeight(50);
        button2.setWidth(500);
        button2.setPosition(Project13class.WIDTH,0,Align.bottomRight);
        button2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Packets.Packet11Lobyback p = new Packets.Packet11Lobyback();
                Connect.client.sendTCP(p);
                gotoscreen(1);
            }
        });

        stage.addActor(button2);
        stage.addActor(creator);
        stage.addActor(otherplayers);
        Gdx.input.setInputProcessor(stage);

    }


    private void gotoscreen(int i){
        if (i==1) {
            gsm.set(new Gamescreen1(gsm));
        }
        if (i==2){
            gsm.set(new Testpvp(gsm));
        }

    }

    public void drawcreator(){

            creator.setText("Lobbycreator " + Integer.toString(cnl.getInfocrid()));

    }

    public void drawother(){
            otherplayers.setText("Players " + cnl.infoother.toString());

    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        drawcreator();
        drawother();
        if (cnl.lobbydeath){
            System.out.println("lobby sdoxlo!!!");
            gotoscreen(1);
        }
        if (cnl.startsession){
            cnl.startsession=false;
            gotoscreen(2);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Project13class.WIDTH,Project13class.HEIGHT);
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}