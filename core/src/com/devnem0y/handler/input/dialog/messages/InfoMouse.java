package com.devnem0y.handler.input.dialog.messages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.handler.input.dialog.Dialog;

import static com.devnem0y.utils.Constants.APP_HEIGHT;

public class InfoMouse extends Dialog{

    private boolean visible;
    private String leader, text;

    public InfoMouse(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);

        String FONT_CHARS = "";
        for (int i = 32; i < 127; i++) FONT_CHARS += (char)i;
        for (int i = 1024; i < 1104; i++) FONT_CHARS += (char)i;

        FreeTypeFontGenerator generatorLeader = new FreeTypeFontGenerator(Gdx.files.internal("fonts/10622.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsLeader = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsLeader.characters = FONT_CHARS;
        paramsLeader.size = 18;
        paramsLeader.color = Color.WHITE;
        setFontLeader(generatorLeader.generateFont(paramsLeader));
    }

    @Override
    public void showM(String leader, String text) {
        visible = true;
        this.leader = leader;
        this.text = text;
    }

    @Override
    public void hide(boolean openB) {
        super.hide();
        visible = false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (visible) getFrame().setPosition(Gdx.input.getX() - 90, (APP_HEIGHT - Gdx.input.getY()) - getFrame().getHeight() / 2);

        if (leader != null) {
            getFontLeader().draw(batch, leader, getFrame().getX() + 3, (getFrame().getY() + getFrame().getHeight()) - 5, getFrame().getWidth() - 6, Align.center, true);
            if (text != null) getFontText().draw(batch, text, getFrame().getX() + 3, (getFrame().getY() + getFrame().getHeight()) - 23, getFrame().getWidth() - 6, Align.center, true);
        } else if (text != null) getFontText().draw(batch, text, getFrame().getX() + 3, (getFrame().getY() + getFrame().getHeight()) - 5, getFrame().getWidth() - 6, Align.center, true);
    }
}
