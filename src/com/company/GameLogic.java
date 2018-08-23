package com.company;

public class GameLogic {

    private double _ThreeValue = 10.0d;
    private double _TwoValue = 2.0d;
    private double _BigEnemyChoice = -10.0d;
    private double _BigOwnChoice = 10.0d;


    private Board board;
    private byte player;
    private byte enemyPlayer;
    private boolean[][] updatingAllowedCells;
    private byte[][] updatingCells;
    private byte[][] updatingFields = new byte[3][3];

    private double[][] values = new double[9][9];


    public GameLogic(Board b, byte p){
        this.board = b;
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

    public double[][] getValues(){
        return values;
    }

    private void UpdateVariables(){     // update values
        boolean[][] start_ac = board.getAllowedCells();
        updatingAllowedCells = start_ac;
        updatingCells = board.getCells();
        updatingFields = board.getFields();
        for (byte x = 0; x < 9; x++){
            for (byte y = 0; y < 9; y++){
                if (start_ac[x][y]){   // cell is allowed
                    if (board.getFields()[x%3][y%3] == this.player || board.getFields()[x%3][y%3] == this.enemyPlayer){
                        values[x][y] = -1000.0d;
                    }
                    else {
                        values[x][y] = WinFieldPossible_getVal(x,y, player);
                    }
                }
                else {                  // cell isn't allowed
                    values[x][y] = -1000.0d;
                }
            }
        }
    }
    private int WinFieldPossible_getVal(byte start_xcoord, byte start_ycoord ,byte p){   // update values
        // player bytes:
        // 1 -> x
        // 2 -> o
        int totalVal = 0;

        updatingCells[start_xcoord][start_ycoord] = p;
        byte[] nextActiveField = new byte[2];
        nextActiveField[0] = (byte)(start_xcoord % 3);
        nextActiveField[1] = (byte)(start_ycoord % 3);

        // es wird terminated, wenn die Reihe nicht auf dem originalem Brett gewonnen werden kann
        if (updatingFields[start_xcoord % 3][start_ycoord % 3] == 0){    // if field isn't won by anybody
            if (board.checkRows(start_xcoord, start_ycoord, p)){
                if (p == this.player){
                    totalVal += _ThreeValue;
                }
                else if (p == this.enemyPlayer){
                    totalVal -= _ThreeValue;
                }
                updatingFields[start_xcoord % 3][start_ycoord % 3] = p;
            }
            else if (board.checkUsefulPairs(start_xcoord, start_ycoord, p)){
                if (p == this.player){
                    totalVal += _TwoValue;
                }
                else if (p == this.enemyPlayer){
                    totalVal -= _TwoValue;
                }
            }

            // update updatingAllowedCells based on updatingCells, updatingFields and nextActiveField
            updatingAllowedCells = board.checkAllowedCells(updatingCells, nextActiveField);

            for (byte x = 0; x < 9; x++){
                for (byte y = 0; y < 9; y++){
                    if (updatingAllowedCells[x][y]){   // cell is allowed
                        if (updatingFields[x%3][y%3] == this.player || updatingFields[x%3][y%3] == this.enemyPlayer){
                            values[x][y] += -1000.0d;
                        }
                        else {
                            values[x][y] += WinFieldPossible_getVal(x,y, player);
                        }
                    }
                    else {                  // cell isn't allowed
                        values[x][y] += -1000.0d;
                    }
                }
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
