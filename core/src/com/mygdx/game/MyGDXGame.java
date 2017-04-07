package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class MyGDXGame extends ApplicationAdapter {
	private Controller controller;
	@Override
	public void create() {
        controller = new GridController("level1.txt");
        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                controller.touchUp();
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                controller.touchDragged();
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                controller.touchDown();
                return true;
            }
        });
	}

	@Override
    public void resize(int width, int height) {
        controller.resize(width, height);
    }

	@Override
	public void render() {
        controller.nextFrame();
    }
	
	@Override
	public void dispose () { /* dispose */ }
}
