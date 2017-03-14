package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;

import java.util.ArrayList;

public class MyGDXGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture sword;
    private Texture grass;
    private Texture outline;
    private Music music;
    private Sound sound;
    private Tile[][] grid;
    private ArrayList<Texture> characters;
    private static int TEXTURE_SIZE = 32; //square size
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        sword = new Texture(Gdx.files.internal("sword.png"));
        grass = new Texture(Gdx.files.internal("grass.png"));
        outline = new Texture(Gdx.files.internal("outline.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("sound.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
        music.setLooping(true);
        music.play();

        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {
                touch();
                return true;
            }
        });

        characters = new ArrayList<Texture>();
        characters.add(sword);

        grid = new Tile[10][10];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = new Tile(Tile.Terrain.GRASS);
            }
        }

        grid[0][0].setOccupant(0);
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
    }

    public void touch() {

    }

    public void drawFrame() {
        batch.begin();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                Tile t = grid[x][y];
                int drawX = x * TEXTURE_SIZE;
                int drawY = y * TEXTURE_SIZE;
                batch.draw(tileTexture(t), drawX, drawY);
                Texture character = tileCharacter(t);
                if (character != null) batch.draw(character, drawX, drawY);
            }
        }
        batch.end();
    }

    private Texture tileTexture(Tile tile) {
        switch (tile.getTerrain()) {
            case GRASS:
                return grass;
        }
        throw new RuntimeException("what happened?");
    }

    private Texture tileCharacter(Tile tile) {
        int occupant = tile.getOccupant();
        if (occupant == -1) return null;
        return characters.get(occupant);
    }

    public float rfloat() {return (float) Math.random();}
	
	@Override
	public void dispose () {
		batch.dispose();
		sword.dispose();
        grass.dispose();
        music.dispose();
        sound.dispose();
        outline.dispose();
	}
}
