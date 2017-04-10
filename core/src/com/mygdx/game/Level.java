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

    private Manager manager;
    private Tile[][] tiles; // Zero indexed, row-column
    private Unit[][] units; // Zero indexed, row-column
    private Player[] players; // One indexed

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

    public Manager getManager() {
        return manager;
    }

    public Level(Manager manager, String name) {
        this.manager = manager;

        String[] rows = Gdx.files.internal(name).readString().split("\n");

        String[][] info = parse(rows[0]);
        int numRows = -1, numCols = -1, numPlayers = -1;
        for (String[] parameter : info) {
            if (parameter[0].equals("rows")) numRows = Integer.parseInt(parameter[1]);
            else if (parameter[0].equals("columns")) numCols = Integer.parseInt(parameter[1]);
            else if (parameter[0].equals("players")) numPlayers = Integer.parseInt(parameter[1]);
        }

        tiles = new Tile[numRows][numCols];
        units = new Unit[numRows][numCols];
        players = new Player[numPlayers + 1]; // To allow for one-indexing of the players

        // Create the tiles. One-indexed for rows only because the initial line exists.
        // Because we build the array from the bottom up, we start with the last row and go up.
        for (int row = numRows; row >= 1; row--) {
            for (int col = 0; col < numCols; col++) {
                String character = rows[row].substring(col, col + 1);
                tiles[row - 1][col] = new Tile(this, character);
            }
        }

        // Read additional information lines, such as a unit specifier or a player specifier.
        for (int row = numRows + 1; row < rows.length; row++) {
            String[][] components = parse(rows[row]);
            String type = components[0][0];

            if (type.equals("player")) {
                int playerNumber = -1;
                int team = -1;
                boolean human = true;
                for (String[] parameter : components) {
                    if (parameter[0].equals("player")) playerNumber = Integer.parseInt(parameter[1]);
                    else if (parameter[0].equals("team")) team = Integer.parseInt(parameter[1]);
                    else if (parameter[0].equals("human")) human = Boolean.parseBoolean(parameter[1]);
                }
                players[playerNumber] = new Player(team, human);
            }

            if (type.equals("gridunit")) {
                int x = Integer.parseInt(components[1][0]);
                int y = Integer.parseInt(components[2][0]);
                units[x][y] = new Unit(this, Arrays.copyOfRange(components, 4, components.length));
            }

        }
    }

    public String[][] parse(String row) {
        String[] first = row.split(":");
        String[][] second = new String[first.length][];
        for (int i = 0; i < first.length; i++) second[i] = first[i].split("_");
        return second;
    }
}
