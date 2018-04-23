package com.mygdx.game.States.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.State;
import com.mygdx.game.View.TestA.Animation;
import com.mygdx.game.View.TestA.Playeranim;
import com.mygdx.game.View.TestA.Shadows;
import com.mygdx.game.objects.Objects;
import com.mygdx.game.objects.Stone;
import com.mygdx.game.objects.ch1;
import com.badlogic.gdx.math.Rectangle;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by CooL on 14.02.2018.
 */
// Окно для теста
public class TestGameplay extends State {
    private boolean newid;
    private Texture back; // текстурка карты
    private ch1 character1;  //обьект логики игрового перса
    private ch1 character1att; //обьект атак игрового перса (думаем над реалезацией проверки нескольких ректенглов одного обьекта класса)
    private ch1 character2;
    private ch1 character2att;
    private Playeranim ch1anim; //обьект класса отрисовки перса
    private Playeranim ch2anim;
    private Rectangle testrect; //ректенгл карты
    public ArrayList<Animation> animlist1; //массив обьектов интерфейса отрисовки
    public ArrayList<Shadows> shadowlist;
    public ArrayList<Objects> objlist;  //массив обьектов интерфейса логики обьекта




    public TestGameplay(GameStateManager gsm) { //конструктор
        super(gsm);
        back = new Texture("test.png");
        camera = new OrthographicCamera() ;
        camera.setToOrtho(false,1280,720);
        character1 = new ch1(100,100,1,10,80,80,0,998);
        character1att= new ch1(0,0,50,5,2,80,0,998);
        character2 = new ch1(400,200,1,10,80,80,1,999);
        character2att= new ch1(0,0,50,5,2,80,1,999);
        ch1anim = new Playeranim();
        ch2anim = new Playeranim();
        testrect = new Rectangle(0,0,1459,350);
        createobjlist(); //переделать на паблик
        createanimlist();
        loadanimlist();
    }

    public List<Objects> createobjlist() {
        objlist = new ArrayList<Objects>();
        objlist.add(character1);
        objlist.add(new Stone(150,155,100,200,1));
        objlist.add(new Stone(300,20,200,100,2));
        objlist.add(new Stone(310,20,500,100,3));
        objlist.add(character1att);
        objlist.add(character2);
        objlist.add(character2att);
        return objlist;
    }

    private List<Animation> createanimlist() {
        animlist1 = new ArrayList<Animation>();
        animlist1.add(ch1anim);
        animlist1.add(ch2anim);
        shadowlist = new ArrayList<Shadows>();
        return animlist1;
    }



   private void loadanimlist(){ //загружаем прорисовки камням и тени
        for(int i=0;i<objlist.size();i++){
            if (objlist.get(i).getBodytype()==200){
                objlist.get(i).upanimlist(animlist1,objlist.get(i).getId());
            }
            if (objlist.get(i).getBodytype()!=50){
                shadowlist.add(new Shadows());
            }
        }
   }



    @Override
    public void update(float dt) {
       handleInput();
       character1.updatech(dt,character1att);
       character2.updatech(dt,character2att);
       collision();
       ch1anim.updatepos(character1);
       ch2anim.updatepos(character2);
       updateshadowlist();
       updateanimlist();
       updateobjlist();
       objcolupdate();
       camupdate(character1.getPosition().x, character1.getPosition().y);
    }

    @Override
    protected void handleInput() {

    }

    private void updateobjlist(){
        Iterator<Objects> iteratorobj = objlist.iterator();
        while (iteratorobj.hasNext()) {
            Objects e1 = iteratorobj.next();
            if (e1.isDead()){
                iteratorobj.remove();
            }
        }
        for(int i=0;i<objlist.size();i++) {
            if (objlist.get(i).getBodytype() == 200) {
                objlist.get(i).update();
            }
        }
    }

