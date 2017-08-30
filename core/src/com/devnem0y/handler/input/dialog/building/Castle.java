package com.devnem0y.handler.input.dialog.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.handler.input.dialog.Dialog;

import static com.devnem0y.screens.GameScreen.gameLogic;

public class Castle extends Dialog {

    private TextureAtlas textureAtlas;
    private Sprite acceptM, acceptF, acceptH, acceptP;
    private Button buildM, buildF, buildH, buildP;
    private Group group;

    public Castle(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);
        textureAtlas = new TextureAtlas("image/atlas/castle.atlas");
        acceptM = new Sprite(textureAtlas.findRegion("accept"));
        acceptF = new Sprite(textureAtlas.findRegion("accept"));
        acceptH = new Sprite(textureAtlas.findRegion("accept"));
        acceptP = new Sprite(textureAtlas.findRegion("accept"));

        createButtons(stage);

        String FONT_CHARS = "";
        for (int i = 32; i < 127; i++) FONT_CHARS += (char)i;
        for (int i = 1024; i < 1104; i++) FONT_CHARS += (char)i;

        FreeTypeFontGenerator generatorLeader = new FreeTypeFontGenerator(Gdx.files.internal("fonts/10622.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsLeader = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsLeader.characters = FONT_CHARS;
        paramsLeader.size = 20;
        paramsLeader.color = Color.WHITE;
        setFontLeader(generatorLeader.generateFont(paramsLeader));
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
        // icon
        if (gameLogic.isMillActively() || gameLogic.isMillBuild()) {
            batch.draw(textureAtlas.findRegion("mill"), getFrame().getX() + 16, getFrame().getY() + 320);
            if (gameLogic.isMillBuild()) batch.draw(acceptM, getFrame().getX() + 34, getFrame().getY() + 338);
        } else batch.draw(textureAtlas.findRegion("millinactive"), getFrame().getX() + 16, getFrame().getY() + 320);
        // leader
        getFontLeader().draw(batch, "Мельница", getFrame().getX() + 16, getFrame().getY() + 305, 100, Align.center, true);
        // text
        if (!gameLogic.isMillBuild()) {
            getFontText().draw(batch, "Усовершенствования и управление ресурсами\n--------------------\nДерево: 300\nКамень: 520", getFrame().getX() + 16, getFrame().getY() + 280, 100, Align.center, true);
        }

        // icon
        if (gameLogic.isFortActively() || gameLogic.isFortBuild()) {
            batch.draw(textureAtlas.findRegion("fort"), getFrame().getX() + 132, getFrame().getY() + 320);
            if (gameLogic.isFortBuild()) batch.draw(acceptF, getFrame().getX() + 150, getFrame().getY() + 338);
        } else batch.draw(textureAtlas.findRegion("fortinactive"), getFrame().getX() + 132, getFrame().getY() + 320);
        // leader
        getFontLeader().draw(batch, "Форт", getFrame().getX() + 132, getFrame().getY() + 305, 100, Align.center, true);
        // text
        if (!gameLogic.isFortBuild()) {
            getFontText().draw(batch, "Развите и прокачка армии\n\n--------------------\nЕда: 120\nДерево: 200\nКамень: 800\nЖелезо: 200", getFrame().getX() + 132, getFrame().getY() + 280, 100, Align.center, true);
        }

        // icon
        if (gameLogic.isBighouseActively() || gameLogic.isBighouseBuild()) {
            batch.draw(textureAtlas.findRegion("bighouse"), getFrame().getX() + 248, getFrame().getY() + 320);
            if (gameLogic.isBighouseBuild()) batch.draw(acceptH, getFrame().getX() + 266, getFrame().getY() + 338);
        } else batch.draw(textureAtlas.findRegion("bighouseinactive"), getFrame().getX() + 248, getFrame().getY() + 320);
        // leader
        getFontLeader().draw(batch, "Большой дом", getFrame().getX() + 248, getFrame().getY() + 305, 100, Align.center, true);
        // text
        if (!gameLogic.isBighouseBuild()) {
            getFontText().draw(batch, "\n\n+3 человека каждые 2 дня\n--------------------\nЕда: 50\nДерево: 350\nКамень: 100", getFrame().getX() + 248, getFrame().getY() + 280, 100, Align.center, true);
        }

        // icon
        if (gameLogic.isPortActively() || gameLogic.isPortBuild()) {
            batch.draw(textureAtlas.findRegion("port"), getFrame().getX() + 364, getFrame().getY() + 320);
            if (gameLogic.isPortBuild()) batch.draw(acceptP, getFrame().getX() + 382, getFrame().getY() + 338);
        } else batch.draw(textureAtlas.findRegion("portinactive"), getFrame().getX() + 364, getFrame().getY() + 320);
        // leader
        getFontLeader().draw(batch, "Порт", getFrame().getX() + 364, getFrame().getY() + 305, 100, Align.center, true);
        // text
        if (!gameLogic.isPortBuild()) {
            getFontText().draw(batch, "Постройка флота и возможность торгавать\n--------------------\nЕда: 310\nДерево: 2600\nКамень: 950\nЖелезо: 470", getFrame().getX() + 364, getFrame().getY() + 280, 100, Align.center, true);
        }


        isActivelyButtons();
    }

    private void createButtons(Stage stage) {
        Skin skin = new Skin(textureAtlas);
        Button.ButtonStyle bStyle = new Button.ButtonStyle();
        bStyle.down = skin.getDrawable("btnS");
        bStyle.up = skin.getDrawable("btnS");

        group = new Group();
        group.setPosition(getFrame().getX(), getFrame().getY());

        buildM = new Button(bStyle);
        buildM.setPosition(0, 0);
        buildM.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.buildMill();
            }
        });

        buildF = new Button(bStyle);
        buildF.setPosition(0, 0);
        buildF.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.buildFort();
            }
        });

        buildH = new Button(bStyle);
        buildH.setPosition(0, 0);
        buildH.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.buildHouse();
            }
        });

        buildP = new Button(bStyle);
        buildP.setPosition(0, 0);
        buildP.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.buildPort();
            }
        });

        group.addActor(buildM);
        group.addActor(buildF);
        group.addActor(buildH);
        group.addActor(buildP);
        stage.addActor(group);
    }

    private void isActivelyButtons() {
        if (gameLogic.isMillActively() && !gameLogic.isMillBuild()) buildM.setPosition(21, 95);
        else buildM.setPosition(-1000, 0);

        if (gameLogic.isFortActively() && !gameLogic.isFortBuild()) buildF.setPosition(137, 95);
        else buildF.setPosition(-1000, 0);

        if (gameLogic.isBighouseActively() && !gameLogic.isBighouseBuild()) buildH.setPosition(253, 95);
        else buildH.setPosition(-1000, 0);

        if (gameLogic.isPortActively() && !gameLogic.isPortBuild()) buildP.setPosition(369, 95);
        else buildP.setPosition(-1000, 0);
    }

    @Override
    public void dispose() {
        super.dispose();
        textureAtlas.dispose();
    }
}
