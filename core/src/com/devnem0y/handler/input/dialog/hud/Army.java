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

public class Army extends Dialog{

    private Group group;

    public Army(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
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
        getFontText().draw(batch, "Войны", getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 50), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "" + gameLogic.getArmy(), getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 85), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "Атака: " + gameLogic.getArmyAttack(), getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 145), getFrame().getWidth() - 40, Align.center, true);
        getFontText().draw(batch, "Защита: " + gameLogic.getArmyDefends(), getFrame().getX() + 20, (getFrame().getY() + getFrame().getHeight() - 165), getFrame().getWidth() - 40, Align.center, true);
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
        left1.setPosition(FRAME_CENTER_WIDTH - 100, FRAME_HEIGHT - 325);
        left1.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.remArmy();
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        Button right1 = new Button(rightStyle);
        right1.setPosition(FRAME_CENTER_WIDTH + 60, FRAME_HEIGHT - 325);
        right1.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.addArmy();
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        group.addActor(left1);
        group.addActor(right1);
        stage.addActor(group);
    }
}

