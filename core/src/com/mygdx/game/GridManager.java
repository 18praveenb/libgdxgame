package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    public void touchUp() {
        super.touchUp();


    }

    @Override
    public void touchDown() {
        super.touchDown();

        GridPoint point = selectedGridPoint();
        int row = point.getY();
        int col = point.getX();

        if ((col >= level.getNumCols()) || (row >= level.getNumRows()))
            return;

        level.getTiles()[row][col].setHighlight(Color.RED);
    }

    @Override
    public void drawFrame() {
        super.drawFrame();
        SpriteBatch batch = getBatch();

        batch.begin();

        for (int row = 0; row < level.getNumRows(); row++)
            for(int col = 0; col < level.getNumCols(); col++) {

                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;

                // Draw tile
                Tile tile = level.getTiles()[row][col];
                batch.draw(tile.getTexture(), x, y);

                // Draw outline
                Texture outline = level.getTexture("outline.png");
                batch.setColor(tile.getHighlight());
                batch.draw(outline, x, y);
                batch.setColor(Color.WHITE);

                // Draw unit
                Unit unit = level.getUnits()[row][col];
                if (unit != null)
                    batch.draw(unit.getTexture(), x, y);
        }
        batch.end();
    }

    public GridPoint selectedGridPoint() {
        return screenToGrid(Gdx.input.getX(), convertY(Gdx.input.getY()));
    }

    public int convertY(int y) {
        return Gdx.graphics.getHeight() - 1 - y;
    }

    public GridPoint worldToGrid(float worldX, float worldY) {
        return new GridPoint((int) (worldX / GRID_SIZE), (int) (worldY / GRID_SIZE));
    }

    public GridPoint screenToGrid(float screenX, float screenY) {
        Vector3 world = toCamera(screenX, screenY);
        return worldToGrid(world.x, world.y);
    }
}
