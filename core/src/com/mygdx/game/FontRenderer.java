package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by Praveen on 4/6/17.
 */
public class FontRenderer {

    private String fontName = "font";
    private float spaceBetweenCharacters = 1; // Pixels
    private float spaceBetweenLines = 1;
    private Manager manager;
    private SpriteBatch batch;


    public FontRenderer(Manager manager, SpriteBatch batch) {
        this.manager = manager;
        this.batch = batch;
    }

    public void drawString(String str, float x, float y, float width) {
        int strlen = str.length();

        // Compute the lines
        ArrayList<ArrayList<Texture>> lines = new ArrayList<ArrayList<Texture>>();
        lines.add(new ArrayList<Texture>());
        float currX = x, maxWidth = x + width;
        for (int i = 0; i < strlen; i++) {
            Texture glyph = this.getGlyph(str.charAt(i));
            float glyphWidth = glyph.getWidth();
            if ((currX + glyphWidth) > maxWidth) {
                currX = x; // reset for the new line
                lines.add(new ArrayList<Texture>()); // new line
            }
            else {
                currX += glyphWidth; // advance along the line
            }
            lines.get(lines.size()-1).add(glyph); // save to latest line (new or old)
        }

        // Compute the height of each line
        int numlines = lines.size();
        float[] heights = new float[numlines];
        for (int i = 0; i < numlines; i++) {
            ArrayList<Texture> line = lines.get(i);
            for (Texture glyph : line) {
                float glyphHeight = glyph.getHeight();
                if (glyphHeight > heights[i]) heights[i] = glyphHeight;
            }
        }

        // Render the lines
        // We add the height *before* rendering but add the width *after rendering*
        // ... because the origin of drawing is the bottom left corner
        // and we want the origin of a font to be the top right corner
        // because the text scrolls to the right and down
        float currY = y;
        for (int i = 0; i < numlines; i++) {
            ArrayList<Texture> line = lines.get(i);
            currX = x;
            currY -= heights[i] + spaceBetweenLines; // seems the y is 'backward' this could be a problem
            int numchars = line.size();
            for (int j = 0; j < numchars; j++) {
                Texture glyph = line.get(j);
                batch.draw(glyph, currX, currY);
                currX += glyph.getWidth() + spaceBetweenCharacters;
            }
        }
    }

    private Texture getGlyph(Character character) {
        String prefix = fontName + "/";
        if (Character.isUpperCase(character)) prefix = prefix + "uc_";
        else if (Character.isLowerCase(character)) prefix = prefix + "lc_";
        return manager.getNow(prefix + character + ".png", Texture.class);
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public float getSpaceBetweenCharacters() {
        return spaceBetweenCharacters;
    }

    public void setSpaceBetweenCharacters(float spaceBetweenCharacters) {
        this.spaceBetweenCharacters = spaceBetweenCharacters;
    }

    public float getSpaceBetweenLines() {
        return spaceBetweenLines;
    }

    public void setSpaceBetweenLines(float spaceBetweenLines) {
        this.spaceBetweenLines = spaceBetweenLines;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }
}
