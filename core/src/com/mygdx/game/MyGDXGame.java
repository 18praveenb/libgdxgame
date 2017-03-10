package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;

public class MyGDXGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture sword;
    private Texture grass;
    private Texture outline;
    private Music music;
    private int soldierX, soldierY;
    private GridItem[][] grid;
    /**
     * Textures are squares with this side length.
     */
    private static int TEXTURE_SIZE = 32;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        sword = new Texture("sword.png");
        grass = new Texture("grass.png");
        outline = new Texture("outline.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
        music.setLooping(true);
        music.play();
        grid = new GridItem[10][10];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = new GridItem(false);
            }
        }
	}

	@Override
	public void render () {
        clearScreen();
        updateState();
        drawFrame();
	}

	public void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

	public void updateState() {
        if (Gdx.input.isTouched()) {
//            int x = (Gdx.input.getX())/TEXTURE_SIZE;
//            int y = (480 - Gdx.input.getY())/TEXTURE_SIZE; // 480 - is temporary hack.
//            if (x >= 0 && x < grid.length && y >=0 && y < grid[0].length) {
//                GridItem square = grid[x][y];
//                square.setSoldier(!square.isSoldier());
//            }
            soldierX = (Gdx.input.getX())/TEXTURE_SIZE;
            soldierY = (Gdx.graphics.getHeight() - Gdx.input.getY())/TEXTURE_SIZE; // 480 - is temporary hack.
        }
    }

    public void drawFrame() {
        batch.begin();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                batch.draw(grass, x*TEXTURE_SIZE, y*TEXTURE_SIZE);
                batch.setColor(rfloat(), rfloat(), rfloat(), 1);
                batch.draw(outline, x*TEXTURE_SIZE, y*TEXTURE_SIZE);
                batch.setColor(1, 1, 1, 1);
//                if (grid[x][y].isSoldier()) batch.draw(sword, x*TEXTURE_SIZE, y*TEXTURE_SIZE);
            }
        }
        batch.draw(sword, soldierX*TEXTURE_SIZE, soldierY*TEXTURE_SIZE);
        batch.end();
    }

    public float rfloat() {return (float) Math.random();}
	
	@Override
	public void dispose () {
		batch.dispose();
		sword.dispose();
	}
}