    private void updateanimlist(){ //сортируем для правильной отрисовки по Y
        Collections.sort(animlist1, new Comparator<Animation>() {
            @Override
            public int compare(Animation o1, Animation o2) {
                return compareTo(o1.getY(),o2.getY());
            }
        });
        Iterator<Animation> iteratoranim = animlist1.iterator();
        while (iteratoranim.hasNext()) {
            Animation e2 = iteratoranim.next();
            if (e2.isDead()){
                iteratoranim.remove();
            }
        }

        for(int i=0;i<objlist.size();i++) {   //присваиваем камням их отрисовки
            if (objlist.get(i).getBodytype() == 200) {
                for (int j=0;j<animlist1.size();j++){
                    if (objlist.get(i).getId()==animlist1.get(j).getId()){
                        objlist.get(i).updateanim(animlist1.get(j));
                    }
                }
            }
        }
    }

    private void updateshadowlist() {
    int j=0;
        for (int i = 0; i < objlist.size(); i++) {
            if ((objlist.get(i).getBodytype()<50)||(objlist.get(i).getBodytype()>99)){
                shadowlist.get(j).setDead(objlist.get(i).isDead());
                shadowlist.get(j).setX(objlist.get(i).getPosition().x + objlist.get(i).getBody().getWidth() / 2);
                shadowlist.get(j).setY(objlist.get(i).getPosition().y);
                shadowlist.get(j).setZ(objlist.get(i).getPosition().z);
                j++;
            }
        }

        Iterator<Shadows> iterators = shadowlist.iterator();
        while (iterators.hasNext()) {
            Animation e3 = iterators.next();
            if (e3.isDead()){
                iterators.remove();
            }
        }

    }

    public int compareTo(float o1,float o2){
        int res=0;
        if (o1<o2)
            res=1;
        if (o1==02)
            res=0;
        if (o1>o2)
            res=-1;
        return res;
    }



    private void objcolupdate() { //находим все пересечения по глубине, X,Z
      for (int i = 0; i < objlist.size()-1; i++) {
          for (int j = i+1; j < objlist.size(); j++) {
              if (((objlist.get(i).getPosition().y - objlist.get(i).getDEPTH() <= objlist.get(j).getPosition().y + objlist.get(j).getDEPTH()) && (objlist.get(i).getPosition().y + objlist.get(i).getDEPTH() >= objlist.get(j).getPosition().y - objlist.get(j).getDEPTH()))) {
                  if ((objlist.get(i).getPosition().x <= objlist.get(j).getPosition().x + objlist.get(j).getBody().getWidth()) && (objlist.get(i).getPosition().x + objlist.get(i).getBody().getWidth() >= objlist.get(j).getPosition().x)) {
                      if ((objlist.get(i).getPosition().z + objlist.get(i).getBody().getHeight() >= objlist.get(j).getPosition().z) && (objlist.get(i).getPosition().z <= objlist.get(j).getPosition().z + objlist.get(j).getBody().getHeight())) {
                          objinter(objlist.get(i), objlist.get(j)); //вызов метода обработки столкновения (передаем 2 обьекта)
                      }
                  }
              }
          }
      }
    }

