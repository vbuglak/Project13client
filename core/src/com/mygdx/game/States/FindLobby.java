package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Network.Connect;
import com.mygdx.game.Network.Packets;
import com.mygdx.game.Project13class;

/**
 * Created by CooL on 15.03.2018.
 */

public class FindLobby extends State {
    public int connectedid;
    public static boolean connectlobby=false;
    private Texture background;
    public Stage stage;
    private Skin skin;
    public  Table table;
    private TextButton button1;
    private TextButton button2;
    public TextButton lobbybutton;
    public Timer timer;
    public Timer timer2;
    public Timer.Task tasktimer;
    public Timer.Task tasktimerconnect;

    public FindLobby(GameStateManager gsm) {
        super(gsm);
        Packets.Packet04Findlobby p = new Packets.Packet04Findlobby();
        Connect.client.sendTCP(p);
        background = new Texture("back.png");
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());

        tasktimer = new Timer.Task() {
            @Override
            public void run() {
                drawlobby(Connect.cnl.getLobbyid());
            }
        };
        tasktimerconnect = new Timer.Task() {
            @Override
            public void run() {
                if (connectlobby){
                    connecttolobby();
                }
                else {
                    System.out.println("не могу подключиться");
                }
            }
        };
        timer = new Timer();
        timer2 = new Timer();
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        button1 = new TextButton("refresh", skin, "default");
        button1.setHeight(50);
        button1.setWidth(500);
        button1.setPosition(0,0);
        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Packets.Packet04Findlobby p = new Packets.Packet04Findlobby();
                Connect.client.sendTCP(p);
                if (timer.isEmpty()) {
                    timer.scheduleTask(tasktimer,0.3f);
                }
                drawlobby(Connect.cnl.getLobbyid());
            }
        });
        button2 = new TextButton("back", skin, "default");
        button2.setHeight(50);
        button2.setWidth(500);
        button2.setPosition(Project13class.WIDTH,0,Align.bottomRight);
        button2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gotoscreen();
            }
        });
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

        drawlobby(Connect.cnl.getLobbyid());

    }
    public void drawlobby(int[] id) {
        table.clear();
        if (id!=null) {
            for (int i = 0; id[i] != 0; i++) {
                final int lid = id[i];
                lobbybutton = new TextButton("Lobby id "+ Integer.toString(id[i]), skin, "default");
                lobbybutton.setHeight(30);
                stage.addActor(lobbybutton);
                lobbybutton.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                    joinlobby(lid);
                    }
                });
                table.add(lobbybutton);
                table.row();
            }
        }
    }

    public void joinlobby(int id){
        Packets.Packet07Joinlobby p = new Packets.Packet07Joinlobby();
        p.lobbyid=id;
        connectedid = id;
        Connect.client.sendTCP(p);
        System.out.println("подключаюсь к "+ Integer.toString(id));
        if (timer2.isEmpty()) {
            timer2.scheduleTask(tasktimerconnect,0.1f);
        }
    }

    public void connecttolobby(){
        gsm.set(new Lobby(gsm,connectedid,false));
        connectlobby=false;
    }

    private void gotoscreen(){
            gsm.set(new Gamescreen1(gsm));
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

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
