package com.company;

public class GameLogic {

    Board board;
    byte player;
    byte enemyPlayer;
    boolean[][] updating_ac;    // updated allowed cells

    int[][] values = new int[9][9];
    private Board board;
    private Board ;

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

    private void UpdateVariables(){     // update values
        for (byte x = 0; x < 9; x++){
            for (byte y = 0; y < 9; y++){
                if (updating_ac[x][y] == true){   // cell is allowed
                    updating_ac = board.getAllowedCells();
                    updating_ac[x][y] = false;
                    values[x][y] = WinFieldPossible_getVal(x,y, player);
                }
                else {                  // cell isn't allowed
                    values[x][y] = -1000;
                }
            }
        }
    }
    private int WinFieldPossible_getVal(byte start_xcoord, byte start_ycoord ,byte player){   // update values
        // player bytes:
        // 1 -> x
        // 2 -> o
        int totalVal = 0;
        if (board)       // sobald man in ein feld kommt, dass fields[x][y] schon von irgendjemandem gewonnen ist, wird terminated und es wird terminated, wenn die Reihe nicht auf dem originalem Brett gewonnen werden kann
        return totalVal;
    }
    private byte[][] WinFieldPossible_getPos(){

    }
}
