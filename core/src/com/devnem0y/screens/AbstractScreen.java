package com.devnem0y.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.devnem0y.Application;

import static com.devnem0y.utils.Constants.*;

public abstract class AbstractScreen implements Screen{


    public final Application app;
    protected OrthographicCamera camera;
    protected Stage stage;

    public static BitmapFont fontLog, fontHUD_GREY, fontHUD_WHILE;

    public AbstractScreen(final Application app) {
        this.app = app;
        this.stage = new Stage();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, APP_WIDTH, APP_HEIGHT);
        stage = new Stage(new FitViewport(APP_WIDTH, APP_HEIGHT, camera));
        fontLog = new BitmapFont();
        fontLog.setColor(Color.SKY);
        fontHUD_WHILE = new BitmapFont(Gdx.files.internal("fonts/white_shadow.fnt"));
        fontHUD_GREY = new BitmapFont(Gdx.files.internal("fonts/grey_outline_16.fnt"));
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();
    }

    @Override
    public void render(float delta) {
        update(delta);
        app.batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
