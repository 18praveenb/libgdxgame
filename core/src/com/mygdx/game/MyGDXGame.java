package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class MyGDXGame extends ApplicationAdapter {
    private AssetManager manager;
	private SpriteBatch batch;
    private OrthographicCamera camera;
    private Level level;
    private int turn;
    private int tick;
    private static int GRID_SIZE = 32; //square size
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(-camera.viewportWidth/2, -camera.viewportHeight/2, 0);
        camera.update();
        manager = new AssetManager();
        level = new Level("level_1.txt");

        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                _touchUp();
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                _touchDragged();
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                _touchDown();
                return true;
            }
        });
	}

	@Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

	@Override
	public void render() {
        clearScreen();
        updateState();
        drawFrame();
	}

	public void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    int enemy_turn_timer = -1;
    final int enemy_turn_time = 60;

	public void updateState() {
        tick = (tick + 1) % (Integer.MAX_VALUE - 1); // for good luck

        if (turn == 1) {
            if (enemy_turn_timer < 0) {
                enemy_turn_timer = enemy_turn_time;
            }
            else if (enemy_turn_timer > 0) {
                enemy_turn_timer -= 1;
            }
            else {
                turn = 0;
                enemy_turn_timer = enemy_turn_time;
            }
        }
    }

    public void _touchDown() {

    }

    public void _touchDragged() {
        camera.translate(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
    }

    public void _touchUp() {
        GridPoint point = screenToGrid(Gdx.input.getX(), Gdx.input.getY());
    }

    public void touch() {
    }

    public Vector3 screenToWorld(float screenX, float screenY) {
        return camera.unproject(new Vector3(screenX, screenY, 0));
    }

    public Vector3 worldToScreen(float worldX, float worldY) {
        return camera.project(new Vector3(worldX, worldY, 0));
    }

    public GridPoint worldToGrid(float worldX, float worldY) {    // may want to create a custom ¬
                                                            // Coordinate instead of using Vector2
        return new GridPoint((int) (worldX / GRID_SIZE), (int) (worldY / GRID_SIZE));
    }

    public GridPoint screenToGrid(float screenX, float screenY) {
        Vector3 world = screenToWorld(screenX, screenY);
        return worldToGrid(world.x, world.y);
    }

    public void drawFrame() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
    }

    public float rfloat() {return (float) Math.random();}

    public int rint(int below) {return (int) (Math.random() * below);}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
