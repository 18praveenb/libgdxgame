package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Praveen on 3/13/17.
 */

public class Unit {

    private Texture texture;
    private String name;
    private int player;

    //Unit combat stats
    private int strength;
    private int defense;

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

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Unit(Level level, String[][] parameters) {
        for (String[] parameter: parameters) {
            if (parameter[0].equals("texture")) texture = level.getManager().getNow(parameter[1], Texture.class);
            if (parameter[0].equals("strength")) strength = Integer.parseInt(parameter[1]);
            if (parameter[0].equals("defense")) defense = Integer.parseInt(parameter[1]);
        }
    }
}
