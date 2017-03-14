package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Praveen on 3/13/17.
 */

public class Unit {

    private int gridX;
    private int gridY;
    private Texture texture;
    private String name;

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGridPoint(GridPoint point) {
        this.gridX = point.getX();
        this.gridY = point.getY();
    }

    public Unit(String name, Texture texture, int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.texture = texture;
        this.name = name;
    }
}
