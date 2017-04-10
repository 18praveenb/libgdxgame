package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayDeque;
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
        float currX = x;
        float deltaX = 0;
        float maxX = x + width;
        float currY = y;
        float deltaY = 0;
        int strlen = str.length();

        for (int i = 0; i < strlen; i++) {
            Character c = str.charAt(i);
                String prefix = folderName + "/";
                if (Character.isUpperCase(c)) prefix = prefix + "uc_";
                else if (Character.isLowerCase(c)) prefix = prefix + "lc_";
                String suffix = ".png";
                Texture glyph = manager.getNow(prefix + c + suffix, Texture.class);

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

}
