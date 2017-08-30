package com.devnem0y.handler.input.dialog.messages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.devnem0y.handler.input.dialog.Dialog;

public class Info extends Dialog {

    public Info(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);
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
    public void show(String leader, String text) {
        super.show(leader, text);
        getFrame().setPosition(getFrame().getX() - 340, getFrame().getY());
    }
}
