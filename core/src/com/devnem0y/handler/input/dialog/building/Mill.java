package com.devnem0y.handler.input.dialog.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

public class Mill extends Dialog {

    private Button anvil, axe, carrot, food, mine, anvilClick, axeClick, carrotClick, foodClick, mineClick;
    private Button.ButtonStyle anvilStyle, axeStyle, carrotStyle, foodStyle, mineStyle;
    private Group group, clickGroup;
    private Skin skin;

    private boolean iconAnvil, iconAxe, iconCarrot, iconFood, iconMine;

    public Mill(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);

        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/mill.atlas")));

        anvilStyle = new Button.ButtonStyle();
        axeStyle = new Button.ButtonStyle();
        carrotStyle = new Button.ButtonStyle();
        foodStyle = new Button.ButtonStyle();
        mineStyle = new Button.ButtonStyle();
        initStyleItems();

        anvil = new Button(anvilStyle);
        axe = new Button(axeStyle);
        carrot = new Button(carrotStyle);
        food = new Button(foodStyle);
        mine = new Button(mineStyle);

        anvilClick = new Button(anvilStyle);
        axeClick = new Button(axeStyle);
        carrotClick = new Button(carrotStyle);
        foodClick = new Button(foodStyle);
        mineClick = new Button(mineStyle);

        initPositionItems();

