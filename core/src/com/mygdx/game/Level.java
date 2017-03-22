package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Praveen on 3/21/17.
 */

public class Level {
    private Tile[][] tiles;
    public Level(String name) {
        String[] rows = Gdx.files.internal(name).readString().split("\n");
        int numRows = rows.length;
        int numCols = rows[0].length();
        for (int ir = 0; ir < numRows; ++ir) {
            for (int jc = 0; jc < numCols; ++jc) {
                String character = rows[ir].substring()
            }
        }
    }
}
