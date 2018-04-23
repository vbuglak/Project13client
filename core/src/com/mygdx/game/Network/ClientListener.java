package com.mygdx.game.Network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.Network.Packets;
import com.mygdx.game.Network.Player;
import com.mygdx.game.States.FindLobby;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CooL on 13.03.2018.
 */

public class ClientListener extends Listener{
    public Client client;
    public boolean autorize;
    public boolean lobbycreated=false;
    public boolean lobbydeath=false;
    public boolean startsession=false;
    public boolean sessionend=false;
    public int clientid;
    public int lobbycreatedid;
    public int[] lobbyid=null;
    public int infocrid=0;
    public List<Integer> infoother=null;
    public List<Player> playersinfo= new ArrayList<Player>();



    public void init(Client client){
        this.client = client;
    }

    public ClientListener() {
       autorize=false;

    }

    @Override
    public void connected(Connection c) {
        System.out.println("Client: Есть контакт!");
        Packets.Packet01Message firstMessage = new Packets.Packet01Message();
        firstMessage.message = "";
        client.sendUDP(firstMessage);
    }

    @Override
    public void disconnected(Connection c) {
        System.out.println("Client: Отлетел очередняра");
    }

    @Override
    public void received(Connection c, Object o) {
        if (o instanceof Packets.Packet01Message){
            Packets.Packet01Message p = (Packets.Packet01Message) o;
            System.out.println("сервер пишет:" + p.message);
        }
        if (o instanceof Packets.Packet02Autorize){
            Packets.Packet02Autorize p = (Packets.Packet02Autorize) o;
           autorize = p.autorize;
           clientid=p.id;
        }
        if (o instanceof Packets.Packet05Lobbyinfo){
            Packets.Packet05Lobbyinfo p = (Packets.Packet05Lobbyinfo) o;
            FindLobby.connectlobby = true;
            infocrid=p.idcreator;
            infoother=p.otherid;
        }
        if (o instanceof Packets.Packet06Lobbylist){
            Packets.Packet06Lobbylist p = (Packets.Packet06Lobbylist) o;
            System.out.println("пришел лоби лист");
            lobbyid = p.id;

        }
        if (o instanceof Packets.Packet08Cancellobby){
            System.out.println("лоби сдохло");
            lobbydeath=true;

        }
        if (o instanceof Packets.Packet09Lobbycreated){
            Packets.Packet09Lobbycreated p = (Packets.Packet09Lobbycreated) o;
            System.out.println("лоби создано");
            lobbycreatedid=p.id;
            lobbycreated=true;
            lobbydeath=false;
        }
        if (o instanceof Packets.Packet13Gamesessionstate){
            Packets.Packet13Gamesessionstate p = (Packets.Packet13Gamesessionstate) o;
            playersinfo=p.characterinfo;
            startsession=true;
        }
        if (o instanceof Packets.Packet15Gamesessionend){
           sessionend=true;
           startsession=false;
            System.out.println("пришел конец");
        }
        if (o instanceof Packets.Packet16Login){
            Packets.Packet16Login p = (Packets.Packet16Login) o;
            if (p.statusinfo==0){
                Connect.status.setText("Login ERROR");
            }
            if (p.statusinfo==1){
                Connect.status.setText("Password ERROR");
            }
            if (p.statusinfo==2){

            }
        }
        if (o instanceof Packets.Packet17Register){
            Packets.Packet17Register p = (Packets.Packet17Register) o;
            if (p.statusinfo==0){
                Connect.status.setText("Login already exist");
            }
            if (p.statusinfo==1){
                Connect.status.setText("Password ERROR");
            }
            if (p.statusinfo==2){
                Connect.status.setText("Account registered success");
            }
        }
    }

    public int[] getLobbyid() {
        return lobbyid;
    }

    public int getLobbycreatedid() {
        return lobbycreatedid;
    }

    public boolean isAutorize() {
        return autorize;
    }

    public boolean isLobbycreated() {
        return lobbycreated;
    }

    public int getInfocrid() {
        return infocrid;
    }

    public List<Integer> getInfoother() {
        return infoother;
    }

}
