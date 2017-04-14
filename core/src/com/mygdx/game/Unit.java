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
    private int moves;

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

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public Unit(Level level, String[][] parameters) {
        for (String[] parameter: parameters) {
            if (parameter[0].equals("texture")) texture = level.getManager().getNow(parameter[1], Texture.class);
            if (parameter[0].equals("moves")) moves = Integer.parseInt(parameter[1]);
        }
    }
}
