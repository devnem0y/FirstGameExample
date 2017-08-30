package com.devnem0y.handler.input;

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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.handler.GameLogic;

import static com.devnem0y.screens.AbstractScreen.fontHUD_GREY;
import static com.devnem0y.screens.AbstractScreen.fontHUD_WHILE;
import static com.devnem0y.screens.GameScreen.dialogManager;
import static com.devnem0y.utils.Constants.*;

public class ButtonsHud {

    private TextureAtlas hudAtlas;

    private Skin skin, building;
    private Button castle, fort, mill, port, house, t1, t2, enemy1, enemy2, enemy3, enemy4, enemy5;
    private Slider sliderGameSpeed;

    public ButtonsHud() {
        hudAtlas = new TextureAtlas("image/atlas/hud.atlas");
        skin = new Skin();
        skin.addRegions(hudAtlas);
        building = new Skin();
        building.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/objMap.atlas")));
        initFont();
    }

    private void initFont() {
        String FONT_CHARS = "";
        for (int i = 32; i < 127; i++) FONT_CHARS += (char)i;
        for (int i = 1024; i < 1104; i++) FONT_CHARS += (char)i;

        FreeTypeFontGenerator generatorText = new FreeTypeFontGenerator(Gdx.files.internal("fonts/10622.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsText = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsText.characters = FONT_CHARS;
        paramsText.size = 18;
        paramsText.color = Color.GRAY;
        fontHUD_GREY = generatorText.generateFont(paramsText);

        FreeTypeFontGenerator generatorLeader = new FreeTypeFontGenerator(Gdx.files.internal("fonts/10622.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsLeader = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsLeader.characters = FONT_CHARS;
        paramsLeader.size = 13;
        paramsLeader.color = Color.WHITE;
        fontHUD_WHILE = generatorLeader.generateFont(paramsLeader);
    }

    public void draw(SpriteBatch batch, GameLogic gameLogic) {
        batch.draw(hudAtlas.findRegion("nextDayUp"), APP_WIDTH / 2 - 40, APP_HEIGHT - 80);
        fontHUD_GREY.draw(batch, gameLogic.getDay() + " ДНИ", APP_WIDTH / 2 - 40, APP_HEIGHT - 75, 80, Align.center, true);

        fontHUD_WHILE.draw(batch, "" + (int)gameLogic.getFood(), APP_WIDTH - 138, APP_HEIGHT - 49);
        fontHUD_WHILE.draw(batch, "" + (int)gameLogic.getWood(), APP_WIDTH - 138, APP_HEIGHT - 115);
        fontHUD_WHILE.draw(batch, "" + (int)gameLogic.getRock(), APP_WIDTH - 138, APP_HEIGHT - 180);
        fontHUD_WHILE.draw(batch, "" + (int)gameLogic.getIron(), APP_WIDTH - 138, APP_HEIGHT - 249);
        fontHUD_WHILE.draw(batch, "" + gameLogic.getPeopleIdle() + "/" + gameLogic.getPeopleMax(), 81, APP_HEIGHT - 40);
    }

    public void createHUD(Stage stage, final GameLogic gameLogic) {
        Group hudGroup = new Group();
        hudGroup.setPosition(0, 0);

        Button.ButtonStyle bookStyle = new Button.ButtonStyle();
        bookStyle.down = skin.getDrawable("bookDown");
        bookStyle.up = skin.getDrawable("bookUp");

        Button.ButtonStyle armyStyle = new Button.ButtonStyle();
        armyStyle.down = skin.getDrawable("armyDown");
        armyStyle.up = skin.getDrawable("armyUp");

        Button book = new Button(bookStyle);
        book.setPosition(10, APP_HEIGHT - 150);
        book.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (gameLogic.isBookOpen()) dialogManager.book.show();
            }
        });

        Button army = new Button(armyStyle);
        army.setPosition(10, APP_HEIGHT - 225);
        army.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (gameLogic.isArmyOpen()) dialogManager.army.show();
            }
        });

        initSlider();
        createMap();

        hudGroup.addActor(book);
        hudGroup.addActor(army);
        hudGroup.addActor(sliderGameSpeed);
        hudGroup.addActor(castle);
        hudGroup.addActor(t1);
        hudGroup.addActor(t2);
        hudGroup.addActor(enemy1);
        hudGroup.addActor(enemy2);
        hudGroup.addActor(enemy3);
        hudGroup.addActor(enemy4);
        hudGroup.addActor(enemy5);
        hudGroup.addActor(fort);
        hudGroup.addActor(mill);