    private void objinter(Objects a,Objects b) { //обрабатываем (не сделано)

        if ((a.getBodytype() == 50 || b.getBodytype() == 50) && (a.getBodytype() == 200 || b.getBodytype() == 200)&&(a.getId()!=b.getId())) {///////////рука-камень
            if (a.getBodytype() == 200) {
                if (b.getAttid().size() == 0) {
                    a.setHp(50);
                    b.setAttid(a.getId());
                    System.out.println("first hit a -50hp");
                }
                if (!b.getAttid().contains(a.getId())) {
                    a.setHp(50);
                    b.setAttid(a.getId());
                    System.out.println(" a -50hp");
                }
            }
            if (b.getBodytype() == 200) {
                if (a.getAttid().size() == 0) {
                    b.setHp(50);
                    a.setAttid(b.getId());
                    System.out.println("first hit b -50hp");
                }
                if (!a.getAttid().contains(b.getId())) {
                    b.setHp(50);
                    a.setAttid(b.getId());
                    System.out.println(" a -50hp");
                }
            }
        }

        if ((a.getBodytype() == 50 || b.getBodytype() == 50) && (a.getBodytype() == 1 || b.getBodytype() == 1)&&(a.getId()!=b.getId())) {///////////рука-игрок
            if (a.getBodytype() == 1) {
                if (b.getAttid().size() == 0) {
                    a.setHp(50);
                    a.setDamage(true);
                    b.setAttid(a.getId());
                    System.out.println("first hit a -50hp");
                }
                if (!b.getAttid().contains(a.getId())) {
                    a.setHp(50);
                    a.setDamage(true);
                    b.setAttid(a.getId());
                    System.out.println(" a -50hp");
                }
            }
            if (b.getBodytype() == 1) {
                if (a.getAttid().size() == 0) {
                    b.setHp(50);
                    b.setDamage(true);
                    a.setAttid(b.getId());
                    System.out.println("first hit b -50hp");
                }
                if (!a.getAttid().contains(b.getId())) {
                    b.setHp(50);
                    b.setDamage(true);
                    a.setAttid(b.getId());
                    System.out.println(" a -50hp");
                }
            }
        }
        if ((a.getBodytype() == 51 || b.getBodytype() == 51) && (a.getBodytype() == 1 || b.getBodytype() == 1)&&(a.getId()!=b.getId())) {///////////рукаlaunch-игрок
            if (a.getBodytype() == 1) {
                if (b.getAttid().size() == 0) {
                    a.setHp(50);
                    a.setLaunch(true);
                    a.setDz(b.getDz());
                    if (b.getRview()) {
                        a.setKx(b.getKx());
                    }
                    else {
                        a.setKx(-b.getKx());
                    }
                    if (a.getRview()==b.getRview()){
                        a.setJugler(false);
                    }
                    else {
                        a.setJugler(true);
                    }
                    b.setAttid(a.getId());
                    System.out.println("first hit a -50hp");
                }
                if (!b.getAttid().contains(a.getId())) {
                    a.setHp(50);
                    a.setLaunch(true);
                    a.setDz(b.getDz());
                    if (b.getRview()) {
                        a.setKx(b.getKx());
                    }
                    else {
                        a.setKx(-b.getKx());
                    }
                    if (a.getRview()==b.getRview()){
                        a.setJugler(false);
                    }
                    else {
                        a.setJugler(true);
                    }
                    b.setAttid(a.getId());
                    System.out.println(" a -50hp");
                }
            }
            if (b.getBodytype() == 1) {
                if (a.getAttid().size() == 0) {
                    b.setHp(50);
                    b.setLaunch(true);
                    b.setDz(a.getDz());
                    if (a.getRview()) {
                        b.setKx(a.getKx());
                    }
                    else {
                        b.setKx(-a.getKx());
                    }
                    if (b.getRview()==a.getRview()){
                        b.setJugler(false);
                    }
                    else {
                        b.setJugler(true);
                    }
                    a.setAttid(b.getId());
                    System.out.println("first hit b -50hp");
                }
                if (!a.getAttid().contains(b.getId())) {
                    b.setHp(50);
                    b.setLaunch(true);
                    b.setDz(a.getDz());
                    if (a.getRview()) {
                        b.setKx(a.getKx());
                    }
                    else {
                        b.setKx(-a.getKx());
                    }
                    if (b.getRview()==a.getRview()){
                        b.setJugler(false);
                    }
                    else {
                        b.setJugler(true);
                    }
                    a.setAttid(b.getId());
                    System.out.println(" a -50hp");
                }
            }
        }
        if ((a.getBodytype() == 51 || b.getBodytype() == 51) && (a.getBodytype() == 4 || b.getBodytype() == 4)&&(a.getId()!=b.getId())) {///////////рукаlaunch-игрокjugle
            if (a.getBodytype() == 4) {
                if (b.getAttid().size() == 0) {
                    a.setHp(50);
                    a.setDz(b.getDz());
                    if (b.getRview()) {
                        a.setKx(b.getKx());
                    }
                    else {
                        a.setKx(-b.getKx());
                    }
                    if (a.getRview()==b.getRview()){
                        a.setJugler(false);
                    }
                    else {
                        a.setJugler(true);
                    }
                    b.setAttid(a.getId());
                    System.out.println("first hit jugle a -50hp");
                }
                if (!b.getAttid().contains(a.getId())) {
                    a.setHp(50);
                    a.setLaunch(true);
                    a.setDz(b.getDz());
                    if (b.getRview()) {
                        a.setKx(b.getKx());
                    }
                    else {
                        a.setKx(-b.getKx());
                    }
                    if (a.getRview()==b.getRview()){
                        a.setJugler(false);
                    }
                    else {
                        a.setJugler(true);
                    }
                    b.setAttid(a.getId());
                    System.out.println(" a -50hp");
                }
            }
            if (b.getBodytype() == 4) {
                if (a.getAttid().size() == 0) {
                    b.setHp(50);
                    b.setDz(a.getDz());
                    if (a.getRview()) {
                        b.setKx(a.getKx());
                    }
                    else {
                        b.setKx(-a.getKx());
                    }
                    if (b.getRview()==a.getRview()){
                        b.setJugler(false);
                    }
                    else {
                        b.setJugler(true);
                    }
                    a.setAttid(b.getId());
                    System.out.println("first hit jugler b -50hp");
                }
                if (!a.getAttid().contains(b.getId())) {
                    b.setHp(50);
                    b.setLaunch(true);
                    b.setDz(a.getDz());
                    if (a.getRview()) {
                        b.setKx(a.getKx());
                    }
                    else {
                        b.setKx(-a.getKx());
                    }
                    if (b.getRview()==a.getRview()){
                        b.setJugler(false);
                    }
                    else {
                        b.setJugler(true);
                    }
                    a.setAttid(b.getId());
                    System.out.println(" a -50hp");
                }
            }
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

    public void collision(){

        if (character1.getBody().getX()<testrect.getX()) {
            character1.getBody().setX(testrect.getX());
            character1.setPosition(character1.getBody().getX(),character1.getBody().getY(),character1.getPosition().z);
        }
        if (character1.getBody().getX()>testrect.getWidth()+testrect.getX()-character1.getBody().getWidth()){
            character1.getBody().setX(testrect.getWidth()-character1.getBody().getWidth());
            character1.setPosition(character1.getBody().getX(),character1.getBody().getY(),character1.getPosition().z);
        }
        if (character1.getBody().getY()<testrect.getY()) {
            character1.getBody().setY(testrect.getY());
            character1.setPosition(character1.getBody().getX(),character1.getBody().getY(),character1.getPosition().z);
        }
        if (character1.getBody().getY()>testrect.getHeight()) {
            character1.getBody().setY(testrect.getHeight());
            character1.setPosition(character1.getBody().getX(),character1.getBody().getY(),character1.getPosition().z);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (character2.getBody().getX()<testrect.getX()) {
            character2.getBody().setX(testrect.getX());
            character2.setPosition(character2.getBody().getX(),character2.getBody().getY(),character2.getPosition().z);
        }
        if (character2.getBody().getX()>testrect.getWidth()+testrect.getX()-character2.getBody().getWidth()){
            character2.getBody().setX(testrect.getWidth()-character2.getBody().getWidth());
            character2.setPosition(character2.getBody().getX(),character2.getBody().getY(),character2.getPosition().z);
        }
        if (character2.getBody().getY()<testrect.getY()) {
            character2.getBody().setY(testrect.getY());
            character2.setPosition(character2.getBody().getX(),character2.getBody().getY(),character2.getPosition().z);
        }
        if (character2.getBody().getY()>testrect.getHeight()) {
            character2.getBody().setY(testrect.getHeight());
            character2.setPosition(character2.getBody().getX(),character2.getBody().getY(),character2.getPosition().z);
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
        for (int i = 0; i < animlist1.size(); i++) {
            animlist1.get(i).finalrender(sb);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        back.dispose();
    }


}
