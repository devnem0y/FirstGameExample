package com.devnem0y.handler.input.dialog.messages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.devnem0y.handler.input.dialog.Dialog;

public class DeathCivilians extends Dialog {

    private Texture label;

    public DeathCivilians(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);
        label = new Texture("image/killPeople.png");
    }

    @Override
    public void show(String text, float delaySeconds) {
        super.show(text, delaySeconds);
        getFrame().setPosition(getFrame().getX(), getFrame().getY() - 300);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(label, getFrame().getX() + 5, getFrame().getY() + getFrame().getHeight() - label.getHeight());
    }

    @Override
    public void dispose() {
        super.dispose();
        label.dispose();
    }
}
