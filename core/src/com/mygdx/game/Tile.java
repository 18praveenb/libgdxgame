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

    public int getOccupant() {
        return occupant;
    }

    public void setOccupant(int occupant) {
        this.occupant = occupant;
    }

    /**
     * The int address of the occupant in array of characters. _definitelyobjectoriented
     */
    private int occupant;

    public Tile(Terrain terrain) {
        this.terrain = terrain;
        this.occupant = -1;
    }
}