package com.company;
import java.net.*;
import java.io.*;

public class Connection {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private int port = 3141;

    //Constructor uses default port (3141) and url as a variable

    public Connection(InetAddress url) {
            try {
            socket = new Socket(url, this.port);
            System.out.println("Socket created! ");

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream());
        }
        catch (SocketException e1){
            e1.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Connected to Server! ");
    }

    //message needs to be converted to a byte array before sending

    public void sendMessage (byte[] msg){
        char[] charArray = new char[msg.length];
        for(int i = 0; i < msg.length; i++) if(msg[i] == -1) charArray[i] = 255; else charArray[i] = (char)msg[i];
        output.write(charArray);
        output.flush();
        System.out.println("Message sent!");
    }

    //messages are received as strings, translation in the protocol class

    public String receiveMessage(){
        char[] charBuffer = new char[120];
        String info;
        try {
            System.out.println("Awaiting message!");
            input.read(charBuffer);
            info = new String(charBuffer);
            return info;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Receiving of message failed.");
        return null;
    }
}