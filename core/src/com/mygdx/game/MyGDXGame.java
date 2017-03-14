package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class MyGDXGame extends ApplicationAdapter {
	private SpriteBatch batch;
    private OrthographicCamera camera;

    private static int GRID_SIZE = 32; //square size
	private Texture sword;
    private Texture grass;
    private Texture outline;
    private Music music;
    private Sound sound;

    private Tile[][] grid;
    private ArrayList<Unit> units;
    private int turn;
    private int tick;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(-camera.viewportWidth/2, -camera.viewportHeight/2, 0);
        camera.update();

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

        units = new ArrayList<Unit>();
        units.add(new Unit("Joe", sword, 0, 0));
        units.add(new Unit("Enemy", sword, 3, 3));

        grid = new Tile[10][10];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = new Tile(Tile.Terrain.GRASS);
            }
        }
	}

	@Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
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
        tick = (tick + 1) % (Integer.MAX_VALUE - 1); // for good luck

        int speed = 10;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) camera.translate(-speed, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) camera.translate(speed, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) camera.translate(0, speed);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) camera.translate(0, -speed);
        camera.update();

        if (turn == 1 && tick % 60 == 0) { // technically means variable speed but who cares.
            units.get(1).setGridX(rint(grid.length));
            units.get(1).setGridY(rint(grid.length));
            turn = 0;
        }
    }

    public void touch() {
        GridPoint point = screenToGrid(Gdx.input.getX(), Gdx.input.getY());
        if (turn == 0) {
            units.get(0).setGridPoint(point);
            turn = 1;
        }
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
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                Tile t = grid[x][y];
                int drawX = x * GRID_SIZE;
                int drawY = y * GRID_SIZE;
                batch.draw(tileTexture(t), drawX, drawY);
            }
        }
        for (Unit unit : units) {
            batch.draw(unit.getTexture(), unit.getGridX() * GRID_SIZE, unit.getGridY() * GRID_SIZE);
        }
        batch.end();
    }

    private Texture tileTexture(Tile tile) {
        switch (tile.getTerrain()) {
            case GRASS:
                return grass;
        }
        throw new RuntimeException("error in getTexture method");
    }

    public float rfloat() {return (float) Math.random();}

    public int rint(int below) {return (int) (Math.random() * below);}
	
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
