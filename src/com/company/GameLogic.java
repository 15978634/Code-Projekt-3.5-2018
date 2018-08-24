package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {

    private double _ThreeValue = 10.0d;
    private double _TwoValue = 2.0d;
    private double _BigEnemyChoice = -10.0d;
    private double _BigOwnChoice = 10.0d;


    private Board board;
    private byte player;
    private byte enemyPlayer;

    private double[][] values = new double[9][9];

    public void setPlayer(byte p){
        this.player = p;
        switch (this.player){
            case 1:
                this.enemyPlayer = 2;
                break;
            case 2:
                this.enemyPlayer = 1;
                break;
        }
    }

    public GameLogic(Board b){
        this.board = b;
    }

    public double[][] getValues(){
        UpdateVariables();
        return values;
    }

    private void UpdateVariables(){     // update values
        boolean[][] start_ac = board.getAllowedCells();
        boolean[][] updatingAllowedCells = start_ac;
        byte[][] updatingCells = board.getCells();
        byte[][] updatingFields = board.getFields();
        for (byte x = 0; x < 9; x++){
            for (byte y = 0; y < 9; y++){
                if (start_ac[x][y]){   // cell is allowed
                    if (board.getFields()[x%3][y%3] == this.player || board.getFields()[x%3][y%3] == this.enemyPlayer){
                        values[x][y] = -1000.0d;
                    }
                    else {
                        values[x][y] = WinFieldPossible_getVal(x,y, this.player, updatingAllowedCells, updatingCells, updatingFields);
                    }
                }
                else {                  // cell isn't allowed
                    values[x][y] = -1000.0d;
                }
            }
        }
    }
    private int WinFieldPossible_getVal(byte start_xcoord, byte start_ycoord ,byte p, boolean[][] updatingAllowedCells, byte[][] updatingCells, byte[][] updatingFields){// update values
        // player bytes:
        // 1 -> x
        // 2 -> o
        int totalVal = 0;

        updatingCells[start_xcoord][start_ycoord] = p;
        byte[] nextActiveField = new byte[2];
        nextActiveField[0] = (byte)(start_xcoord % 3);
        nextActiveField[1] = (byte)(start_ycoord % 3);

        if (updatingFields[start_xcoord % 3][start_ycoord % 3] == 0){    // if field isn't won by anybody
            // Termination conditions
            //
            if (board.checkRows(start_xcoord, start_ycoord, p)){
                if (p == this.player){
                    totalVal += _ThreeValue;
                }
                else if (p == this.enemyPlayer){
                    totalVal -= _ThreeValue;
                }
                updatingFields[start_xcoord % 3][start_ycoord % 3] = p;

                if (board.checkFieldRows((byte)(start_xcoord % 3), (byte)(start_ycoord % 3), p, updatingFields)){
                    if (p == this.player){
                        totalVal += 500000.0d;
                        return totalVal;
                    }
                    else if (p == this.enemyPlayer){
                        totalVal -= 500000.0d;
                        return totalVal;
                    }
                }
            }
            else if (board.checkUsefulPairs(start_xcoord, start_ycoord, p)){
                if (p == this.player){
                    totalVal += _TwoValue;
                }
                else if (p == this.enemyPlayer){
                    totalVal -= _TwoValue;
                }
            }


            updatingAllowedCells = board.checkAllowedCells(updatingCells, nextActiveField);

            boolean isEmpty = true;
            for (int x = 0; x < 3; x++){
                for (int y = 0; y < 3; y++){
                    if (!updatingAllowedCells[(nextActiveField[0]*3)+x][(nextActiveField[1]*3)+y]){
                        isEmpty = false;
                        break;
                    }
                }
            }
            if (isEmpty){
                // TODO: calculate a bit more
                return totalVal;
            }

            double[] maxVal = new double[81];
            int counter = 0;
            for (byte x = 0; x < 9; x++){
                for (byte y = 0; y < 9; y++){
                    if (updatingAllowedCells[x][y]){   // cell is allowed
                        if (updatingFields[x%3][y%3] == this.player || updatingFields[x%3][y%3] == this.enemyPlayer){       // check whether the field where i place my next x/o is already won
                            maxVal[counter] = -1000.0d;
                        }
                        else {
                            if (p == this.player){
                                maxVal[counter] = WinFieldPossible_getVal(x,y, this.enemyPlayer, updatingAllowedCells, updatingCells, updatingFields);
                            }
                            else if (p == this.enemyPlayer){
                                maxVal[counter] = WinFieldPossible_getVal(x,y, this.player, updatingAllowedCells, updatingCells, updatingFields);
                            }
                        }
                    }
                    else {                  // cell isn't allowed
                        maxVal[counter] = -1000.0d;
                    }
                    counter++;
                }
            }
            Arrays.sort(maxVal);


            if (p == this.player){
                totalVal += maxVal[0];
            }
            else if (p == this.enemyPlayer){
                totalVal += maxVal[80];
            }

            return totalVal;
        }
        else {      // if field is won by someone
            if (p == this.player){
                totalVal += _BigOwnChoice;
            }
            else if (p == this.enemyPlayer){
                totalVal += _BigEnemyChoice;
            }
            return totalVal;
        }
    }
}
