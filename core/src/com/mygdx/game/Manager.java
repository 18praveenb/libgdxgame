package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;

/**
 * Created by Praveen on 4/6/17.
 */

public class Manager extends AssetManager {
    //** Synchronous get
    public <T> T getNow(String name, java.lang.Class<T> type) {
        this.load(name, type);
        this.finishLoadingAsset(name);
        return this.get(name, type);
    }
}
