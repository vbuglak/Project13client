package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.View.TestA.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CooL on 14.02.2018.
 */

public class ch1 extends Character implements Objects{
    private Vector3 position;
    private Vector3 speed;
    private Rectangle body;
    private int bodytype;
    private int DEPTH;
    private int id=999;
    private int state;      // 1-стоим(stay) 2-идем(walk) 3-прыгаем(jump) 4- бежим(run) 5-атакуем 6-defend 7-crouch 8-injured
    private int control[][]={
            { 19,21,20,22,148,149,150 },
            { 51,29,47,32,35,36,38 }
};
    private int number=0;
    private int numberhit;
    private int injhp=0;
    public ArrayList<Integer> attid;
    private static final float YSPEED = 1;
    private final static float XSPEED= 2;
    private final static float RUNSPEED= 6;
    private final static float JUMPSPEED= 7;
    private final static float GRAVITY= 0.3f;
    private boolean isjump = false;
    private boolean ismove = false;
    private boolean isrunr = false;
    private boolean isrunl = false;
    private boolean rjump = false;
    private boolean rdjump = false;
    private boolean stjump = false;
    private boolean isattack = false;
    private boolean isattackst = false;
    private boolean isdefendst = false;
    private boolean combo = false;
    private boolean crouch = false;
    private boolean iscrouchst=false;
    private boolean runattack=false;
    private boolean dead=false;
    private boolean damage=false;
    private boolean defend=false;
    private boolean startinj=false;
    private boolean launch=false;
    private boolean juglest=false;
    private boolean fallst=false;
    private boolean fall=false;
    private boolean jugler=false;
    private boolean wakeinvul=false;
    private boolean skillst=false;
    private boolean skill1=false;
    private boolean kpress=false;
    private float dz=0;
    private float kx=0;
    float startrun=0;
    float keytime=0;
    float keytime1=0;
    float keytime2=0;
    float startattime=0;
    float hp;

    long defendstarttime=0;
    long injstarttime=0;
    long startcrtime=0;
    long fallstarttime=0;
    long wakestarttime=0;
    long startsktime=0;


    public ch1(int x, int y,int bodyt,int D,float bodyw,float bodyh,int plnumb,int idp) {

        position = new Vector3(x, y, 0);
        speed = new Vector3(3, 2, 0);
        state = 1;  // 1-стоим(stay) 2-идем(walk) 3-прыгаем(jump) 4- бежим(run) 5-атакуем 6-defend 7-crouch 8-injuredм
        body = new Rectangle(x,y,bodyw,bodyh);
        bodytype = bodyt;
        DEPTH = D;
        hp=5000;
        attid = new ArrayList<Integer>();
        number=plnumb;
        id=idp;
    }



    @Override
    public void updatech(float dt,ch1 att) {
        if (hp<=0){
            dead=true;
        }
        att.speed.x = 0;
        att.speed.y = 0;
        att.rview=rview;
        statecontrol(dt,att);
        handleInput(dt);
        comboinput();
        body.x = position.x;
        body.y = position.y;
    }


    public void comboinput() {

        if (Gdx.input.isKeyJustPressed(control[number][6])) {
            keytime1 = TimeUtils.nanoTime();
        }

        if (TimeUtils.nanoTime() - keytime1 < 300000000)  {
            if((Gdx.input.isKeyJustPressed(control[number][1]))||(Gdx.input.isKeyJustPressed(control[number][3]))){
                keytime2 = TimeUtils.nanoTime();
            }
        }
        if (TimeUtils.nanoTime() - keytime2 < 300000000){
            if ((Gdx.input.isKeyJustPressed(control[number][4]))){
                skill1=true;
            }
        }
    }

