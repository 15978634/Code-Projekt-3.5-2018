package com.company;

import java.net.InetAddress;

public class Main {
private static Board board;
private static Protocol protocol;
private static Connection con;
private static GameLogic gameLogic;
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
        gameLogic = new GameLogic(board);

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
            protocol.setByteArray(incomingMessage);
            if(protocol.getId()=='I'){
                System.out.println("Information received!");
                board.update(protocol.getBig_board(),protocol.getCells(),protocol.getActive_field());
                gameLogic.setPlayer((byte)(protocol.getClient_id()+1));
                byte [] coords = Main.calculateHighestValue();
                xcoord = coords[0];
                ycoord = coords[1];
                leavingMessage = new byte[5];
                leavingMessage[0]='M';
                leavingMessage[1]=(byte)'A';
                leavingMessage[2]= xcoord;
                leavingMessage[3]= ycoord;
                leavingMessage[4]= (byte)'A';
                con.sendMessage(leavingMessage);

            }
            if(protocol.getId()=='E'){
                System.out.println("Error message from Server received!");
                System.out.println(protocol.getError_code());

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
    private static byte[] calculateHighestValue(){
        byte[] coords = new byte[2];
        double highestValueYet = -1001;
        double[][] values = gameLogic.getValues();
            for(byte i = 0; i<= 8;i++){
                for(byte ii = 0; ii<=8;ii++){
                    if(highestValueYet<values[i][ii]){
                        highestValueYet = values[i][ii];
                        coords[0]=i;
                        coords[1]=ii;
                    }
                }
            }
        return coords;
    }
}
