package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Praveen on 3/21/17.
 */

public class Level {

    AssetManager manager;
    private Tile[][] tiles;
    private Unit[][] units;
    private Player[] players;

    public Tile[][] getTiles() {
        return tiles;
    }

    public Unit[][] getUnits() {
        return units;
    }

    public int getNumRows() {
        return tiles.length;
    }

    public int getNumCols() {
        return tiles[0].length;
    }

    public Level(String name) {
        manager = new AssetManager();
        // Load the rows
        String[] rows = Gdx.files.internal(name).readString().split("\n");

        //Pluck the dimensions and dimension tile, unit arrays
        // Filespec: first line should be numCols:numRows (width:height)
        String[] dimensions = rowComponents(rows[0]);
        int numCols = Integer.parseInt(dimensions[0]);
        if (numCols != rows[1].length()) {
            throw new RuntimeException("Mismatch");
        }
        int numRows = Integer.parseInt(dimensions[1]);
        tiles = new Tile[numRows][numCols];
        units = new Unit[numRows][numCols];

        // Create the tiles. One-indexed for rows only because of initial line.
        for (int row = 1; row <= numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                String character = rows[row].substring(col, col + 1);
                tiles[row - 1][col] = new Tile(this, character);
            }
        }

        // Create the units
        for (int row = numRows + 1; row < rows.length; row++) {
            String[][] components = parse(rows[row]);
            String type = components[0][0];

            if (type.equals("player")) {

            }

            if (type.equals("unit")) {
                int x = Integer.parseInt(components[1][0]);
                int y = Integer.parseInt(components[2][0]);
                units[x][y] = new Unit(this, Arrays.copyOfRange(components, 3, components.length));
            }

        }
    }

    public String[][] parse(String row) {
        String[] first = row.split(":");
        String[][] second = new String[first.length][];
        for (int i = 0; i < first.length; i++) second[i] = first[i].split("_");
        return second;
    }

    /**
     * Uses assetManager to ensure that textures for units and tiles are not loaded multiple times.
     * @param fileName Include the file extension.
     * @return Texture
     */
    public Texture getTexture(String fileName) {
        manager.load(fileName, Texture.class);
        manager.finishLoadingAsset(fileName);
        return manager.get(fileName, Texture.class);
    }
}