    private void statecontrol(float dt, ch1 att) {  // 1-стоим(stay) 2-идем(walk) 3-прыгаем(jump) 4- бежим(run) 5-атакуем 6-defend 7-crouch 8-injured

        if (state==1){     //////////STAY
            att.bodytype=99;
            stay();
            if (ismove){
                state = 2;
            }
            if (isjump){
                state = 3;
                dz=JUMPSPEED;
            }
            if (isrunr||isrunl){
                state = 4;
            }
            if (isattack){
                state = 5;
            }
            if (defend){
                state = 6;
            }
            if (damage){
                state = 8;
            }
            if (launch){
                state = 9;
            }
            if (fall){
                state = 10;
            }
            if (skill1){
                state = 11;
            }
    }
        if (state==2){///////////WALK
            att.bodytype=99;
            bodytype=1;
            walk();
            if (!ismove){
                state = 1;
            }
            if (isjump){
                state = 3;
                dz=JUMPSPEED;
            }
            if (isrunr||isrunl){
                state = 4;
            }
            if (isattack){
                state = 5;
            }
            if (defend){
                state = 6;
            }
            if (damage){
                state = 8;
            }
            if (launch){
                state = 9;
            }
            if (fall){
                state = 10;
            }
            if (skill1){
                state = 11;
            }
        }
        if (state==3){          //////////////JUMP
            jump(dt,att);
            if (!isjump){
                state=1;
            }
            if (damage){
                state = 8;
            }
            if (launch){
                state = 9;
            }
            if (defend){
                defend=false;
                crouch=false;
            }
        }

        if (state==4){         //////////RUN
            bodytype=1;
            run();
            if (!isrunl&&!isrunr){
                state = 1;
            }
            if (!ismove){
                isrunl=false;
                isrunr=false;
                state = 1;
            }
            if (isjump){
                rjump=true;
                state = 3;
                isrunl=false;
                isrunr=false;
                dz=JUMPSPEED;
            }
            if (isattack){
                runattack = true;
                state=5;
            }
            if (crouch){
                state=7;
            }
            if (damage){
                state = 8;
            }
            if (launch){
                state = 9;
            }
            if (fall){
                state = 10;
            }
            if (skill1){
                state = 11;
            }
        }

        if (state==5){      //////////////ATTACK
            attack(dt,att);
            if (!isattack){
                state = 1;
            }
            if (damage){
                state = 8;
            }
            if (launch){
                state = 9;
            }
            if (fall){
                state = 10;
            }
            if (skill1){
                state = 11;
            }
        }
        if (state==6){      //////////////DEfend
            defend();
            if (!defend){
                state = 1;
            }
            if (skill1){
                state = 11;
            }
            if (isjump){
                isjump=false;
            }
            if (isattack){
                isattack=false;
            }
        }
        if (state==7){      //////////////crouch
            crouch();
            if (!crouch){
                state = 1;
            }
            if (skill1){
                state = 11;
            }
        }
        if (state==8){  ///////////////injured
            att.bodytype=99;
            injured();
            damage=false;
            skill1=false;
            if (launch){
                state = 9;
            }
            if (fall){
                state = 10;
            }
        }
        if (state==9){  ///////////////jugle
            att.bodytype=99;
            skill1=false;
            jugle();
        }
        if (state==10){  //////////fall
            att.bodytype=99;
        fall();
        }
        if (state==11){  //////////skill1
            skill1(att);
            if (!skill1){
                state=1;
            }
        }
    }



    @Override
    public void handleInput(float dt) {
        if (startrun<5) {
            startrun += dt;   //////!!!!!!!optimize
        }
        else {
            startrun=5;
        }


        if ((!Gdx.input.isKeyPressed(control[number][1]))&&(!Gdx.input.isKeyPressed(control[number][3]))&&(!Gdx.input.isKeyPressed(control[number][0]))&&(!Gdx.input.isKeyPressed(control[number][2]))){
            ismove = false;
            isrunl = false;
            isrunr = false;
        }
        if (Gdx.input.isKeyPressed(control[number][1])) {
            speed.x = -XSPEED;
            if ((!isattack)&&(!crouch)&&(!juglest)&&(!fallst)&&(!startinj)) {
                rview = false;
            }
            ismove = true;
            if (((startrun > 0.08) && (startrun < 0.12)) && (!isjump) || ((isrunl) && (!isrunr))) {
                isrunl = true;
            }
            startrun = 0;
        }

        if (Gdx.input.isKeyPressed(control[number][3])) {
            speed.x = XSPEED;
            if ((!isattack)&&(!crouch)&&(!juglest)&&(!fallst)&&(!startinj)) {
                rview = true;
            }
            ismove = true;
            if (((startrun > 0.08) && (startrun < 0.12)) && (!isjump) || ((isrunr) && (!isrunl))) {
                isrunr = true;
            }
            startrun = 0;

       }
        if (Gdx.input.isKeyPressed(control[number][2])) {
            speed.y = -YSPEED;
            ismove = true;
        }
        if (Gdx.input.isKeyPressed(control[number][0])) {
            speed.y = YSPEED;
            ismove = true;
        }
        if (Gdx.input.isKeyJustPressed(control[number][5])) {

                isjump=true;

        }

        if (Gdx.input.isKeyJustPressed(control[number][4])) {

                isattack=true;

        }
        if (Gdx.input.isKeyPressed(control[number][6])){

                defend=true;
                crouch=true;

        }
    }




