package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;

/**
 * Created by Praveen on 3/21/17.
 */

public class Level {

    private Tile[][] tiles;
    private Unit[][] units;

    public Level(String name) {
        // Load the rows
        String[] rows = Gdx.files.internal(name).readString().split("\n");

        //Pluck the dimensions and dimension tile, unit arrays
        // Filespec: first line should be numCols:numRows (width:height)
        String[] dimensions = rowComponents(rows[0]);
        int numCols = Integer.parseInt(dimensions[0]);
        if numCols != rows[1].length() {throw new RuntimeException("Mismatch");}
        int numRows = Integer.parseInt(dimensions[1]);
        tiles = new Tile[numRows][numCols];
        units = new Unit[numRows][numCols];

        // Create the tiles. One-indexed for rows only because of initial line.
        for (int row = 1; row <= numRows; row++) {for (int col = 0; col < numCols; col++) {
            String character = rows[row].substring(col, col + 1);
            tiles[row][col] = new Tile(character);
        }}

        // Create the units
        for (int row = numRows; row < rows.length; row++) {
            Unit unit = new Unit()
        }
    }

    private String[] rowComponents(String row) {
        return row.split(":");
    }

}
