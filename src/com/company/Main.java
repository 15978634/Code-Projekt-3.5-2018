package com.company;

import java.net.InetAddress;

public class Main {
private static Board board;
private static Protocol protocol;
private static Connection con;
private static String incomingMessage;
private static String leavingMessage;
private static String url;
private static boolean GameRunning;

    public static void setGameRunning(boolean r){
        GameRunning = r;
    }

    public static void main(String[] args) {
        url = "127.0.0.1";
        connectToServer(url);
        board = new Board();

    }
    private static void connectToServer(String url){
        try{
            con = new Connection(InetAddress.getByName(url));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void startGame(){
        GameRunning = true;
        while(GameRunning){

        }
    }
}
