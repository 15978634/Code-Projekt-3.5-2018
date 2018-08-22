package com.company;

public class GameLogic {

    double _ThreeValue = 10.0d;
    double _TwoValue = 2.0d;


    private Board board;
    private byte player;
    private byte enemyPlayer;
    private boolean[][] updating_ac;    // updated allowed cells

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
        for (byte x = 0; x < 9; x++){
            for (byte y = 0; y < 9; y++){
                if (start_ac[x][y]){   // cell is allowed
                    updating_ac = board.getAllowedCells();
                    updating_ac[x][y] = false;
                    values[x][y] = WinFieldPossible_getVal(x,y, player);
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
        // es wird terminated, wenn die Reihe nicht auf dem originalem Brett gewonnen werden kann
        if (board.getFields()[start_xcoord % 3][start_ycoord % 3] == 0){    // if field isnt won by anybody
            if (board.checkRows(start_xcoord, start_ycoord, p)){
                if (p == this.player){
                    totalVal += _ThreeValue;
                }
                else if (p == this.enemyPlayer){
                    totalVal -= _ThreeValue;
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
        }
        else {      // if field is won by someone
            if (p == this.player){

            }
            else if (p == this.enemyPlayer){

            }
        }
        return totalVal;
    }
}