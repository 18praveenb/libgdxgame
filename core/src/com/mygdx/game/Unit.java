package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Praveen on 3/13/17.
 */

public class Unit {

    private Texture texture;
    private String name;

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

    public Unit(String[] parameters) {
        for (String parameterString: parameters) {
            String[] parameter = parseParameter(parameterString);
            if (parameter[0].equals("tex")) setTexture(new Texture(Gdx.files.internal(parameter[1])));
            if (parameter[0].equals("str")) setStrength(Integer.parseInt(parameter[1]));
            if (parameter[0].equals("def")) setDefense(Integer.parseInt(parameter[1]));
        }
    }

    private String[] parseParameter(String parameter) {
        return parameter.split("_");
    }
}
