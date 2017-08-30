package com.devnem0y.handler.input.dialog.messages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.devnem0y.handler.input.dialog.Dialog;

public class ArmyIsCrushed extends Dialog {

    private Texture label;

    public ArmyIsCrushed(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);
        label = new Texture("image/crushed.png");
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(label, (getFrame().getX() + getFrame().getWidth() / 2) - label.getWidth() / 2, (getFrame().getY() + getFrame().getHeight() / 2) - label.getHeight() / 2);
    }

    @Override
    public void dispose() {
        super.dispose();
        label.dispose();
    }
}
