package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * The Manager can manage an entire screen for a part of the game e.g. turn based display, battle display, main menu
 * Created by Praveen on 3/23/17.
 */

public class Controller {
    private Manager manager;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private int frame;

    public AssetManager getManager() {
        return manager;
    }
    public SpriteBatch getBatch() {
        return batch;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
    public int getFrame() {return frame;}

    public Controller() {
        manager = new Manager();
        batch = new SpriteBatch();
        frame = 0;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(-camera.viewportWidth/2, -camera.viewportHeight/2, 0);
        camera.update();
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    public Vector3 fromCamera(float screenX, float screenY) {
        return getCamera().unproject(new Vector3(screenX, screenY, 0));
    }

    public Vector3 fromCamera(Vector3 vec) {
        return fromCamera(vec.x, vec.y);
    }

    public Vector3 toCamera(float worldX, float worldY) {
        return getCamera().project(new Vector3(worldX, worldY, 0));
    }

    public Vector3 toCamera(Vector3 vec) {
        return toCamera(vec.x, vec.y);
    }

    public void touchDown() {

    }

    public void touchDragged() {
        getCamera().translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
        getCamera().update();
    }

    public void touchUp() {

    }

    public void nextFrame() {
        clearScreen();
        updateState();
        prepareToDraw();
        drawFrame();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void updateState() {
        ++frame;
    }

    public void prepareToDraw() {
        batch.setProjectionMatrix(camera.combined);
    }

    public void drawFrame() {

    }
}