//        hudGroup.addActor(house);
        hudGroup.addActor(port);
        stage.addActor(hudGroup);
    }

    private void initSlider() {
        Skin sliderSkin = new Skin(new TextureAtlas(Gdx.files.internal("image/atlas/mySlider.atlas")));
        Slider.SliderStyle style = new Slider.SliderStyle();
        style.background = sliderSkin.getDrawable("slider");
        style.knob = sliderSkin.getDrawable("knob");
        sliderGameSpeed = new Slider(1, 15, 3, false, style);
        sliderGameSpeed.setPosition(APP_WIDTH / 2 + 150, APP_HEIGHT - 40);
        sliderGameSpeed.setValue(15);
    }

    public Slider getSliderGameSpeed() {
        return sliderGameSpeed;
    }

    private void createMap() {
        Button.ButtonStyle castleStyle = new Button.ButtonStyle();
        castleStyle.down = building.getDrawable("castle");
        castleStyle.up = building.getDrawable("castle");

        Button.ButtonStyle millStyle = new Button.ButtonStyle();
        millStyle.down = building.getDrawable("mill");
        millStyle.up = building.getDrawable("mill");

        Button.ButtonStyle fortStyle = new Button.ButtonStyle();
        fortStyle.down = building.getDrawable("fort");
        fortStyle.up = building.getDrawable("fort");

//        Button.ButtonStyle houseStyle = new Button.ButtonStyle();
//        houseStyle.down = building.getDrawable("house");
//        houseStyle.up = building.getDrawable("house");

        Button.ButtonStyle portStyle = new Button.ButtonStyle();
        portStyle.down = building.getDrawable("lighthouse");
        portStyle.up = building.getDrawable("lighthouse");

        Button.ButtonStyle t1Style = new Button.ButtonStyle();
        t1Style.down = building.getDrawable("pointer-road");
        t1Style.up = building.getDrawable("pointer-road");

        Button.ButtonStyle t2Style = new Button.ButtonStyle();
        t2Style.down = building.getDrawable("signpost");
        t2Style.up = building.getDrawable("signpost");

        Button.ButtonStyle enemy1Style = new Button.ButtonStyle();
        enemy1Style.down = building.getDrawable("spider");
        enemy1Style.up = building.getDrawable("spider");

        Button.ButtonStyle enemy2Style = new Button.ButtonStyle();
        enemy2Style.down = building.getDrawable("animal-skull");
        enemy2Style.up = building.getDrawable("animal-skull");

        Button.ButtonStyle enemy3Style = new Button.ButtonStyle();
        enemy3Style.down = building.getDrawable("octopus");
        enemy3Style.up = building.getDrawable("octopus");

        Button.ButtonStyle enemy4Style = new Button.ButtonStyle();
        enemy4Style.down = building.getDrawable("ship-pirates");
        enemy4Style.up = building.getDrawable("ship-pirates");

        Button.ButtonStyle enemy5Style = new Button.ButtonStyle();
        enemy5Style.down = building.getDrawable("scorpion");
        enemy5Style.up = building.getDrawable("scorpion");

        castle = new Button(castleStyle);
        castle.setPosition(100, 368);

        mill = new Button(millStyle);
        fort = new Button(fortStyle);
//        house = new Button(houseStyle);
        port = new Button(portStyle);

        t1 = new Button(t1Style);
        t1.setPosition(612, 407);
        t2 = new Button(t2Style);
        t2.setPosition(1032, 367);

        enemy1 = new Button(enemy1Style);
        enemy2 = new Button(enemy2Style);
        enemy3 = new Button(enemy3Style);
        enemy4 = new Button(enemy4Style);
        enemy5 = new Button(enemy5Style);

    }

    public void initClick(final GameLogic gameLogic) {
        if (castle != null) {
            castle.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if (gameLogic.isCastleOpen()) dialogManager.castle.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isCastleOpen()) {
                        dialogManager.reminder.show("Замок", false, 0);
                        gameLogic.setOutlineC(true);
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    gameLogic.setOutlineC(false);
                    dialogManager.reminder.hide(false);
                }
            });
        }

        if (gameLogic.isFortBuild()) fort.setPosition(317, 230);
        else fort.setPosition(-1000, 230);
        if (fort != null) {
            fort.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if (gameLogic.isFortOpen()) dialogManager.fort.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isFortOpen()) {
                        gameLogic.setOutlineF(true);
                        dialogManager.reminder.show("Форт", false, 0);
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    gameLogic.setOutlineF(false);
                    dialogManager.reminder.hide(false);
                }
            });
        }

        if (gameLogic.isMillBuild()) mill.setPosition(176, 247);
        else mill.setPosition(-1000, 247);
        if (mill != null) {
            mill.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if (gameLogic.isMillOpen()) dialogManager.mill.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isMillOpen()) {
                        gameLogic.setOutlineM(true);
                        dialogManager.reminder.show("Мельница", false, 0);
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    gameLogic.setOutlineM(false);
                    dialogManager.reminder.hide(false);
                }
            });
        }

