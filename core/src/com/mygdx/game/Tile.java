package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Tile {

    public enum Terrain {LAND}

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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    private Texture texture;

    public Tile(Level level, String character) {
        this.terrain = Terrain.LAND;
        this.highlight = Color.CLEAR;

        String texture = "grass.png";
        if (character.equals("^")) texture = "lapis_wall.png";
        // else character == "." we hope
        this.texture = level.getManager().getNow(texture, Texture.class);
    }
}