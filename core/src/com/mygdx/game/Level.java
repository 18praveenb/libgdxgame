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

    // Variables only used during initialization
    private int numRows;
    private int numCols;
    private int numUnits;
    private String[] rows;

    public Level(String name) {
        determineNumbers(name);
        populateTiles();
        populateUnits();
    }

    private void determineNumbers(String name) {
        rows = Gdx.files.internal(name).readString().split("\n");
        numRows = rows.length;
        numUnits = 0;
        numCols = rows[0].length();
        for (int ir = 0; ir < numRows; ++ir) {
            for (int jc = 0; jc < numCols; ++jc) {
                String character = rows[ir].substring(jc, jc + 1);
                if (character.equals("*")) {
                    --numRows;
                    ++numUnits;
                }
            }
        }
    }

    private void populateTiles() {

    }

    private void populateUnits() {

    }

}
