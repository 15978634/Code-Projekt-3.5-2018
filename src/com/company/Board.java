package com.company;

import java.util.Arrays;

public class Board {
    private byte[][] fields;                                    //Gibt an welches feld wem gehört
    private byte[][] cells;
    private byte[] activeField;
    private boolean[][] allowedCells = new boolean[9][9];

    public boolean[][] getAllowedCells(){
        for(int i = 0; i < 9; i++) for(int j = 0; j < 9; j++) allowedCells[i][j] = false;
        checkAllowedCells();
        return this.allowedCells;
    }
    public byte[][] getCells(){
        return this.cells;
    }
    public byte[][] getFields(){
        return this.fields;
    }

    public Board(){
    }
    public void update(byte[][] fields, byte[][] cells, byte[] activeField){
        this.fields = fields;
        this.cells = cells;
        this.activeField = activeField;
    }
    public boolean checkUsefulPairs(byte xcoord, byte ycoord, byte player, byte[][] cells){
        /*
        only checks for pairs in useful locations for rows:
        not:    but:    also: does work
        -x-     x--     x--
        x--     -x-     ---
        ---     ---     x--
        code is written the same way as below
         */
        //player x = 1 player o = 2
        int cellNumber;
        cellNumber = xcoord%3+(ycoord%3)*3;
        switch (cellNumber){
            case 0:
                if(((cells[xcoord+1][ycoord]== player) && (cells[xcoord+2][ycoord]==0)) || ((cells[xcoord+2][ycoord] == player )&&(cells[xcoord+1][ycoord]== 0))){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord+1]== player)||(cells[xcoord][ycoord+2]==player)){
                        return true;
                    }
                    else {
                        if(((cells[xcoord+1][ycoord+1]== player)&&(cells[xcoord+2][ycoord+2]==0))||((cells[xcoord+2][ycoord+2]==player)&&(cells[xcoord+1][ycoord+1]== 0))){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }

                }
            case 1:
                if(((cells[xcoord-1][ycoord]== player)&&(cells[xcoord+1][ycoord] == 0)) || ((cells[xcoord+1][ycoord] == player)&&(cells[xcoord-1][ycoord]== 0))){
                    return true;
                }
                else{
                    if(((cells[xcoord][ycoord+1]== player)&&(cells[xcoord][ycoord+2]==0))||((cells[xcoord][ycoord+2]==player)&&(cells[xcoord][ycoord+1]== 0))){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 2:
                if(((cells[xcoord-1][ycoord]== player)&&(cells[xcoord-2][ycoord] == 0)) || ((cells[xcoord-2][ycoord] == player)&&(cells[xcoord-1][ycoord]== 0))){
                    return true;
                }
                else{
                    if(((cells[xcoord][ycoord+1]== player)&&(cells[xcoord][ycoord+2]==0))||((cells[xcoord][ycoord+2]==player)&&(cells[xcoord][ycoord+1]== 0))){
                        return true;
                    }
                    else {
                        if(((cells[xcoord-1][ycoord+1]== player)&&(cells[xcoord-2][ycoord+2]==0))||((cells[xcoord-2][ycoord+2]==player)&&(cells[xcoord-1][ycoord+1]== 0))){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }

                }
            case 3:
                if(((cells[xcoord+1][ycoord]== player)&&(cells[xcoord+2][ycoord] == 0)) || ((cells[xcoord+2][ycoord] == player)&&(cells[xcoord+1][ycoord]== 0))){
                    return true;
                }
                else{
                    if(((cells[xcoord][ycoord+1]== player)&&(cells[xcoord][ycoord-1]==0))||((cells[xcoord][ycoord-1]==player)&&(cells[xcoord][ycoord+1]== 0))){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 4:
                if(((cells[xcoord-1][ycoord]== player)&&(cells[xcoord+1][ycoord] == 0)) || (cells[xcoord+1][ycoord] == player)&&(cells[xcoord-1][ycoord]== 0)){
                    return true;
                }
                else{
                    if(((cells[xcoord-1][ycoord]== player)&&(cells[xcoord+1][ycoord]== 0))||((cells[xcoord+1][ycoord]==player)&&(cells[xcoord-1][ycoord]== 0))){
                        return true;
                    }
                    else {
                        if(((cells[xcoord-1][ycoord-1]== player)&&(cells[xcoord+1][ycoord+1]==0))||((cells[xcoord+1][ycoord+1]==player)&&(cells[xcoord-1][ycoord-1]== 0))){
                            return true;
                        }
                        else {
                            if(((cells[xcoord+1][ycoord-1]== player)&&(cells[xcoord-1][ycoord+1]==0))||((cells[xcoord-1][ycoord+1]==player)&&(cells[xcoord+1][ycoord-1]== 0))){
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    }

                }
            case 5:
                if(((cells[xcoord][ycoord-1]== player)&&(cells[xcoord][ycoord+1] == 0)) || ((cells[xcoord][ycoord+1] == player)&&(cells[xcoord][ycoord-1]==0))){
                    return true;
                }
                else{
                    if(((cells[xcoord-1][ycoord]== player)&&(cells[xcoord-2][ycoord]==0))||((cells[xcoord-2][ycoord]==player)&&(cells[xcoord-1][ycoord]== 0))){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 6:
                if(((cells[xcoord+1][ycoord]== player)&&(cells[xcoord+2][ycoord] == 0)) || ((cells[xcoord+2][ycoord] == player)&&(cells[xcoord+1][ycoord]== 0))){
                    return true;
                }
                else{
                    if(((cells[xcoord][ycoord-1]== player)&&(cells[xcoord][ycoord-2]==0))||((cells[xcoord][ycoord-2]==player)&&(cells[xcoord][ycoord-1]== 0))){
                        return true;
                    }
                    else {
                        if(((cells[xcoord+1][ycoord-1]== player)&&(cells[xcoord+2][ycoord-2]==0))||((cells[xcoord+2][ycoord-2]==player)&&(cells[xcoord+1][ycoord-1]== 0))){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            case 7:
                if(((cells[xcoord-1][ycoord-1]== player)&&(cells[xcoord+1][ycoord] == 0)) || ((cells[xcoord+1][ycoord] == player)&&(cells[xcoord-1][ycoord-1]== 0))){
                    return true;
                }
                else{
                    if(((cells[xcoord][ycoord-1]== player)&&(cells[xcoord][ycoord-2]==0))||((cells[xcoord][ycoord-2]==player)&&(cells[xcoord][ycoord-1]== 0))){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 8:
                if(((cells[xcoord-1][ycoord]== player)&&(cells[xcoord-2][ycoord] == 0)) || ((cells[xcoord-2][ycoord] == player)&&(cells[xcoord-1][ycoord]== 0))){
                    return true;
                }
                else{
                    if(((cells[xcoord][ycoord-1]== player)&&(cells[xcoord][ycoord-2]==0))||((cells[xcoord][ycoord-2]==player)&&(cells[xcoord][ycoord-1]== 0))){
                        return true;
                    }
                    else {
                        if(((cells[xcoord-1][ycoord-1]== player)&&(cells[xcoord-2][ycoord-2]== 0))||((cells[xcoord-2][ycoord-2]==player)&&(cells[xcoord-1][ycoord-1]== 0))){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
        }
        return false;
    }
    public boolean checkUsefulPairs(byte x, byte y, byte p){                            //Override for using Attribute for cells[][]
        boolean ret =checkUsefulPairs(x,y,p,this.cells);
        return ret;
    }

    public boolean checkRows(byte xcoord, byte ycoord, byte player, byte[][] cells){        //checks if a win for said player in this field is possible
                                                                            //player x = 1 player o = 2
        int cellNumber;
        cellNumber = xcoord%3+(ycoord%3)*3;
        switch (cellNumber){
            case 0:
                if((cells[xcoord+1][ycoord]== player) && (cells[xcoord+2][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord+1]== player)&&(cells[xcoord][ycoord+2]==player)){
                        return true;
                    }
                    else {
                        if((cells[xcoord+1][ycoord+1]== player)&&(cells[xcoord+2][ycoord+2]==player)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }

                }
            case 1:
                if((cells[xcoord-1][ycoord]== player) && (cells[xcoord+1][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord+1]== player)&&(cells[xcoord][ycoord+2]==player)){
                        return true;
                    }
                        else {
                            return false;
                        }
                }
            case 2:
                if((cells[xcoord-1][ycoord]== player) && (cells[xcoord-2][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord+1]== player)&&(cells[xcoord][ycoord+2]==player)){
                        return true;
                    }
                    else {
                        if((cells[xcoord-1][ycoord+1]== player)&&(cells[xcoord-2][ycoord+2]==player)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }

                }
            case 3:
                if((cells[xcoord+1][ycoord]== player) && (cells[xcoord+2][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord+1]== player)&&(cells[xcoord][ycoord-1]==player)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 4:
                if((cells[xcoord-1][ycoord]== player) && (cells[xcoord+1][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord-1][ycoord]== player)&&(cells[xcoord+1][ycoord]==player)){
                        return true;
                    }
                    else {
                        if((cells[xcoord-1][ycoord-1]== player)&&(cells[xcoord+1][ycoord+1]==player)){
                            return true;
                        }
                        else {
                            if((cells[xcoord+1][ycoord-1]== player)&&(cells[xcoord-1][ycoord+1]==player)){
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    }

                }
            case 5:
                if((cells[xcoord][ycoord-1]== player) && (cells[xcoord][ycoord+1] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord-1][ycoord]== player)&&(cells[xcoord-2][ycoord]==player)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 6:
                if((cells[xcoord+1][ycoord]== player) && (cells[xcoord+2][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord-1]== player)&&(cells[xcoord][ycoord-2]==player)){
                        return true;
                    }
                    else {
                        if((cells[xcoord+1][ycoord-1]== player)&&(cells[xcoord+2][ycoord-2]==player)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            case 7:
                if((cells[xcoord-1][ycoord-1]== player) && (cells[xcoord+1][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord-1]== player)&&(cells[xcoord][ycoord-2]==player)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 8:
                if((cells[xcoord-1][ycoord]== player) && (cells[xcoord-2][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord-1]== player)&&(cells[xcoord][ycoord-2]==player)){
                        return true;
                    }
                    else {
                        if((cells[xcoord-1][ycoord-1]== player)&&(cells[xcoord-2][ycoord-2]==player)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
        }
        return false;
    }
    public boolean checkRows(byte x, byte y, byte p){                                   //Override for using Attribute for cells[][]
        boolean ret =checkRows(x,y,p,this.cells);
        return ret;
    }
    public boolean checkFieldRows(byte xcoord, byte ycoord, byte player, byte[][] fields){
        //player x = 1 player o = 2
        int cellNumber = xcoord+(ycoord)*3;
        switch (cellNumber){
            case 0:
                if((fields[xcoord+1][ycoord]== player) && (fields[xcoord+2][ycoord] == player)){
                    return true;
                }
                else{
                    if((fields[xcoord][ycoord+1]== player)&&(fields[xcoord][ycoord+2]==player)){
                        return true;
                    }
                    else {
                        if((fields[xcoord+1][ycoord+1]== player)&&(fields[xcoord+2][ycoord+2]==player)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }

                }
            case 1:
                if((fields[xcoord-1][ycoord]== player) && (fields[xcoord+1][ycoord] == player)){
                    return true;
                }
                else{
                    if((fields[xcoord][ycoord+1]== player)&&(fields[xcoord][ycoord+2]==player)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 2:
                if((fields[xcoord-1][ycoord]== player) && (fields[xcoord-2][ycoord] == player)){
                    return true;
                }
                else{
                    if((cells[xcoord][ycoord+1]== player)&&(cells[xcoord][ycoord+2]==player)){
                        return true;
                    }
                    else {
                        if((fields[xcoord-1][ycoord+1]== player)&&(fields[xcoord-2][ycoord+2]==player)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }

                }
            case 3:
                if((fields[xcoord+1][ycoord]== player) && (fields[xcoord+2][ycoord] == player)){
                    return true;
                }
                else{
                    if((fields[xcoord][ycoord+1]== player)&&(fields[xcoord][ycoord-1]==player)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 4:
                if((fields[xcoord-1][ycoord]== player) && (fields[xcoord+1][ycoord] == player)){
                    return true;
                }
                else{
                    if((fields[xcoord-1][ycoord]== player)&&(fields[xcoord+1][ycoord]==player)){
                        return true;
                    }
                    else {
                        if((fields[xcoord-1][ycoord-1]== player)&&(fields[xcoord+1][ycoord+1]==player)){
                            return true;
                        }
                        else {
                            if((fields[xcoord+1][ycoord-1]== player)&&(fields[xcoord-1][ycoord+1]==player)){
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                    }

                }
            case 5:
                if((fields[xcoord][ycoord-1]== player) && (fields[xcoord][ycoord+1] == player)){
                    return true;
                }
                else{
                    if((fields[xcoord-1][ycoord]== player)&&(fields[xcoord-2][ycoord]==player)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 6:
                if((fields[xcoord+1][ycoord]== player) && (fields[xcoord+2][ycoord] == player)){
                    return true;
                }
                else{
                    if((fields[xcoord][ycoord-1]== player)&&(fields[xcoord][ycoord-2]==player)){
                        return true;
                    }
                    else {
                        if((fields[xcoord+1][ycoord-1]== player)&&(fields[xcoord+2][ycoord-2]==player)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            case 7:
                if((fields[xcoord-1][ycoord-1]== player) && (fields[xcoord+1][ycoord] == player)){
                    return true;
                }
                else{
                    if((fields[xcoord][ycoord-1]== player)&&(fields[xcoord][ycoord-2]==player)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            case 8:
                if((fields[xcoord-1][ycoord]== player) && (fields[xcoord-2][ycoord] == player)){
                    return true;
                }
                else{
                    if((fields[xcoord][ycoord-1]== player)&&(fields[xcoord][ycoord-2]==player)){
                        return true;
                    }
                    else {
                        if((fields[xcoord-1][ycoord-1]== player)&&(fields[xcoord-2][ycoord-2]==player)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
        }
        return false;
    }

    public void eliminateWonFields(){                   //Eliminates moves that would play in any field that is already won
                                                        //only needed if there is no active field (field already won or lost)
        for(int i1 = 0; i1<=8;i1++){
            for(int i2 = 0;i2<=8;i2++){

                if(this.fields[(int)Math.ceil((i1+1d)/3d)][(int)Math.ceil(((i2+1d)/3d))]!=0){   //checks if field of the cell is owned by someone by dividing the cells array and rounding up giving the coordinates of the field containing said cell, please dont change this line...
                    allowedCells[i1][i2] = false;        //sets the cell to disallowed
                }
            }
        }

    }
    public boolean[][] checkAllowedCells(byte[][] cells, byte[]activeField){
        //Search for active cells in cells[] by looking at activeField
        if (this.activeField[0]!=3){                    //the field isn`t won yet//this array is true for every cell the player is allowed to play
            boolean[][] allowedCells = new boolean[9][9];
            for (int i1 = 0; i1<=2;i1++) {
                for (int i2 = 0; i2 <= 2; i2++) {
                    if(cells[activeField[0] * 3 + i1][activeField[1] * 3 + i2]==0){               //checking whether cell is already occupied
                        allowedCells[activeField[0] * 3 + i1][activeField[1] * 3 + i2] = true;
                    }
                }
            }
        }
        else{                                           //the field is already won or fully occupied
            for (int i1 = 0; i1<=8;i1++) {
                for (int i2 = 0; i2 <=8; i2++) {        //for loop through the whole array
                    if(cells[i1][i2]==0){               //checking whether cell is already occupied
                        this.allowedCells[i1][i2] = true;
                    }
                }
            }
        }
        return allowedCells;
    }
    public void checkAllowedCells(){
        this.allowedCells = checkAllowedCells(this.cells , this.activeField);
    }
}