    @Override
    public void stay() {
        if (wakeinvul){
            wakestarttime = TimeUtils.nanoTime();
            wakeinvul = false;
            bodytype=49;
        }
        if ((TimeUtils.nanoTime() - wakestarttime < 1000000000)){
            bodytype=49;
        }
        else {
            bodytype=1;
        }
        speed.x=0;
        speed.y=0;
    }

    @Override
    public void walk() {
        position.add(speed.x,speed.y,0);
        speed.x=0;
        speed.y=0;
    }

    @Override
    public void jump(float dt,ch1 att) {

    if ((isrunr)||(isrunl)||(rjump)) {
        isrunl = false;
        isrunr = false;
        rjump = true;
        if ((rview)&&(!stjump)){
            rdjump = true;
            stjump = true;
        }
        if ((!rview)&&(!stjump)){
            rdjump = false;
            stjump = true;
        }

        if ((isjump) && (position.z >= 0)&&(!isattack)) {
            dz-=GRAVITY;
            if ((rview)&&(rdjump)) {
                position.add(RUNSPEED, speed.y / 2, dz);
            }
                if ((!rview)&&(rdjump)){
                    position.add(RUNSPEED-4, speed.y / 2, dz);
                }

            if ((!rview)&&(!rdjump)) {
                position.add(-RUNSPEED, speed.y / 2, dz);
            }
            if ((rview)&&(!rdjump)){
                position.add(-RUNSPEED+4, speed.y / 2, dz);
            }
        } else if ((isjump)&&(!isattack)) {
            isjump = false;
            position.z = 0;
            rjump=false;
            stjump=false;
        }
        if ((isjump) && (position.z >= 0)&&(isattack)) {
            dz-=GRAVITY;
            att.bodytype=51;
            att.dz=2;
            att.kx=3;
            if ((rview)&&(rdjump)) {
                position.add(RUNSPEED, speed.y / 2, dz);
                att.setPosition(position.x + 60, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            }
            if ((!rview)&&(rdjump)){
                rview=true;
            }

            if ((!rview)&&(!rdjump)) {
                position.add(-RUNSPEED, speed.y / 2, dz);
                att.setPosition(position.x + 10, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            }
            if ((rview)&&(!rdjump)){
                rview=false;
            }
        } else if ((isjump)&&(isattack)) {
            isjump = false;
            position.z = 0;
            dz=0;
            rjump=false;
            stjump=false;
            isattack=false;
            att.attid.clear();
            att.isattack = false;
            att.damage = false;
            att.setPosition(-9999, -9999, -9999);
            att.setBody(att.getPosition().x, att.getPosition().y);
        }
    }
        else {
        if ((isjump) && (position.z >= 0) && (!rjump) && (isattack)) {//jump at + jump
            dz-=GRAVITY;

            att.bodytype=51;
            att.dz=-50;
            att.kx=1;
            if (rview) {
                position.add(2, 0, dz);
                att.setPosition(position.x + 60, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            } else {
                position.add(-2, 0, dz);
                att.setPosition(position.x + 10, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            }
        } else if ((isjump)&&(!rjump)&&(isattack)) {
            isattack=false;
            isjump = false;
            position.z = 0;
            dz=0;
            att.attid.clear();
            att.isattack = false;
            att.damage = false;
            att.setPosition(-9999, -9999, -9999);
            att.setBody(att.getPosition().x, att.getPosition().y);
        }
        if ((isjump) && (position.z >= 0) && (!rjump)&&(!isattack)) {
            dz-=GRAVITY;
            position.add(speed.x, speed.y, dz);
        } else if ((isjump)&&(!rjump)&&(!isattack)) {
            isjump = false;
            position.z = 0;
            dz=0;
        }
    }
        }


    @Override
    public void run() {
        if (rview) {
            position.add(RUNSPEED, speed.y / 2, 0);
            } else {
                position.add(-RUNSPEED, speed.y / 2, 0);
            }
        if (isrunr && isrunl) {
            isrunr = false;
            isrunl = false;
        }
        if (rview&&isrunl){
            isrunr = false;
            isrunl = false;
        }
        if (!rview&&isrunr){
            isrunr = false;
            isrunl = false;
        }
    }


    @Override
    public void attack(float dt,ch1 att) {
        if (!isattackst) {
            startattime = TimeUtils.nanoTime();
            isattackst = true;
            numberhit=1;
            att.bodytype=50;
        }
        if ((TimeUtils.nanoTime() - startattime < 1000000000/3 )&&(!runattack)) { ////stay attack

            if (Gdx.input.isKeyJustPressed(control[number][4])) {
                combo=true;
            }
            if (rview){
                position.add(1, 0, 0);
            }
            else {
                position.add(-1, 0, 0);
            }
            if ((rview)&&(TimeUtils.nanoTime() - startattime > 1000000000/6 )) {
                att.setPosition(position.x + 60, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            } else if (TimeUtils.nanoTime() - startattime > 1000000000/6 ) {
                att.setPosition(position.x + 10, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            }
            att.isattack = true;

        }
        else if ((!runattack)&&(combo)&&(numberhit!=3)){
            combo=false;
            startattime=TimeUtils.nanoTime();
            att.attid.clear();
            numberhit++;
            if(numberhit==3){
                att.bodytype=51;
                att.dz=7;
                att.kx=1;
            }
        }


           else if ((!runattack)&&(!combo)||(numberhit==3)){
            isattack = false;
            isattackst = false;
            runattack = false;
            att.bodytype=50;
            att.attid.clear();
            att.isattack = false;
            att.damage = false;
            att.setPosition(-9999, -9999, -9999);
            att.setBody(att.getPosition().x, att.getPosition().y);
        }

        if ((TimeUtils.nanoTime() - startattime < 1000000000 ) && (runattack)) { ////run attack
            att.bodytype=51;
            att.dz=2;
            att.kx=10;
            att.isattack = true;
            if (rview) {
                att.setPosition(position.x + 60, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            } else {
                att.setPosition(position.x + 10, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            }


            if (rview) {
                position.add(RUNSPEED, 0, 0);
            }
            if (!rview) {
                position.add(-RUNSPEED, 0, 0);
            }
        } else if(runattack) {
                isattack = false;
                isattackst = false;
                runattack = false;
                att.attid.clear();
                att.isattack = false;
                att.damage = false;
                att.setPosition(-9999, -9999, -9999);
                att.setBody(att.getPosition().x, att.getPosition().y);
                att.bodytype=50;
            }



    }

    public void defend() {
        if (!isdefendst) {
            defendstarttime = TimeUtils.nanoTime();
            isdefendst = true;
        }
        if ((TimeUtils.nanoTime() - defendstarttime < 1000000000)) {
            bodytype = 2;
        }
        else {
            defend = false;
            isdefendst=false;
            bodytype = 1;
            crouch=false;
        }
    }

    public void crouch(){
        if (!iscrouchst) {
            startcrtime = TimeUtils.nanoTime();
            iscrouchst = true;
        }
        if (TimeUtils.nanoTime() - startcrtime < 1000000000 ) {
            bodytype=3;
            if (rview) {
                position.add(RUNSPEED, 0, 0);
            } else {
                position.add(-RUNSPEED, 0, 0);
            }
        } else {
            bodytype=1;
            iscrouchst=false;
            crouch=false;
            defend=false;
        }
    }

    public void injured(){
        if (!startinj) {
            injstarttime = TimeUtils.nanoTime();
            startinj = true;
        }
        if (TimeUtils.nanoTime() - injstarttime < 1000000000) {
            if (damage){
                startinj = false;
                injhp++;
            }
            damage=false;
            if (injhp>4){
                damage=false;
                state = 10;
                startinj = false;
                injhp=0;
                fall=true;
            }
        }
        else {
            damage=false;
            state = 1;
            startinj = false;
            injhp=0;
        }
    }

    public void jugle(){
    bodytype=4;
    injhp=0;
    if (!juglest) {
        position.add(kx, 0, dz);
        juglest=true;
    }
        if (position.z>0) {
          dz-=GRAVITY;
          position.add(kx,0,dz);
        }
        else {
            juglest=false;
            bodytype=1;
            launch=false;
            fall=true;
            state=10;
            position.z=0;
            startinj=false;
        }
    }

    public void fall(){
        bodytype=5;
        injhp=0;
        if (!fallst) {
            fallstarttime = TimeUtils.nanoTime();
            fallst = true;
        }
        if ((TimeUtils.nanoTime() - fallstarttime < 1000000000)){


            if ((Gdx.input.isKeyPressed(control[number][1])||(Gdx.input.isKeyPressed(control[number][3])))&&(TimeUtils.nanoTime() - fallstarttime > 800000000 )) {
                if (Gdx.input.isKeyPressed(control[number][1])){
                    rview=false;
                }
                if (Gdx.input.isKeyPressed(control[number][3])){
                    rview=true;
                }
                fall=false;
                fallst=false;
                state=7;
                bodytype=3;
                crouch=true;
            }
        }
        else{
            fall=false;
            fallst=false;
            isjump=false;
            isrunr=false;
            isrunl=false;
            state=1;
            bodytype=49;
            wakeinvul=true;
        }
    }

    public void skill1(ch1 att){

        if (!skillst) {
            startsktime = TimeUtils.nanoTime();
            skillst = true;
            numberhit=1;
            att.bodytype=51;
            att.dz=5;
            att.kx=1;
        }
        if (TimeUtils.nanoTime() - startsktime < 1000000000/2 ) {

            if (Gdx.input.isKeyJustPressed(control[number][4])) {
                combo=true;
            }
            if (rview){
                position.add(1, 0, 0);
            }
            else {
                position.add(-1, 0, 0);
            }
            if ((rview)&&(TimeUtils.nanoTime() - startsktime > 1000000000/4 )) {
                att.setPosition(position.x + 60, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            } else if (TimeUtils.nanoTime() - startsktime > 1000000000/4 ) {
                att.setPosition(position.x + 10, position.y, position.z);
                att.setBody(att.getPosition().x, att.getPosition().y);
            }
            att.isattack = true;

        }
        else if ((combo)&&(numberhit!=2)){
            combo=false;
            startsktime=TimeUtils.nanoTime();
            att.attid.clear();
            numberhit++;
            if(numberhit==2){
                att.bodytype=51;
                att.dz=7;
                att.kx=1;
            }
        }


        else if ((!combo)||(numberhit==2)){
            skillst = false;
            runattack = false;
            att.bodytype=50;
            att.attid.clear();
            att.isattack = false;
            att.damage = false;
            att.setPosition(-9999, -9999, -9999);
            att.setBody(att.getPosition().x, att.getPosition().y);
            state=1;
            skill1=false;
        }
    }

///////////////////////////////////////////////////////////////
    @Override
    public List<Animation> upanimlist(List<Animation> animlist, int i) { //не используется в этом классе (возможно переделать под общий случай)
        return null;
    }
    @Override
    public void updateanim(Animation oa) { //не используется в этом классе (возможно переделать под общий случай)

    }
    @Override
    public void update() { //метод интерфейса (для перехода на общий случай)

    }
/////////////////////////////////////////////////////////////////



    public boolean isDamage() {
        return damage;
    }
    @Override
    public boolean isDead() {
        return dead;
    }
    public int getBodytype() {
        return bodytype;
    }
    public Rectangle getBody() {
        return body;
    }
    public boolean getRview(){
        return rview;
    }
    public Vector3 getPosition() {
        return position;
    }
    public int getState() {
        return state;
    }
    public int getDEPTH() {
        return DEPTH;
    }
    @Override
    public int getId() {
        return id;
    }
    public float getDz() {
        return dz;
    }
    public float getKx() {
        return kx;
    }
    public ArrayList<Integer> getAttid() {
        return attid;
    }
    public boolean isCombo() {
        return combo;
    }
    public boolean isRunattack() {
        return runattack;
    }
    public boolean isAttack() {
        return isattack;
    }
    public boolean isRjump() {
        return rjump;
    }
    public boolean isJugler() {
        return jugler;
    }




    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }
    public void setBody(float bodyx,float bodyy) {
        this.body.x = bodyx;
        this.body.y = bodyy;
    }
    public void setHp(float hp) {
        this.hp = this.hp - hp;
    }
    public void setDamage(boolean damage) {
        this.damage = damage;
    }
    public void setAttid(int id) {
        attid.add(id);
    }
    public void setLaunch(boolean launch) {
        this.launch = launch;
    }
    public void setJugler(boolean jugler) {
        this.jugler = jugler;
    }
    public void setDz(float dz) {
        this.dz = dz;
    }
    public void setKx(float kx) {
        this.kx=kx;
    }
}

