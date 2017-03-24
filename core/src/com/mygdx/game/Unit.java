package com.mygdx.game;

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
    private int magic;
    private int defense;
    private int resistance;
    private int speed;
    private int skill;

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

    public int getStrength() { return strength; }

    public int getMagic() { return magic; }

    public int getDefense() { return defense; }

    public int getResistance() { return resistance; }

    public int getSpeed() { return speed; }

    public int getSkill() { return skill; }

    public Unit(String[] parameters) {
        for (String parameterString: parameters) {
            String[] parameter = parseParameter(parameterString);
            if parameter[0].equals("tex")
        }
    }

    private String[] parseParameter(String parameter) {
        return parameter.split("_");
    }
}
