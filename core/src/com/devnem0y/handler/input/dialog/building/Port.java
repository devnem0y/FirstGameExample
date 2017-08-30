package com.devnem0y.handler.input.dialog.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.handler.input.dialog.Dialog;
import com.devnem0y.screens.GameScreen;

import static com.devnem0y.screens.GameScreen.gameLogic;

public class Port extends Dialog {

    private Button anchor, gold, anchorClick, goldClick;
    private Button.ButtonStyle anchorStyle, goldStyle;
    private Group group, clickGroup;
    private Skin skin;

    private boolean iconAnchor, iconGold;

    public Port(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);

        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/port.atlas")));

        anchorStyle = new Button.ButtonStyle();
        goldStyle = new Button.ButtonStyle();
        initStyleItems();

        anchor = new Button(anchorStyle);
        gold = new Button(goldStyle);

        anchorClick = new Button(anchorStyle);
        goldClick = new Button(goldStyle);

        initPositionItems();

        createPort(stage);
        createPortClick(stage);
    }

    private void initStyleItems() {
        if (gameLogic.isAnchorImproved()) {
            anchorStyle.down = skin.getDrawable("anchorimproved");
            anchorStyle.up = skin.getDrawable("anchorimproved");
        } else if (gameLogic.isAnchorActively()) {
            anchorStyle.down = skin.getDrawable("anchor");
            anchorStyle.up = skin.getDrawable("anchor");
        } else {
            anchorStyle.down = skin.getDrawable("anchorinactive");
            anchorStyle.up = skin.getDrawable("anchorinactive");
        }

        if (gameLogic.isGoldImproved()) {
            goldStyle.down = skin.getDrawable("goldimproved");
            goldStyle.up = skin.getDrawable("goldimproved");
        } else if (gameLogic.isGoldActively()) {
            goldStyle.down = skin.getDrawable("gold");
            goldStyle.up = skin.getDrawable("gold");
        } else {
            goldStyle.down = skin.getDrawable("goldinactive");
            goldStyle.up = skin.getDrawable("goldinactive");
        }
    }

    private void initPositionItems() {
        final float FRAME_CENTER_WIDTH = getFrame().getWidth() / 2;
        final float FRAME_HEIGHT = getFrame().getHeight();

        if (gameLogic.isAnchorImproved()) {
            anchor.setPosition(FRAME_CENTER_WIDTH - 110, FRAME_HEIGHT - 138);
            anchorClick.setPosition(-1000, FRAME_HEIGHT - 138);
        } else if (GameScreen.gameLogic.isAnchorActively()) {
            anchor.setPosition(-1000, FRAME_HEIGHT - 138);
            anchorClick.setPosition(FRAME_CENTER_WIDTH - 110, FRAME_HEIGHT - 138);
        } else {
            anchor.setPosition(FRAME_CENTER_WIDTH - 110, FRAME_HEIGHT - 138);
            anchorClick.setPosition(-1000, FRAME_HEIGHT - 138);
        }

        if (gameLogic.isGoldImproved()) {
            gold.setPosition(FRAME_CENTER_WIDTH + 30, FRAME_HEIGHT - 130);
            goldClick.setPosition(-1000, FRAME_HEIGHT - 130);
        } else if (gameLogic.isGoldActively()) {
            gold.setPosition(-1000, FRAME_HEIGHT - 130);
            goldClick.setPosition(FRAME_CENTER_WIDTH + 30, FRAME_HEIGHT - 130);
        } else {
            gold.setPosition(FRAME_CENTER_WIDTH + 30, FRAME_HEIGHT - 130);
            goldClick.setPosition(-1000, FRAME_HEIGHT - 130);
        }
    }

    private void createPortClick(Stage stage) {
        clickGroup = new Group();
        clickGroup.setPosition(getFrame().getX(), getFrame().getY());

        anchorClick.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activAnchor();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconAnchor = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconAnchor = false;
            }
        });

        goldClick.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activGold();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconGold = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconGold = false;
            }
        });

        clickGroup.addActor(anchorClick);
        clickGroup.addActor(goldClick);
        stage.addActor(clickGroup);
    }

    private void createPort(Stage stage) {
        group = new Group();
        group.setPosition(getFrame().getX(), getFrame().getY());

        anchor.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconAnchor = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconAnchor = false;
            }
        });

        gold.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconGold = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconGold = false;
            }
        });

        group.addActor(anchor);
        group.addActor(gold);
        stage.addActor(group);
    }

    @Override
    public void show() {
        super.show();
        group.setPosition(getFrame().getX(), getFrame().getY());
        clickGroup.setPosition(getFrame().getX(), getFrame().getY());
    }

    @Override
    public void hide() {
        super.hide();
        group.setPosition(getFrame().getX(), getFrame().getY());
        clickGroup.setPosition(getFrame().getX(), getFrame().getY());
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        initPositionItems();
        initStyleItems();
        getFontText().draw(batch, "Описание --------------------------------------------------------------------------", getFrame().getX() + 16, getFrame().getY() + 175, getFrame().getWidth() - 32, Align.left, true);
        if (!gameLogic.isAnchorImproved() && iconAnchor) {
            getFontText().draw(batch, "Построить флот\n+92 атака\n+80 защита\n---------------------------\nЕда (1100); Дерево (4700); Железо (360); Требуется наличие армии", getFrame().getX() + 16, getFrame().getY() + 155, getFrame().getWidth() - 32, Align.left, true);
        }
        if (!gameLogic.isGoldImproved() && iconGold) {
            getFontText().draw(batch, "Торговля\n+1500 ко всем ресурсам каждые 7 дней\n---------------------------\nЕда (1850); Дерево (1200); Железо (270);", getFrame().getX() + 16, getFrame().getY() + 155, getFrame().getWidth() - 32, Align.left, true);
        }
    }
}
