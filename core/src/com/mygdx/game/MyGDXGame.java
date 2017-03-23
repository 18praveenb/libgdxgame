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
	private Manager manager;
	@Override
	public void create() {
        manager = new GridManager("level1.txt");
        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                manager.touchUp();
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                manager.touchDragged();
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                manager.touchDown();
                return true;
            }
        });
	}

	@Override
    public void resize(int width, int height) {
        manager.resize(width, height);
    }

	@Override
	public void render() {
        manager.nextFrame();
    }
	
	@Override
	public void dispose () { /* dispose */ }
}
