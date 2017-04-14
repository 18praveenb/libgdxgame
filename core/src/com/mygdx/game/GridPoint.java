package com.mygdx.game;

/**
 * Created by Praveen on 3/13/17.
 */

public class GridPoint {
    private int row, col;
    public GridPoint(int col, int row) {this.row = row; this.col = col;}
    public int getRow() {return this.row;}
    public int getCol() {return this.col;}
    public <T> T valueIn(T[][] array) {
        return array[row][col];
    }
    public int nonDiagonalDistance(GridPoint destination) {
        return Math.abs(destination.row - row) + Math.abs(destination.col - col);
    }
}