        createPort(stage);
        createPortClick(stage);
    }

    private void initStyleItems() {
        if (gameLogic.isAnvilImproved()) {
            anvilStyle.down = skin.getDrawable("anvilimproved");
            anvilStyle.up = skin.getDrawable("anvilimproved");
        } else if (gameLogic.isAnvilActively()) {
            anvilStyle.down = skin.getDrawable("anvil");
            anvilStyle.up = skin.getDrawable("anvil");
        } else {
            anvilStyle.down = skin.getDrawable("anvilinactive");
            anvilStyle.up = skin.getDrawable("anvilinactive");
        }

        if (gameLogic.isAxeImproved()) {
            axeStyle.down = skin.getDrawable("axeimproved");
            axeStyle.up = skin.getDrawable("axeimproved");
        } else if (gameLogic.isAxeActively()) {
            axeStyle.down = skin.getDrawable("axe");
            axeStyle.up = skin.getDrawable("axe");
        } else {
            axeStyle.down = skin.getDrawable("axeinactive");
            axeStyle.up = skin.getDrawable("axeinactive");
        }

        if (gameLogic.isCarrotImproved()) {
            carrotStyle.down = skin.getDrawable("carrotimproved");
            carrotStyle.up = skin.getDrawable("carrotimproved");
        } else if (gameLogic.isCarrotActively()) {
            carrotStyle.down = skin.getDrawable("carrot");
            carrotStyle.up = skin.getDrawable("carrot");
        } else {
            carrotStyle.down = skin.getDrawable("carrotinactive");
            carrotStyle.up = skin.getDrawable("carrotinactive");
        }

        if (gameLogic.isFoodImproved()) {
            foodStyle.down = skin.getDrawable("foodimproved");
            foodStyle.up = skin.getDrawable("foodimproved");
        } else if (gameLogic.isFoodActively()) {
            foodStyle.down = skin.getDrawable("food");
            foodStyle.up = skin.getDrawable("food");
        } else {
            foodStyle.down = skin.getDrawable("foodinactive");
            foodStyle.up = skin.getDrawable("foodinactive");
        }

        if (gameLogic.isMinelImproved()) {
            mineStyle.down = skin.getDrawable("mineimproved");
            mineStyle.up = skin.getDrawable("mineimproved");
        } else if (gameLogic.isMinelActively()) {
            mineStyle.down = skin.getDrawable("mine");
            mineStyle.up = skin.getDrawable("mine");
        } else {
            mineStyle.down = skin.getDrawable("mineinactive");
            mineStyle.up = skin.getDrawable("mineinactive");
        }
    }

    private void initPositionItems() {
        final float FRAME_CENTER_WIDTH = getFrame().getWidth() / 2;
        final float FRAME_HEIGHT = getFrame().getHeight();

        if (gameLogic.isAnvilImproved()) {
            anvil.setPosition(FRAME_CENTER_WIDTH + 50, FRAME_HEIGHT - 220);
            anvilClick.setPosition(-1000, FRAME_HEIGHT - 220);
        } else if (GameScreen.gameLogic.isAnvilActively()) {
            anvil.setPosition(-1000, FRAME_HEIGHT - 220);
            anvilClick.setPosition(FRAME_CENTER_WIDTH + 50, FRAME_HEIGHT - 220);
        } else {
            anvil.setPosition(FRAME_CENTER_WIDTH + 50, FRAME_HEIGHT - 220);
            anvilClick.setPosition(-1000, FRAME_HEIGHT - 220);
        }

        if (gameLogic.isAxeImproved()) {
            axe.setPosition(FRAME_CENTER_WIDTH + 5, FRAME_HEIGHT - 130);
            axeClick.setPosition(-1000, FRAME_HEIGHT - 130);
        } else if (gameLogic.isAxeActively()) {
            axe.setPosition(-1000, FRAME_HEIGHT - 130);
            axeClick.setPosition(FRAME_CENTER_WIDTH + 5, FRAME_HEIGHT - 130);
        } else {
            axe.setPosition(FRAME_CENTER_WIDTH + 5, FRAME_HEIGHT - 130);
            axeClick.setPosition(-1000, FRAME_HEIGHT - 130);
        }

        if (gameLogic.isCarrotImproved()) {
            carrot.setPosition(FRAME_CENTER_WIDTH - 85, FRAME_HEIGHT - 130);
            carrotClick.setPosition(-1000, FRAME_HEIGHT - 130);
        } else if (gameLogic.isCarrotActively()) {
            carrot.setPosition(-1000, FRAME_HEIGHT - 130);
            carrotClick.setPosition(FRAME_CENTER_WIDTH - 85, FRAME_HEIGHT - 130);
        } else {
            carrot.setPosition(FRAME_CENTER_WIDTH - 85, FRAME_HEIGHT - 130);
            carrotClick.setPosition(-1000, FRAME_HEIGHT - 130);
        }

        if (gameLogic.isFoodImproved()) {
            food.setPosition(FRAME_CENTER_WIDTH - 130, FRAME_HEIGHT - 220);
            foodClick.setPosition(-1000, FRAME_HEIGHT - 220);
        } else if (gameLogic.isFoodActively()) {
            food.setPosition(-1000, FRAME_HEIGHT - 220);
            foodClick.setPosition(FRAME_CENTER_WIDTH - 130, FRAME_HEIGHT - 220);
        } else {
            food.setPosition(FRAME_CENTER_WIDTH - 130, FRAME_HEIGHT - 220);
            foodClick.setPosition(-1000, FRAME_HEIGHT - 220);
        }

        if (gameLogic.isMinelImproved()) {
            mine.setPosition(FRAME_CENTER_WIDTH - 40, FRAME_HEIGHT - 220);
            mineClick.setPosition(-1000, FRAME_HEIGHT - 220);
        } else if (gameLogic.isMinelActively()) {
            mine.setPosition(-1000, FRAME_HEIGHT - 220);
            mineClick.setPosition(FRAME_CENTER_WIDTH - 40, FRAME_HEIGHT - 220);
        } else {
            mine.setPosition(FRAME_CENTER_WIDTH - 40, FRAME_HEIGHT - 220);
            mineClick.setPosition(-1000, FRAME_HEIGHT - 220);
        }
    }

    private void createPortClick(Stage stage) {
        clickGroup = new Group();
        clickGroup.setPosition(getFrame().getX(), getFrame().getY());

        anvilClick.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activAnvil();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconAnvil = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconAnvil = false;
            }
        });

        axeClick.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activAxe();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconAxe = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconAxe = false;
            }
        });

        carrotClick.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activCarrot();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconCarrot = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconCarrot = false;
            }
        });

        foodClick.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activFood();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconFood = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconFood = false;
            }
        });

        mineClick.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameLogic.activMine();
            }
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconMine = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconMine = false;
            }
        });

        clickGroup.addActor(anvilClick);
        clickGroup.addActor(axeClick);
        clickGroup.addActor(carrotClick);
        clickGroup.addActor(foodClick);
        clickGroup.addActor(mineClick);
        stage.addActor(clickGroup);
    }

    private void createPort(Stage stage) {
        group = new Group();
        group.setPosition(getFrame().getX(), getFrame().getY());

        anvil.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconAnvil = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconAnvil = false;
            }
        });

        axe.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconAxe = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconAxe = false;
            }
        });

        food.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconFood = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconFood = false;
            }
        });

        carrot.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconCarrot = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconCarrot = false;
            }
        });

        mine.addListener(new ClickListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                iconMine = true;
            }
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                iconMine = false;
            }
        });

        group.addActor(anvil);
        group.addActor(axe);
        group.addActor(carrot);
        group.addActor(food);
        group.addActor(mine);
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
        if (!gameLogic.isAnvilImproved() && iconAnvil) {
            getFontText().draw(batch, "Кузница\n+30  к добыче железа\n---------------------------\nЕда (900); Дерево (3000); Камень (4800); Железо (750);", getFrame().getX() + 16, getFrame().getY() + 155, getFrame().getWidth() - 32, Align.left, true);
        }
        if (!gameLogic.isAxeImproved() && iconAxe) {
            getFontText().draw(batch, "Топор с двумя лезвиями\n+15 к добычи дерева\n---------------------------\nЕда (400); Дерево (350); Камень (300); Железо (1800);", getFrame().getX() + 16, getFrame().getY() + 155, getFrame().getWidth() - 32, Align.left, true);
        }
        if (!gameLogic.isCarrotImproved() && iconCarrot) {
            getFontText().draw(batch, "Увеличить размер полей\n+5 к добыче еды\n---------------------------\nЕда (70); Дерево (2000); Железо (340);", getFrame().getX() + 16, getFrame().getY() + 155, getFrame().getWidth() - 32, Align.left, true);
        }
        if (!gameLogic.isFoodImproved() && iconFood) {
            getFontText().draw(batch, "Скотоводство\n+7 к добыче еды\n---------------------------\nЕда (2370); Дерво (1700); Камень (1250); Железо (600);", getFrame().getX() + 16, getFrame().getY() + 155, getFrame().getWidth() - 32, Align.left, true);
        }
        if (!gameLogic.isMinelImproved() && iconMine) {
            getFontText().draw(batch, "Телега\n+10 к добыче всех ресурсов\n---------------------------\nДерево (800); Железо (270);", getFrame().getX() + 16, getFrame().getY() + 155, getFrame().getWidth() - 32, Align.left, true);
        }
    }
}
