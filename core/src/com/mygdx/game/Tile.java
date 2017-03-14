package com.mygdx.game;

public class Tile {

    public enum Terrain {GRASS}

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    private Terrain terrain;

    public Tile(Terrain terrain) {
        this.terrain = terrain;
    }
}