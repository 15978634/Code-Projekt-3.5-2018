package com.company;

import java.lang.String;

public class Protocol {

    private int read;

    private byte id;
    private byte client_id;
    private byte[][] big_board = new byte[3][3];
    private byte[][] cells = new byte[9][9];
    private byte[] active_field = new byte[2];

    private byte[] coordinates = new byte[2];

    private byte error_code;

    public byte getId(){
        return  id;
    }
    public byte getClient_id(){
        return client_id;
    }
    public byte[][] getBig_board(){
        return big_board;
    }
    public byte[][] getCells(){
        return cells;
    }
    public byte[] getActive_field(){
        return active_field;
    }
    public byte[] getCoordinates(){
        return coordinates;
    }
    public byte getError_code() {
        return error_code;
    }


    public void setByteArray(String information){
        byte[] information_byte = new byte[information.length()];
        for(int i = 0; i < information_byte.length; i++) information_byte[i] = (byte)information.charAt(i);

        id = information_byte[0];

        switch (id){
            case 'I':
                client_id = information_byte[2];

                read = 4;
                for (int y = 0; y < 3; y++){
                    for (int x = 0; x < 3; x++){
                        big_board[x][y] = information_byte[read];
                        read++;
                    }
                }

                read++;
                for (int y = 0; y < 9; y++){
                    for (int x = 0; x < 9; x++){
                        cells[x][y] = information_byte[read];
                        read++;
                    }
                }

                read++;
                for (int c = 0; c < 2; c++){
                    active_field[c] = information_byte[read];
                    read++;
                }

                break;

            case 'W':
                client_id = information_byte[2];

                break;

            case 'E':
                error_code = information_byte[2];

                break;
        }

        System.out.println("Test");
    }
}
