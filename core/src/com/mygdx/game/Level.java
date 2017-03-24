package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Praveen on 3/21/17.
 */

public class Level {

    private Tile[][] tiles;
    private Unit[][] units;

    public Tile[][] getTiles() {
        return tiles;
    }

    public Unit[][] getUnits() {
        return units;
    }

    public int getNumRows() {
        return tiles[0].length;
    }

    public int getNumCols() {
        return tiles.length;
    }

    public Level(String name) {
        // Load the rows
        String[] rows = Gdx.files.internal(name).readString().split("\n");

        //Pluck the dimensions and dimension tile, unit arrays
        // Filespec: first line should be numCols:numRows (width:height)
        String[] dimensions = rowComponents(rows[0]);
        int numCols = Integer.parseInt(dimensions[0]);
        if (numCols != rows[1].length()) {throw new RuntimeException("Mismatch");}
        int numRows = Integer.parseInt(dimensions[1]);
        tiles = new Tile[numRows][numCols];
        units = new Unit[numRows][numCols];

        // Create the tiles. One-indexed for rows only because of initial line.
        for (int row = 1; row <= numRows; row++) {for (int col = 0; col < numCols; col++) {
            String character = rows[row].substring(col, col + 1);
            tiles[row-1][col] = new Tile(character);
        }}

        // Create the units
        for (int row = numRows + 1; row < rows.length; row++) {
            String[] components = rowComponents(rows[row]);
            int x = Integer.parseInt(components[0]);
            int y = Integer.parseInt(components[1]);
            String type = components[2];
            Unit unit = null; // get ready for some RuntimeExceptions
            if (type.equals("unit")) {
                unit = new Unit(Arrays.copyOfRange(components, 3, components.length));
            }
            units[x][y] = unit;
        }
    }

    private String[] rowComponents(String row) {
        return row.split(":");
    }

}
