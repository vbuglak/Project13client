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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Network.Connect;
import com.mygdx.game.Project13class;
import com.mygdx.game.States.Test.TestGameplay;

/**
 * Created by CooL on 14.02.2018.
 */
// Класс окна меню
public class MenuState extends State {
    private Texture background;
    private TextButton button1;
    private TextButton button2;
    private Stage stage;
    private Skin skin;
    private Table table;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("back.png");
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        button1 = new TextButton("Test gameplay", skin, "default");
        button1.setHeight(50);
        button1.setWidth(500);
        button2 = new TextButton("Start game", skin, "default");
        button2.setHeight(50);
        button2.setWidth(500);
        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gotoscreen(1);
            }
        });
        button2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gotoscreen(2);
            }
        });
        table.padTop(300);
        table.add(button1).padBottom(30);
        table.row();
        table.add(button2);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    private void gotoscreen(int i){

        if (i==1){
            gsm.set(new TestGameplay(gsm));
        }
        if (i==2){
            gsm.set(new Connect(gsm));
        }
    }
    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
    handleInput();
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
        background.dispose();

    }
}
