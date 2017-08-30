package com.devnem0y.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.Application;
import com.devnem0y.managers.GameScreenManager;

import static com.devnem0y.utils.Constants.APP_HEIGHT;
import static com.devnem0y.utils.Constants.APP_WIDTH;

public class GameOver extends AbstractScreen{

    public GameOver(final Application app) {
         super(app);
}

    @Override
    public void show() {
        super.show();
        System.out.println("GAME OVER");
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            app.gsm.setScreen(GameScreenManager.STATE.MENU);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.begin();
        fontLog.draw(app.batch, "GAME OVER\npress SPACE to the MenuScreen", 20, APP_HEIGHT / 2, APP_WIDTH - 40, Align.center, true);
        app.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
