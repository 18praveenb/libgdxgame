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

public class Font {

    private Manager manager;
    private String folderName;
    private Queue<Texture> glyphQueue;

    public Font(Manager manager, String folderName) {
        this.manager = manager;
        this.folderName = folderName;
        this.glyphQueue = new ArrayDeque<Texture>();
    }

    public void drawString(String str, SpriteBatch batch, float x, float y, float width) {
        int strlen = str.length();

        // Compute the lines
        ArrayList<ArrayList<Texture>> lines = new ArrayList<ArrayList<Texture>>();
        float currX = x;
        for (int i = 0; i < strlen; i++) {
            Texture glyph = this.getGlyph(str.charAt(i));
            float glyphWidth = glyph.getWidth();
            if ((currX + glyphWidth) > (x + width)) {
                currX = x;
                lines.add(new ArrayList<Texture>());
            }
            else {
                currX += glyphWidth;
            }
            lines.get(lines.size()-1).add(glyph);
        }

        // Compute the height of each line

        // Render the lines

        for (int i = 0; i < strlen; i++) {
            Texture glyph =


                float postX = currX + glyph.getWidth();
                if (postX > maxX) {
                    currX -= deltaX;
                    currY -= deltaY;
                    deltaY -= 0;
                    deltaX = 0;
                    drawQueue(currX, currY, batch);
                    currX = x;
                }
                glyphQueue.add(glyph);
                currX += glyph.getWidth();
                deltaX += glyph.getWidth();
                deltaY = Math.max(deltaY, glyph.getHeight());
        }
        drawQueue(currX - deltaX, currY + deltaY, batch);
    }

    private void drawQueue(float x, float y, SpriteBatch batch) {
        float currX = x;
        for (Texture glyph : glyphQueue) {
            batch.draw(glyph, currX, y);
            currX += glyph.getWidth();
        }
        glyphQueue.clear();
    }

    private Texture getGlyph(Character character) {
        String prefix = folderName + "/";
        if (Character.isUpperCase(character)) prefix = prefix + "uc_";
        else if (Character.isLowerCase(character)) prefix = prefix + "lc_";
        return manager.getNow(prefix + character + ".png", Texture.class);
    }

}