//        if (gameLogic.isBighouseBuild()) house.setPosition(639, 100);
//        else house.setPosition(-1000, 100);
//        if (house != null) {
//            house.addListener(new ClickListener() {
//                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                    return true;
//                }
//                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//                    if (gameLogic.isHouseOpen()) System.out.println("HOUSE");
//                }
//            });
//        }

        if (gameLogic.isPortBuild()) port.setPosition(75, 157);
        else port.setPosition(-1000, 157);
        if (port != null) {
            port.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if (gameLogic.isPortOpen()) dialogManager.port.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isPortOpen()) {
                        gameLogic.setOutlineL(true);
                        dialogManager.reminder.show("Порт", false, 0);
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    gameLogic.setOutlineL(false);
                    dialogManager.reminder.hide(false);
                }
            });
        }

        if (t1 != null) {
            t1.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    //if (gameLogic.isCastleOpen())
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isCastleOpen()) {
                        dialogManager.reminder.show("Указатель", false, 0);
                        gameLogic.setOutlinePr(true);
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlinePr(false);
                        dialogManager.reminder.hide(false);
                    }
                }
            });
        }

        if (t2 != null) {
            t2.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    //if (gameLogic.isCastleOpen())
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isCastleOpen()) {
                        dialogManager.reminder.show("Табличка", false, 0);
                        gameLogic.setOutlineSig(true);
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineSig(false);
                        dialogManager.reminder.hide(false);
                    }
                }
            });
        }

        if (gameLogic.enemyArmy.isAlive()) enemy1.setPosition(608, 505);
        else enemy1.setPosition(-1000, 505);
        if (enemy1 != null) {
            enemy1.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    //if (gameLogic.isPortOpen()) dialogManager.port.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineS(true);
                        dialogManager.infoMouse.showM("Паук", "атака: 0 защита: 0");
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineS(false);
                        dialogManager.infoMouse.hide(false);
                    }
                }
            });
        }

        if (gameLogic.enemyArmy.isAlive()) enemy2.setPosition(1054, 612);
        else enemy2.setPosition(-1000, 612);
        if (enemy2 != null) {
            enemy2.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    //if (gameLogic.isPortOpen()) dialogManager.port.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineAs(true);
                        dialogManager.infoMouse.showM("Тролли", "атака: 0 защита: 0");
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineAs(false);
                        dialogManager.infoMouse.hide(false);
                    }
                }
            });
        }

        if (gameLogic.enemyArmy.isAlive()) enemy3.setPosition(164, 637);
        else enemy3.setPosition(-1000, 637);
        if (enemy3 != null) {
            enemy3.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    //if (gameLogic.isPortOpen()) dialogManager.port.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineOct(true);
                        dialogManager.infoMouse.showM("Осминог", "атака: 0 защита: 0");
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineOct(false);
                        dialogManager.infoMouse.hide(false);
                    }
                }
            });
        }

        if (gameLogic.enemyArmy.isAlive()) enemy4.setPosition(388, 5);
        else enemy4.setPosition(-1000, 5);
        if (enemy4 != null) {
            enemy4.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    //if (gameLogic.isPortOpen()) dialogManager.port.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineSp(true);
                        dialogManager.infoMouse.showM("Пираты", "атака: 0 защита: 0");
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineSp(false);
                        dialogManager.infoMouse.hide(false);
                    }
                }
            });
        }

        if (gameLogic.enemyArmy.isAlive()) enemy5.setPosition(995, 150);
        else enemy5.setPosition(-1000, 150);
        if (enemy5 != null) {
            enemy5.addListener(new ClickListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    //if (gameLogic.isPortOpen()) dialogManager.port.show();
                }
                public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineScor(true);
                        dialogManager.infoMouse.showM("Скорпион", "атака: 0 защита: 0");
                    }
                }
                public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (gameLogic.isCastleOpen()) {
                        gameLogic.setOutlineScor(false);
                        dialogManager.infoMouse.hide(false);
                    }
                }
            });
        }
    }

    public void dispose() {
        hudAtlas.dispose();
    }
}
