package com.devnem0y.handler.input.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import static com.devnem0y.screens.GameScreen.gameLogic;
import static com.devnem0y.utils.Constants.*;

public class Dialog {

    private Rectangle frame;
    private Texture texture;
    private boolean onExit;
    private Button exit;
    private BitmapFont fontLeader, fontText;
    private String leader = null, text = null;
    private int align;

    public Dialog(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        frame = new Rectangle();
        frame.width = width;
        frame.height = height;
        texture = new Texture("show.png");
        frame.setPosition(-1000, APP_HEIGHT / 2 - frame.getHeight() / 2);
        this.onExit = onExit;
        if (onExit) initButtonExit(stage);
//        fontLeader = new BitmapFont(Gdx.files.internal("fonts/white_shadow.fnt"));
//        fontText = new BitmapFont(Gdx.files.internal("fonts/white_shadow.fnt"));
        fontLeader = new BitmapFont();
        fontText = new BitmapFont();
        initFont();
        this.leader = leader;
        this.text = text;
        this.align = align;
    }

    private void initFont() {
        String FONT_CHARS = "";
        for (int i = 32; i < 127; i++) FONT_CHARS += (char)i;
        for (int i = 1024; i < 1104; i++) FONT_CHARS += (char)i;

        FreeTypeFontGenerator generatorText = new FreeTypeFontGenerator(Gdx.files.internal("fonts/10622.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsText = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsText.characters = FONT_CHARS;
        paramsText.size = 14;
        paramsText.color = Color.WHITE;
        fontText = generatorText.generateFont(paramsText);

        FreeTypeFontGenerator generatorLeader = new FreeTypeFontGenerator(Gdx.files.internal("fonts/10622.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsLeader = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsLeader.characters = FONT_CHARS;
        paramsLeader.size = 26;
        paramsLeader.color = Color.WHITE;
        fontLeader = generatorLeader.generateFont(paramsLeader);
    }

    public void show() {
        closeButtons();
        frame.setPosition(APP_WIDTH / 2 - frame.getWidth() / 2, APP_HEIGHT / 2 - frame.getHeight() / 2);
        if (exit != null) {
            exit.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void show(String text) {
        closeButtons();
        this.text = text;
        frame.setPosition(APP_WIDTH / 2 - frame.getWidth() / 2, APP_HEIGHT / 2 - frame.getHeight() / 2);
        if (exit != null) {
            exit.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void showM(String leader, String text) {
        closeButtons();
        this.text = text;
        this.leader = leader;
    }

    public void show(String leader, String text) {
        closeButtons();
        this.text = text;
        this.leader = leader;
        frame.setPosition(APP_WIDTH / 2 - frame.getWidth() / 2, APP_HEIGHT / 2 - frame.getHeight() / 2);
        if (exit != null) {
            exit.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void show(String text, float delaySeconds) {
        this.text = text;
        frame.setPosition(APP_WIDTH / 2 - frame.getWidth() / 2, APP_HEIGHT / 2 - frame.getHeight() / 2);
        if (exit != null) {
            exit.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
        if (!onExit) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    hide(false);
                }
            }, delaySeconds);
        }
    }

    public void show(String text, boolean autoHide, float delaySeconds) {
        this.text = text;
        frame.setPosition(APP_WIDTH / 2 - frame.getWidth() / 2, APP_HEIGHT / 2 - frame.getHeight() / 2);
        if (exit != null) {
            exit.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
        if (!onExit && autoHide) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    hide(false);
                }
            }, delaySeconds);
        }
    }

    public void hide() {
        openButtons();
        frame.setPosition(-1000, APP_HEIGHT / 2 - frame.getHeight() / 2);
        if (exit != null) {
            exit.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void hide(boolean openB) {
        if (openB) openButtons();
        frame.setPosition(-1000, APP_HEIGHT / 2 - frame.getHeight() / 2);
        if (exit != null) {
            exit.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void draw(SpriteBatch batch) {
        if (texture != null) batch.draw(texture, frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        if (leader != null) {
            fontLeader.draw(batch, leader, frame.getX() + 20, (frame.getY() + frame.getHeight() - 20), frame.getWidth() - 40, Align.center, true);
            if (text != null) fontText.draw(batch, text, frame.getX() + 20, (frame.getY() + frame.getHeight() - 45), frame.getWidth() - 40, align, true);
        } else if (text != null) fontText.draw(batch, text, frame.getX() + 72, (frame.getY() + frame.getHeight() - 20), frame.getWidth() - 92, align, true);
    }

    private void initButtonExit(Stage stage) {
        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/hud.atlas")));
        Button.ButtonStyle exitStyle = new Button.ButtonStyle();
        exitStyle.down = skin.getDrawable("checkDown");
        exitStyle.up = skin.getDrawable("checkUp");
        exit = new Button(exitStyle);
        exit.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        exit.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                hide();
            }
        });

        stage.addActor(exit);
    }

    private void closeButtons() {
        gameLogic.setBookOpen(false);
        gameLogic.setArmyOpen(false);
        gameLogic.setCastleOpen(false);
        gameLogic.setFortOpen(false);
        gameLogic.setMillOpen(false);
        gameLogic.setHouseOpen(false);
        gameLogic.setPortOpen(false);
    }

    private void openButtons() {
        gameLogic.setBookOpen(true);
        gameLogic.setArmyOpen(true);
        gameLogic.setCastleOpen(true);
        gameLogic.setFortOpen(true);
        gameLogic.setMillOpen(true);
        gameLogic.setHouseOpen(true);
        gameLogic.setPortOpen(true);
    }

    protected Rectangle getFrame() {
        return frame;
    }

    public BitmapFont getFontLeader() {
        return fontLeader;
    }

    public BitmapFont getFontText() {
        return fontText;
    }

    public void setFontText(BitmapFont fontText) {
        this.fontText = fontText;
    }

    public void setFontLeader(BitmapFont fontLeader) {
        this.fontLeader = fontLeader;
    }

    public void dispose() {
        if (texture != null) texture.dispose();
    }
}
