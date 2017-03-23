package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Praveen on 3/23/17.
 */

public class GridManager extends Manager {

    private Level level;
    private int turn;
    private static int GRID_SIZE = 32;

    public GridManager(String levelName) {
        super();
        this.level = new Level(levelName);
        this.turn = 0;
    }

    @Override
    public void drawFrame() {
        super.drawFrame();
        getBatch().begin();

        getBatch().end();
    }

    public GridPoint worldToGrid(float worldX, float worldY) {
        return new GridPoint((int) (worldX / GRID_SIZE), (int) (worldY / GRID_SIZE));
    }

    public GridPoint screenToGrid(float screenX, float screenY) {
        Vector3 world = toCamera(screenX, screenY);
        return worldToGrid(world.x, world.y);
    }
}
