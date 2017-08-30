package com.devnem0y.handler.input.dialog.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.handler.input.dialog.Dialog;

import static com.devnem0y.screens.GameScreen.gameLogic;

public class Book extends Dialog{

    private Group group;

    public Book(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);
        create(stage);
    }

    @Override
    public void show() {
        super.show();
        group.setPosition(getFrame().getX(), getFrame().getY());
    }

    @Override
    public void hide() {
        super.hide();
        group.setPosition(getFrame().getX(), getFrame().getY());
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        getFontText().draw(batch, "Фермеры", getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 70), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "" + gameLogic.getPeasant(), getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 107), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "Дровосеки", getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 180), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "" + gameLogic.getFeller(), getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 217), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "Каменщики", getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 290), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "" + gameLogic.getMason(), getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 327), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "Рудокопы", getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 400), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "" + gameLogic.getMiner(), getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 437), getFrame().getWidth() - 40, Align.center, true);
    }

    private void create(Stage stage) {
        final float FRAME_CENTER_WIDTH = getFrame().getWidth() / 2;
        final  float FRAME_HEIGHT = getFrame().getY() + getFrame().getHeight();

        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/hud.atlas")));

        Button.ButtonStyle leftStyle = new Button.ButtonStyle();
        leftStyle.down = skin.getDrawable("leftDown");
        leftStyle.up = skin.getDrawable("leftUp");

        Button.ButtonStyle rightStyle = new Button.ButtonStyle();
        rightStyle.down = skin.getDrawable("rightDown");
        rightStyle.up = skin.getDrawable("rightUp");

        group = new Group();
        group.setPosition(getFrame().getX(), getFrame().getY());

        Button left1 = new Button(leftStyle);
        left1.setPosition(FRAME_CENTER_WIDTH - 100, FRAME_HEIGHT - 235);
        left1.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.remPeasant();
            }
        });

        Button right1 = new Button(rightStyle);
        right1.setPosition(FRAME_CENTER_WIDTH + 60, FRAME_HEIGHT - 235);
        right1.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.addPeasant();
            }
        });

        Button left2 = new Button(leftStyle);
        left2.setPosition(FRAME_CENTER_WIDTH - 100, FRAME_HEIGHT - 345);
        left2.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.remFeller();
            }
        });

        Button right2 = new Button(rightStyle);
        right2.setPosition(FRAME_CENTER_WIDTH + 60, FRAME_HEIGHT - 345);
        right2.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.addFeller();
            }
        });

        Button left3 = new Button(leftStyle);
        left3.setPosition(FRAME_CENTER_WIDTH - 100, FRAME_HEIGHT - 455);
        left3.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.remMason();
            }
        });

        Button right3 = new Button(rightStyle);
        right3.setPosition(FRAME_CENTER_WIDTH + 60, FRAME_HEIGHT - 455);
        right3.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.addMason();
            }
        });

        Button left4 = new Button(leftStyle);
        left4.setPosition(FRAME_CENTER_WIDTH - 100, FRAME_HEIGHT - 565);
        left4.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.remMiner();
            }
        });

        Button right4 = new Button(rightStyle);
        right4.setPosition(FRAME_CENTER_WIDTH + 60, FRAME_HEIGHT - 565);
        right4.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.addMiner();
            }
        });

        group.addActor(left1);
        group.addActor(right1);
        group.addActor(left2);
        group.addActor(right2);
        group.addActor(left3);
        group.addActor(right3);
        group.addActor(left4);
        group.addActor(right4);
        stage.addActor(group);
    }
}
