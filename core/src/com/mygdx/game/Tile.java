package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class Tile {

    public enum Terrain {GRASS}

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    private Terrain terrain;

    public Color getHighlight() {
        return highlight;
    }

    public void setHighlight(Color highlight) {
        this.highlight = highlight;
    }

    private Color highlight;

    public Tile(Terrain terrain) {
        this.terrain = terrain;
        this.highlight = Color.CLEAR;
    }
}