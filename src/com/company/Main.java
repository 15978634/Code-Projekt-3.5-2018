package com.company;

import java.net.InetAddress;

public class Main {
private static Board board;
private static Protocol protocol;
private static Connection con;
private static String incomingMessage;
private static byte[] leavingMessage;
private static String url;
private static boolean GameRunning;

    public static void setGameRunning(boolean r){
        GameRunning = r;
    }

    public static void main(String[] args) {
        url = "127.0.0.1";
        connectToServer(url);
        board = new Board();
        protocol = new Protocol();

        startGame();
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
        byte xcoord;
        byte ycoord;
        GameRunning = true;
        while(GameRunning){
            incomingMessage = con.receiveMessage();
            if(protocol.getId()=='I'){
                System.out.println("Information received!");
                protocol.setByteArray(incomingMessage);
                board.update(protocol.getBig_board(),protocol.getCells(),protocol.getActive_field());



                leavingMessage[0]='M';
                leavingMessage[1]=(byte)0xFF;
                leavingMessage[2]= xcoord;
                leavingMessage[3]= ycoord;
                leavingMessage[4]= (byte)0xFF;
                con.sendMessage(leavingMessage);

            }
            if(protocol.getId()=='E'){
                System.out.println("Error message from Server received!");
            }
            if(protocol.getId()=='W'){
                System.out.println("Win message received");
                System.out.print("Player: ");
                System.out.print(protocol.getClient_id());
                System.out.println(" won!");
                GameRunning = false;
            }
        }
    }
}
