package com.devnem0y.handler.input.dialog.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
import com.devnem0y.handler.input.dialog.Dialog;
import com.devnem0y.screens.GameScreen;

import static com.devnem0y.screens.GameScreen.dialogManager;
import static com.devnem0y.screens.GameScreen.gameLogic;
import static com.devnem0y.utils.Constants.APP_HEIGHT;

public class Fort extends Dialog {

    private Button attack1, attack2, attack3, attack4, def1, def2, def3, def4,
            attack1Click, attack2Click, attack3Click, attack4Click, def1Click, def2Click, def3Click, def4Click;
    private Button.ButtonStyle attack1Style, attack2Style, attack3Style, attack4Style, def1Style, def2Style, def3Style, def4Style;
    private Group group, clickGroup;
    private Skin skin;
    private Texture skillShem;

    public Fort(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);
        skillShem = new Texture("skillShem.png");
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/fort.atlas")));

        attack1Style = new Button.ButtonStyle();
        attack2Style = new Button.ButtonStyle();
        attack3Style = new Button.ButtonStyle();
        attack4Style = new Button.ButtonStyle();
        def1Style = new Button.ButtonStyle();
        def2Style = new Button.ButtonStyle();
        def3Style = new Button.ButtonStyle();
        def4Style = new Button.ButtonStyle();
        initStyleItems();

        attack1 = new Button(attack1Style);
        attack2 = new Button(attack2Style);
        attack3 = new Button(attack3Style);
        attack4 = new Button(attack4Style);
        def1 = new Button(def1Style);
        def2 = new Button(def2Style);
        def3 = new Button(def3Style);
        def4 = new Button(def4Style);

        attack1Click = new Button(attack1Style);
        attack2Click = new Button(attack2Style);
        attack3Click = new Button(attack3Style);
        attack4Click = new Button(attack4Style);
        def1Click = new Button(def1Style);
        def2Click = new Button(def2Style);
        def3Click = new Button(def3Style);
        def4Click = new Button(def4Style);

        initPositionItems();

