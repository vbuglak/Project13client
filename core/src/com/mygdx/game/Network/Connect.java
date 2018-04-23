package com.mygdx.game.Network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.game.Project13class;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.Gamescreen1;
import com.mygdx.game.States.State;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by CooL on 13.03.2018.
 */

public class Connect extends State {
    int portSocket = 25565;
    String ipAddres = "95.73.79.163";

    public static Client client;
    public static ClientListener cnl;
    public boolean autorize;
    private TextButton loginb;
    private TextButton registerb;
    private TextField login;
    private TextField password;
    private Label l;
    private Label p;
    public static Label status;
    private Stage stage;
    private Skin skin;
    private Table table;


    public Connect(GameStateManager gsm) {
        super(gsm);
        client = new Client();
        cnl = new ClientListener();
        cnl.init(client);
        registerPackets();
        client.addListener(cnl);
        client.start();
        autorize = false;
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        //TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(font_38, Color.valueOf("646b6c"), getDrawable(atlas.findRegion("textField_cursor")), getDrawable(atlas.findRegion("textField_selection")), getDrawable(atlas.findRegion("textField_bg")));
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        loginb = new TextButton("Login", skin, "default");
        loginb.setHeight(50);
        loginb.setWidth(150);
        loginb.setPosition(500,200, Align.bottomLeft);
        loginb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Packets.Packet16Login p = new Packets.Packet16Login();
                p.login=login.getText();
                p.password=password.getText();
               cnl.client.sendTCP(p);
            }
        });
        registerb = new TextButton("Register", skin, "default");
        registerb.setHeight(50);
        registerb.setWidth(150);
        registerb.setPosition(670,200, Align.bottomLeft);
        registerb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Packets.Packet17Register p = new Packets.Packet17Register();
                p.login=login.getText();
                p.password=password.getText();
                cnl.client.sendTCP(p);
            }
        });
        login = new TextField("12345",skin);
        login.getDefaultInputListener();
        password = new TextField("54321",skin);
        password.getDefaultInputListener();
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        l= new Label("Login:",skin);
        p= new Label("Password:",skin);
        status= new Label("",skin);
        table.padTop(300);
        table.add(l).padRight(50f);
        table.add(login);
        table.row();
        table.add(p);
        table.add(password);
        table.row();
        table.add(status);
        stage.addActor(table);
        stage.addActor(loginb);
        stage.addActor(registerb);
        Gdx.input.setInputProcessor(stage);

        try {
            client.connect(5000,ipAddres,portSocket,portSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void registerPackets(){
        Kryo kryo = client.getKryo();
        kryo.register(int[].class);
        kryo.register(ArrayList.class);
        kryo.register(Player.class);
        kryo.register(Vector3.class);
        kryo.register(Packets.Packet01Message.class);
        kryo.register(Packets.Packet02Autorize.class);
        kryo.register(Packets.Packet03Createlobby.class);
        kryo.register(Packets.Packet04Findlobby.class);
        kryo.register(Packets.Packet05Lobbyinfo.class);
        kryo.register(Packets.Packet06Lobbylist.class);
        kryo.register(Packets.Packet07Joinlobby.class);
        kryo.register(Packets.Packet08Cancellobby.class);
        kryo.register(Packets.Packet09Lobbycreated.class);
        kryo.register(Packets.Packet10Givelinfo.class);
        kryo.register(Packets.Packet11Lobyback.class);
        kryo.register(Packets.Packet12Gamesessionstart.class);
        kryo.register(Packets.Packet13Gamesessionstate.class);
        kryo.register(Packets.Packet14Playerbuttons.class);
        kryo.register(Packets.Packet15Gamesessionend.class);
        kryo.register(Packets.Packet16Login.class);
        kryo.register(Packets.Packet17Register.class);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
   // handleInput();
        if (cnl.isAutorize()){
            gsm.set(new Gamescreen1(gsm));
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {

    }

    public Client getClient() {
        return client;
    }

    public void setAutorize(boolean autorize) {
        this.autorize = autorize;
    }
}
