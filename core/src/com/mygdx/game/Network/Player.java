package com.mygdx.game.Network;

import com.badlogic.gdx.math.Vector3;
import com.esotericsoftware.kryonet.Connection;


/**
 * Created by CooL on 15.03.2018.
 */

public class Player {
    public int characterid;
    public Vector3 position;
    public boolean rview;
    public int state;
    public int cid;
    public boolean attackview;

    public Player() {
    }

    public Player(int characterid, Vector3 position, boolean rview, int state, int id,boolean arview) {
        this.characterid = characterid;
        this.position = position;
        this.rview = rview;
        this.state = state;
        this.cid = id;
        this.attackview = arview;
    }
}