        createShowFort(stage);
        createShowFortClick(stage);
    }

    @Override
    public void show() {
        super.show();
        group.setPosition(getFrame().getX(), getFrame().getY() - 114);
        clickGroup.setPosition(getFrame().getX(), getFrame().getY() - 114);
    }

    @Override
    public void hide() {
        super.hide();
        group.setPosition(getFrame().getX(), getFrame().getY() - 114);
        clickGroup.setPosition(getFrame().getX(), getFrame().getY() - 114);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(skillShem, getFrame().getX(), getFrame().getY() + 75);
        initPositionItems();
        initStyleItems();
    }

    private void createShowFortClick(Stage stage) {
        final float FRAME_HEIGHT = getFrame().getY() + getFrame().getHeight();

        clickGroup = new Group();
        clickGroup.setPosition(getFrame().getX(), getFrame().getY());

        attack1Click.setPosition(-1000, FRAME_HEIGHT / 2 + 32);
        attack1Click.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activAttack1();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isAttack1Improved()) dialogManager.info.show("ПРОСТОЙ МОЛОТ", "\n\nАтака: +4\n--------\nЖелезо: 800\nДерево: 350");
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        attack2Click.setPosition(-1000, FRAME_HEIGHT / 2 - 12);
        attack2Click.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activAttack2();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isAttack2Improved()) dialogManager.info.show("ОРУЖИЕ В ДВУХ РУКАХ", "\n\nАтака: +6\n--------\nПРОСТОЙ МОЛОТ или ЛЕГКАЯ ЗАШИТА\nЖелезо: 1700\nДерево: 900");
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        attack3Click.setPosition(-1000, FRAME_HEIGHT / 2 - 70);
        attack3Click.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activAttack3();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isAttack3Improved()) dialogManager.info.show("ЛУЧНИКИ", "Атака: +7\n--------\nЛЕГКАЯ ЗАЩИТА\nЖелезо: 650\nДерево: 1900");
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        attack4Click.setPosition(-1000, FRAME_HEIGHT / 2 - 172);
        attack4Click.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activAttack4();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isAttack4Improved()) dialogManager.info.show("БЕРЕСЕРК", "Атака: +8\n--------\nЩИТ и ДОСПЕХИ ПАЛАДИНА\nЖелезо: 3000\nДерево: 600\nКамень: 700");
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        def1Click.setPosition(-1000, FRAME_HEIGHT / 2 + 32);
        def1Click.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activDef1();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isDef1Improved()) dialogManager.info.show("ЛЕГКАЯ ЗАЩИТА", "\n\nЗащита: +3\n--------\nЕда: 500\nДерево: 200");
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        def2Click.setPosition(-1000, FRAME_HEIGHT / 2 - 70);
        def2Click.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activDef2();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isDef2Improved()) dialogManager.info.show("ЩИТ", "Защита: +5\n--------\nПРОСТОЙ МОЛОТ\nЕда: 500\nДерево: 1450\nЖелезо: 100");
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        def3Click.setPosition(-1000, FRAME_HEIGHT / 2 - 128);
        def3Click.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activDef3();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isDef3Improved()) dialogManager.info.show("ДОСПЕХИ ПАЛАДИНА", "\n\nЗащита: +6\n--------\nОРУЖИЕ В ДВУХ РУКАХ\nДерево: 370\nЖелезо: 4500\nКамень: 250");
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        def4Click.setPosition(-1000, FRAME_HEIGHT / 2 - 172);
        def4Click.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activDef4();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isDef4Improved()) dialogManager.info.show("ДОСПЕХИ БЕРЕСЕРКА", "\n\nЗащита: +7\n--------\nЛУЧНИКИ и ДОСПЕХИ ПАЛАДИНА\nЕда: 700\nДерево: 450\nЖелезо: 3200");
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        clickGroup.addActor(attack1Click);
        clickGroup.addActor(attack2Click);
        clickGroup.addActor(attack3Click);
        clickGroup.addActor(attack4Click);
        clickGroup.addActor(def1Click);
        clickGroup.addActor(def2Click);
        clickGroup.addActor(def3Click);
        clickGroup.addActor(def4Click);
        stage.addActor(clickGroup);
    }

    private void createShowFort(Stage stage) {
        final float FRAME_CENTER_WIDTH = getFrame().getWidth() / 2;

        group = new Group();
        group.setPosition(getFrame().getX(), getFrame().getY());

        attack1.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 + 32);
        attack1.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isAttack1Improved()) dialogManager.info.show("ПРОСТОЙ МОЛОТ", "\n\nАтака: +4\n--------\nЖелезо: 800\nДерево: 350");
            }

            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });
        attack2.setPosition(FRAME_CENTER_WIDTH - 40, APP_HEIGHT / 2 - 12);
        attack2.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isAttack2Improved()) dialogManager.info.show("ОРУЖИЕ В ДВУХ РУКАХ", "\n\nАтака: +6\n--------\nПРОСТОЙ МОЛОТ или ЛЕГКАЯ ЗАШИТА\nЖелезо: 1700\nДерево: 900");
            }

            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });
        attack3.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 - 70);
        attack3.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isAttack3Improved()) dialogManager.info.show("ЛУЧНИКИ", "Атака: +7\n--------\nЛЕГКАЯ ЗАЩИТА\nЖелезо: 650\nДерево: 1900");
            }

            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });
        attack4.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 - 172);
        attack4.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isAttack4Improved()) dialogManager.info.show("БЕРЕСЕРК", "Атака: +8\n--------\nЩИТ и ДОСПЕХИ ПАЛАДИНА\nЖелезо: 3000\nДерево: 600\nКамень: 700");
            }

            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });
        def1.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 + 32);
        def1.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isDef1Improved()) dialogManager.info.show("ЛЕГКАЯ ЗАЩИТА", "\n\nЗащита: +3\n--------\nЕда: 500\nДерево: 200");
            }

            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });
        def2.setPosition(FRAME_CENTER_WIDTH, APP_HEIGHT / 2 - 70);
        def2.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isDef2Improved()) dialogManager.info.show("ЩИТ", "Защита: +5\n--------\nПРОСТОЙ МОЛОТ\nЕда: 500\nДерево: 1450\nЖелезо: 100");
            }

            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });
        def3.setPosition(FRAME_CENTER_WIDTH, APP_HEIGHT / 2 - 128);
        def3.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isDef3Improved()) dialogManager.info.show("ДОСПЕХИ ПАЛАДИНА", "\n\nЗащита: +6\n--------\nОРУЖИЕ В ДВУХ РУКАХ\nДерево: 370\nЖелезо: 4500\nКамень: 250");
            }

            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });
        def4.setPosition(FRAME_CENTER_WIDTH, APP_HEIGHT / 2 - 172);
        def4.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!gameLogic.isDef4Improved()) dialogManager.info.show("ДОСПЕХИ БЕРЕСЕРКА", "\n\nЗащита: +7\n--------\nЛУЧНИКИ и ДОСПЕХИ ПАЛАДИНА\nЕда: 700\nДерево: 450\nЖелезо: 3200");
            }

            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                dialogManager.info.hide(false);
            }
        });

        group.addActor(attack1);
        group.addActor(attack2);
        group.addActor(attack3);
        group.addActor(attack4);
        group.addActor(def1);
        group.addActor(def2);
        group.addActor(def3);
        group.addActor(def4);
        stage.addActor(group);
    }

    private void initStyleItems() {
        if (gameLogic.isAttack1Improved()) {
            attack1Style.down = skin.getDrawable("attack1_improved");
            attack1Style.up = skin.getDrawable("attack1_improved");
        } else if (gameLogic.isAttack1Actively()) {
            attack1Style.down = skin.getDrawable("attack1");
            attack1Style.up = skin.getDrawable("attack1");
        } else {
            attack1Style.down = skin.getDrawable("attack1_inactively");
            attack1Style.up = skin.getDrawable("attack1_inactively");
        }

        if (gameLogic.isAttack2Improved()) {
            attack2Style.down = skin.getDrawable("attack2_improved");
            attack2Style.up = skin.getDrawable("attack2_improved");
        } else if (gameLogic.isAttack2Actively()) {
            attack2Style.down = skin.getDrawable("attack2");
            attack2Style.up = skin.getDrawable("attack2");
        } else {
            attack2Style.down = skin.getDrawable("attack2_inactively");
            attack2Style.up = skin.getDrawable("attack2_inactively");
        }

        if (gameLogic.isAttack3Improved()) {
            attack3Style.down = skin.getDrawable("attack3_improved");
            attack3Style.up = skin.getDrawable("attack3_improved");
        } else if (gameLogic.isAttack3Actively()) {
            attack3Style.down = skin.getDrawable("attack3");
            attack3Style.up = skin.getDrawable("attack3");
        } else {
            attack3Style.down = skin.getDrawable("attack3_inactively");
            attack3Style.up = skin.getDrawable("attack3_inactively");
        }

        if (gameLogic.isAttack4Improved()) {
            attack4Style.down = skin.getDrawable("attack4_improved");
            attack4Style.up = skin.getDrawable("attack4_improved");
        } else if (gameLogic.isAttack4Actively()) {
            attack4Style.down = skin.getDrawable("attack4");
            attack4Style.up = skin.getDrawable("attack4");
        } else {
            attack4Style.down = skin.getDrawable("attack4_inactively");
            attack4Style.up = skin.getDrawable("attack4_inactively");
        }

        //===================

        if (gameLogic.isDef1Improved()) {
            def1Style.down = skin.getDrawable("def1_improved");
            def1Style.up = skin.getDrawable("def1_improved");
        } else if (gameLogic.isDef1Actively()) {
            def1Style.down = skin.getDrawable("def1");
            def1Style.up = skin.getDrawable("def1");
        } else {
            def1Style.down = skin.getDrawable("def1_inactively");
            def1Style.up = skin.getDrawable("def1_inactively");
        }

        if (gameLogic.isDef2Improved()) {
            def2Style.down = skin.getDrawable("def2_improved");
            def2Style.up = skin.getDrawable("def2_improved");
        } else if (gameLogic.isDef2Actively()) {
            def2Style.down = skin.getDrawable("def2");
            def2Style.up = skin.getDrawable("def2");
        } else {
            def2Style.down = skin.getDrawable("def2_inactively");
            def2Style.up = skin.getDrawable("def2_inactively");
        }

        if (gameLogic.isDef3Improved()) {
            def3Style.down = skin.getDrawable("def3_improved");
            def3Style.up = skin.getDrawable("def3_improved");
        } else if (gameLogic.isDef3Actively()) {
            def3Style.down = skin.getDrawable("def3");
            def3Style.up = skin.getDrawable("def3");
        } else {
            def3Style.down = skin.getDrawable("def3_inactively");
            def3Style.up = skin.getDrawable("def3_inactively");
        }

        if (gameLogic.isDef4Improved()) {
            def4Style.down = skin.getDrawable("def4_improved");
            def4Style.up = skin.getDrawable("def4_improved");
        } else if (gameLogic.isDef4Actively()) {
            def4Style.down = skin.getDrawable("def4");
            def4Style.up = skin.getDrawable("def4");
        } else {
            def4Style.down = skin.getDrawable("def4_inactively");
            def4Style.up = skin.getDrawable("def4_inactively");
        }
    }

    private void initPositionItems() {
        final float FRAME_CENTER_WIDTH = getFrame().getWidth() / 2;
        final float FRAME_HEIGHT = getFrame().getY() + getFrame().getHeight();

        if (gameLogic.isAttack1Improved()) {
            attack1.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 + 32);
            attack1Click.setPosition(-1000, APP_HEIGHT / 2 + 32);
        } else if (GameScreen.gameLogic.isAttack1Actively()) {
            attack1.setPosition(-1000, APP_HEIGHT / 2 + 32);
            attack1Click.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 + 32);
        } else {
            attack1.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 + 32);
            attack1Click.setPosition(-1000, APP_HEIGHT / 2 + 32);
        }

        if (gameLogic.isAttack2Improved()) {
            attack2.setPosition(FRAME_CENTER_WIDTH - 40, APP_HEIGHT / 2 - 12);
            attack2Click.setPosition(-1000, APP_HEIGHT / 2 - 12);
        } else if (gameLogic.isAttack2Actively()) {
            attack2.setPosition(-1000, APP_HEIGHT / 2 - 12);
            attack2Click.setPosition(FRAME_CENTER_WIDTH - 40, APP_HEIGHT / 2 - 12);
        } else {
            attack2.setPosition(FRAME_CENTER_WIDTH - 40, APP_HEIGHT / 2 - 12);
            attack2Click.setPosition(-1000, APP_HEIGHT / 2 - 12);
        }

        if (gameLogic.isAttack3Improved()) {
            attack3.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 - 70);
            attack3Click.setPosition(-1000, APP_HEIGHT / 2 - 70);
        } else if (gameLogic.isAttack3Actively()) {
            attack3.setPosition(-1000, APP_HEIGHT / 2 - 70);
            attack3Click.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 - 70);
        } else {
            attack3.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 - 70);
            attack3Click.setPosition(-1000, APP_HEIGHT / 2 - 70);
        }

        if (gameLogic.isAttack4Improved()) {
            attack4.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 - 172);
            attack4Click.setPosition(-1000, APP_HEIGHT / 2 - 172);
        } else if (gameLogic.isAttack4Actively()) {
            attack4.setPosition(-1000, APP_HEIGHT / 2 - 172);
            attack4Click.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 - 172);
        } else {
            attack4.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2- 172);
            attack4Click.setPosition(-1000, APP_HEIGHT / 2 - 172);
        }

        //===================

        if (gameLogic.isDef1Improved()) {
            def1.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 + 32);
            def1Click.setPosition(-1000, APP_HEIGHT / 2 + 32);
        } else if (gameLogic.isDef1Actively()) {
            def1.setPosition(-1000, APP_HEIGHT / 2 + 32);
            def1Click.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 + 32);
        } else {
            def1.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 + 32);
            def1Click.setPosition(-1000, APP_HEIGHT / 2 + 32);
        }

        if (gameLogic.isDef2Improved()) {
            def2.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 - 70);
            def2Click.setPosition(-1000, APP_HEIGHT / 2 - 70);
        } else if (gameLogic.isDef2Actively()) {
            def2.setPosition(-1000, APP_HEIGHT / 2 - 70);
            def2Click.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 - 70);
        } else {
            def2.setPosition(FRAME_CENTER_WIDTH - 139, APP_HEIGHT / 2 - 70);
            def2Click.setPosition(-1000, APP_HEIGHT / 2 - 70);
        }

        if (gameLogic.isDef3Improved()) {
            def3.setPosition(FRAME_CENTER_WIDTH - 40, APP_HEIGHT / 2 - 128);
            def3Click.setPosition(-1000, APP_HEIGHT / 2 - 128);
        } else if (gameLogic.isDef3Actively()) {
            def3.setPosition(-1000, APP_HEIGHT / 2 - 128);
            def3Click.setPosition(FRAME_CENTER_WIDTH - 40, APP_HEIGHT / 2 - 128);
        } else {
            def3.setPosition(FRAME_CENTER_WIDTH - 40, APP_HEIGHT / 2 - 128);
            def3Click.setPosition(-1000, APP_HEIGHT / 2 - 128);
        }

        if (gameLogic.isDef4Improved()) {
            def4.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 - 172);
            def4Click.setPosition(-1000, APP_HEIGHT / 2 - 172);
        } else if (gameLogic.isDef4Actively()) {
            def4.setPosition(-1000, APP_HEIGHT / 2 - 172);
            def4Click.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 - 172);
        } else {
            def4.setPosition(FRAME_CENTER_WIDTH + 61, APP_HEIGHT / 2 - 172);
            def4Click.setPosition(-1000, APP_HEIGHT / 2 - 172);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        skillShem.dispose();
    }
}
