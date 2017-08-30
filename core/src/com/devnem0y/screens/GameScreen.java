package com.devnem0y.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.devnem0y.Application;
import com.devnem0y.handler.GameLogic;
import com.devnem0y.handler.input.ButtonsHud;
import com.devnem0y.managers.DialogManager;
import com.devnem0y.managers.GameScreenManager;

public class GameScreen extends AbstractScreen{

    public GameScreen(final Application app) {
        super(app);
    }

    public static GameLogic gameLogic;
    private Pixmap pm;
    private Texture bgMap, HUD;
    public static DialogManager dialogManager;
    public static ButtonsHud buttonsHud;

    @Override
    public void show() {
        super.show();
        stage.clear();
        System.out.println("GAME");

        gameLogic = new GameLogic();
        gameLogic.createGame();

        bgMap = new Texture("bgMap.png");
        HUD = new Texture("hud.png");
        pm = new Pixmap(Gdx.files.internal("cursorImage.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));

        buttonsHud = new ButtonsHud();
        buttonsHud.createHUD(stage, gameLogic);
        dialogManager = new DialogManager(stage);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            app.gsm.setScreen(GameScreenManager.STATE.MENU);
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        gameLogic.update(app, delta);
        buttonsHud.initClick(gameLogic);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.begin();
        app.batch.draw(bgMap, 0, 0);
        app.batch.draw(HUD, 0, 0);
        gameLogic.renderMap(app.batch);
        buttonsHud.draw(app.batch, gameLogic);
        dialogManager.render(app.batch);
        fontLog.draw(app.batch, "press BACKSPACE return MenuScreen", 10, 60);
        fontLog.draw(app.batch, "press ESC to the exit", 10, 25);
        app.batch.end();
        stage.draw();
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
        bgMap.dispose();
        pm.dispose();
        HUD.dispose();
        buttonsHud.dispose();
        dialogManager.dispose();
    }
}
