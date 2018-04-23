package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by CooL on 14.02.2018.
 */
// Класс в котором реализованы методы переключения окон(состояний)
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }
    public void push(State state) {
        states.push(state);
    }
    public void pop() {
        states.pop().dispose();
    }
    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
