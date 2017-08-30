package com.devnem0y.handler.input.dialog.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.devnem0y.handler.input.dialog.Dialog;

public class Battle extends Dialog {

    private Texture grid, iconSquad1, iconSquad2, iconSquad3, iconSquad4;
    private Rectangle squad1, squad2, squad3, squad4;
    private boolean activ1, activ2, activ3, activ4;
    private Button leftS1, rightS1, upS1, downS1,
            leftS2, rightS2, upS2, downS2,
            leftS3, rightS3, upS3, downS3,
            leftS4, rightS4, upS4, downS4;

    public Battle(int width, int height, String leader, String text, int align, boolean onExit, Stage stage) {
        super(width, height, leader, text, align, onExit, stage);
        grid = new Texture("image/grid_2.png");
        initSquads();
        initMove(stage);
    }

    private void initSquads() {
        iconSquad1 = new Texture("image/squad1.png");
        squad1 = new Rectangle();
        squad1.setPosition(getFrame().getX() + 5, getFrame().getY() + 5);
        squad1.width = 60;
        squad1.height = 60;

        iconSquad2 = new Texture("image/squad2.png");
        squad2 = new Rectangle();
        squad2.setPosition(getFrame().getX() + 5, getFrame().getY() + 5);
        squad2.width = 60;
        squad2.height = 60;

        iconSquad3 = new Texture("image/squad3.png");
        squad3 = new Rectangle();
        squad3.setPosition(getFrame().getX() + 5, getFrame().getY() + 5);
        squad3.width = 60;
        squad3.height = 60;

        iconSquad4 = new Texture("image/squad4.png");
        squad4 = new Rectangle();
        squad4.setPosition(getFrame().getX() + 5, getFrame().getY() + 5);
        squad4.width = 60;
        squad4.height = 60;

    }

    @Override
    public void show() {
        super.show();
        squad4.setPosition(getFrame().getX() + 8, getFrame().getY() + 7);
        squad3.setPosition(getFrame().getX() + 8, getFrame().getY() + 70);
        squad2.setPosition(getFrame().getX() + 71, getFrame().getY() + 133);
        squad1.setPosition(getFrame().getX() + 8, getFrame().getY() + 196);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        update();
        batch.draw(iconSquad1, squad1.getX(), squad1.getY());
        batch.draw(iconSquad2, squad2.getX(), squad2.getY());
        batch.draw(iconSquad3, squad3.getX(), squad3.getY());
        batch.draw(iconSquad4, squad4.getX(), squad4.getY());
        squadSetPosition();

        batch.draw(grid, getFrame().getX() + 5, getFrame().getY() + 5);
    }

    private void squadSetPosition() {
        if (activ1) {
            if ((squad1.getX() + 63 == squad2.getX() && (squad1.getY() == squad2.getY()))
                    || (squad1.getX() + 63 == squad3.getX() && (squad1.getY() == squad3.getY()))
                    || (squad1.getX() + 63 == squad4.getX() && (squad1.getY() == squad4.getY()))) {
                rightS1.setPosition(-1000, squad1.getY());
            } else {
                if (squad1.getX() != 894)
                    rightS1.setPosition(squad1.getX() + 60, squad1.getY() - 2);
                else rightS1.setPosition(-1000, squad1.getY());
            }

            if ((squad1.getX() == squad2.getX() + 63 && (squad1.getY() == squad2.getY()))
                    || (squad1.getX() == squad3.getX() + 63 && (squad1.getY() == squad3.getY()))
                    || (squad1.getX() == squad4.getX() + 63 && (squad1.getY() == squad4.getY()))) {
                leftS1.setPosition(-1000, squad1.getY());
            } else {
                if (squad1.getX() != getFrame().getX() + 8)
                    leftS1.setPosition(squad1.getX() - 65, squad1.getY() - 2);
                else leftS1.setPosition(-1000, squad1.getY());
            }

            if (((squad1.getX() == squad2.getX()) && (squad1.getY() + 63 == squad2.getY()))
                    || ((squad1.getX() == squad3.getX()) && (squad1.getY() + 63 == squad3.getY()))
                    || ((squad1.getX() == squad4.getX()) && (squad1.getY() + 63 == squad4.getY()))) {
                upS1.setPosition(-1000, squad1.getY() + 62);
            } else {
                if (squad1.getY() != 487)
                    upS1.setPosition(squad1.getX() - 3, squad1.getY() + 62);
                else upS1.setPosition(-1000, squad1.getY() + 62);
            }

            if (((squad1.getX() == squad2.getX()) && (squad1.getY() == squad2.getY() + 63))
                    || ((squad1.getX() == squad3.getX()) && (squad1.getY() == squad3.getY() + 63))
                    || ((squad1.getX() == squad4.getX()) && (squad1.getY() == squad4.getY() + 63))) {
                downS1.setPosition(-1000, squad1.getY() - 65);
            } else {
                if (squad1.getY() != getFrame().getY() + 7 )
                    downS1.setPosition(squad1.getX() - 3, squad1.getY() - 65);
                else downS1.setPosition(-1000, squad1.getY() - 65);
            }
        } else {
            rightS1.setPosition(-1000, squad1.getY());
            leftS1.setPosition(-1000, squad1.getY());
            upS1.setPosition(-1000, squad1.getY() + 62);
            downS1.setPosition(-1000, squad1.getY() - 65);
        }

        if (activ2) {
            if ((squad2.getX() + 63 == squad1.getX() && (squad2.getY() == squad1.getY()))
                    || (squad2.getX() + 63 == squad3.getX() && (squad2.getY() == squad3.getY()))
                    || (squad2.getX() + 63 == squad4.getX() && (squad2.getY() == squad4.getY()))) {
                rightS2.setPosition(-1000, squad2.getY());
            } else {
                if (squad2.getX() != 894)
                    rightS2.setPosition(squad2.getX() + 60, squad2.getY() - 2);
                else rightS2.setPosition(-1000, squad2.getY());
            }

            if ((squad2.getX() == squad1.getX() + 63 && (squad2.getY() == squad1.getY()))
                    || (squad2.getX() == squad3.getX() + 63 && (squad2.getY() == squad3.getY()))
                    || (squad2.getX() == squad4.getX() + 63 && (squad2.getY() == squad4.getY()))) {
                leftS2.setPosition(-1000, squad2.getY());
            } else {
                if (squad2.getX() != getFrame().getX() + 8)
                    leftS2.setPosition(squad2.getX() - 65, squad2.getY() - 2);
                else leftS2.setPosition(-1000, squad2.getY());
            }

            if (((squad2.getX() == squad1.getX()) && (squad2.getY() + 63 == squad1.getY()))
                    || ((squad2.getX() == squad3.getX()) && (squad2.getY() + 63 == squad3.getY()))
                    || ((squad2.getX() == squad4.getX()) && (squad2.getY() + 63 == squad4.getY()))) {
                upS2.setPosition(-1000, squad2.getY() + 62);
            } else {
                if (squad2.getY() != 487)
                    upS2.setPosition(squad2.getX() - 3, squad2.getY() + 62);
                else upS2.setPosition(-1000, squad2.getY() + 62);
            }

            if (((squad2.getX() == squad1.getX()) && (squad2.getY() == squad1.getY() + 63))
                    || ((squad2.getX() == squad3.getX()) && (squad2.getY() == squad3.getY() + 63))
                    || ((squad2.getX() == squad4.getX()) && (squad2.getY() == squad4.getY() + 63))) {
                downS2.setPosition(-1000, squad2.getY() - 65);
            } else {
                if (squad2.getY() != getFrame().getY() + 7 )
                    downS2.setPosition(squad2.getX() - 3, squad2.getY() - 65);
                else downS2.setPosition(-1000, squad2.getY() - 65);
            }
        } else {
            rightS2.setPosition(-1000, squad2.getY());
            leftS2.setPosition(-1000, squad2.getY());
            upS2.setPosition(-1000, squad2.getY() + 62);
            downS2.setPosition(-1000, squad2.getY() - 65);
        }

        if (activ3) {
            if ((squad3.getX() + 63 == squad1.getX() && (squad3.getY() == squad1.getY()))
                    || (squad3.getX() + 63 == squad2.getX() && (squad3.getY() == squad2.getY()))
                    || (squad3.getX() + 63 == squad4.getX() && (squad3.getY() == squad4.getY()))) {
                rightS3.setPosition(-1000, squad3.getY());
            } else {
                if (squad3.getX() != 894)
                    rightS3.setPosition(squad3.getX() + 60, squad3.getY() - 2);
                else rightS3.setPosition(-1000, squad3.getY());
            }

            if ((squad3.getX() == squad1.getX() + 63 && (squad3.getY() == squad1.getY()))
                    || (squad3.getX() == squad2.getX() + 63 && (squad3.getY() == squad2.getY()))
                    || (squad3.getX() == squad4.getX() + 63 && (squad3.getY() == squad4.getY()))) {
                leftS3.setPosition(-1000, squad3.getY());
            } else {
                if (squad3.getX() != getFrame().getX() + 8)
                    leftS3.setPosition(squad3.getX() - 65, squad3.getY() - 2);
                else leftS3.setPosition(-1000, squad3.getY());
            }

            if (((squad3.getX() == squad1.getX()) && (squad3.getY() + 63 == squad1.getY()))
                    || ((squad3.getX() == squad2.getX()) && (squad3.getY() + 63 == squad2.getY()))
                    || ((squad3.getX() == squad4.getX()) && (squad3.getY() + 63 == squad4.getY()))) {
                upS3.setPosition(-1000, squad3.getY() + 62);
            } else {
                if (squad3.getY() != 487)
                    upS3.setPosition(squad3.getX() - 3, squad3.getY() + 62);
                else upS3.setPosition(-1000, squad3.getY() + 62);
            }

            if (((squad3.getX() == squad1.getX()) && (squad3.getY() == squad1.getY() + 63))
                    || ((squad3.getX() == squad2.getX()) && (squad3.getY() == squad2.getY() + 63))
                    || ((squad3.getX() == squad4.getX()) && (squad3.getY() == squad4.getY() + 63))) {
                downS3.setPosition(-1000, squad3.getY() - 65);
            } else {
                if (squad3.getY() != getFrame().getY() + 7 )
                    downS3.setPosition(squad3.getX() - 3, squad3.getY() - 65);
                else downS3.setPosition(-1000, squad3.getY() - 65);
            }
        } else {
            rightS3.setPosition(-1000, squad3.getY());
            leftS3.setPosition(-1000, squad3.getY());
            upS3.setPosition(-1000, squad3.getY() + 62);
            downS3.setPosition(-1000, squad3.getY() - 65);
        }

        if (activ4) {
            if ((squad4.getX() + 63 == squad1.getX() && (squad4.getY() == squad1.getY()))
                    || (squad4.getX() + 63 == squad2.getX() && (squad4.getY() == squad2.getY()))
                    || (squad4.getX() + 63 == squad3.getX() && (squad4.getY() == squad3.getY()))) {
                rightS4.setPosition(-1000, squad4.getY());
            } else {
                if (squad4.getX() != 894)
                    rightS4.setPosition(squad4.getX() + 60, squad4.getY() - 2);
                else rightS4.setPosition(-1000, squad4.getY());
            }

            if ((squad4.getX() == squad1.getX() + 63 && (squad4.getY() == squad1.getY()))
                    || (squad4.getX() == squad2.getX() + 63 && (squad4.getY() == squad2.getY()))
                    || (squad4.getX() == squad3.getX() + 63 && (squad4.getY() == squad3.getY()))) {
                leftS4.setPosition(-1000, squad4.getY());
            } else {
                if (squad4.getX() != getFrame().getX() + 8)
                    leftS4.setPosition(squad4.getX() - 65, squad4.getY() - 2);
                else leftS4.setPosition(-1000, squad4.getY());
            }

            if (((squad4.getX() == squad1.getX()) && (squad4.getY() + 63 == squad1.getY()))
                    || ((squad4.getX() == squad2.getX()) && (squad4.getY() + 63 == squad2.getY()))
                    || ((squad4.getX() == squad3.getX()) && (squad4.getY() + 63 == squad3.getY()))) {
                upS4.setPosition(-1000, squad4.getY() + 62);
            } else {
                if (squad4.getY() != 487)
                    upS4.setPosition(squad4.getX() - 3, squad4.getY() + 62);
                else upS4.setPosition(-1000, squad4.getY() + 62);
            }

            if (((squad4.getX() == squad1.getX()) && (squad4.getY() == squad1.getY() + 63))
                    || ((squad4.getX() == squad2.getX()) && (squad4.getY() == squad2.getY() + 63))
                    || ((squad4.getX() == squad3.getX()) && (squad4.getY() == squad3.getY() + 63))) {
                downS4.setPosition(-1000, squad4.getY() - 65);
            } else {
                if (squad4.getY() != getFrame().getY() + 7 )
                    downS4.setPosition(squad4.getX() - 3, squad4.getY() - 65);
                else downS4.setPosition(-1000, squad4.getY() - 65);
            }
        } else {
            rightS4.setPosition(-1000, squad4.getY());
            leftS4.setPosition(-1000, squad4.getY());
            upS4.setPosition(-1000, squad4.getY() + 62);
            downS4.setPosition(-1000, squad4.getY() - 65);
        }
    }

    private void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            activ1 = !activ1;
            activ2 = false;
            activ3 = false;
            activ4 = false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            activ2 = !activ2;
            activ1 = false;
            activ3 = false;
            activ4 = false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            activ3 = !activ3;
            activ1 = false;
            activ2 = false;
            activ4 = false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            activ4 = !activ4;
            activ1 = false;
            activ2 = false;
            activ3 = false;
        }
    }

    private void controller() {
        rightS1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad1.setX(squad1.getX() + 63);
            }
        });
        leftS1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad1.setX(squad1.getX() - 63);
            }
        });
        upS1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad1.setY(squad1.getY() + 63);
            }
        });
        downS1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad1.setY(squad1.getY() - 63);
            }
        });

        rightS2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad2.setX(squad2.getX() + 63);
            }
        });
        leftS2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad2.setX(squad2.getX() - 63);
            }
        });
        upS2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad2.setY(squad2.getY() + 63);
            }
        });
        downS2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad2.setY(squad2.getY() - 63);
            }
        });

        rightS3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad3.setX(squad3.getX() + 63);
            }
        });
        leftS3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad3.setX(squad3.getX() - 63);
            }
        });
        upS3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad3.setY(squad3.getY() + 63);
            }
        });
        downS3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad3.setY(squad3.getY() - 63);
            }
        });

        rightS4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad4.setX(squad4.getX() + 63);
            }
        });
        leftS4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad4.setX(squad4.getX() - 63);
            }
        });
        upS4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad4.setY(squad4.getY() + 63);
            }
        });
        downS4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                squad4.setY(squad4.getY() - 63);
            }
        });
    }

    private void initMove(Stage stage) {
        Skin skin = new Skin(new TextureAtlas("image/atlas/btn_move.atlas"));
        Button.ButtonStyle btnStyle = new Button.ButtonStyle();
        btnStyle.down = skin.getDrawable("btn");
        btnStyle.up = skin.getDrawable("btn");
        rightS1 = new Button(btnStyle);
        leftS1 = new Button(btnStyle);
        upS1 = new Button(btnStyle);
        downS1 = new Button(btnStyle);
        rightS2 = new Button(btnStyle);
        leftS2 = new Button(btnStyle);
        upS2 = new Button(btnStyle);
        downS2 = new Button(btnStyle);
        rightS3 = new Button(btnStyle);
        leftS3 = new Button(btnStyle);
        upS3 = new Button(btnStyle);
        downS3 = new Button(btnStyle);
        rightS4 = new Button(btnStyle);
        leftS4 = new Button(btnStyle);
        upS4 = new Button(btnStyle);
        downS4 = new Button(btnStyle);
        stage.addActor(rightS1);
        stage.addActor(leftS1);
        stage.addActor(upS1);
        stage.addActor(downS1);
        stage.addActor(rightS2);
        stage.addActor(leftS2);
        stage.addActor(upS2);
        stage.addActor(downS2);
        stage.addActor(rightS3);
        stage.addActor(leftS3);
        stage.addActor(upS3);
        stage.addActor(downS3);
        stage.addActor(rightS4);
        stage.addActor(leftS4);
        stage.addActor(upS4);
        stage.addActor(downS4);
        controller();
    }

    @Override
    public void dispose() {
        super.dispose();
        grid.dispose();
        iconSquad1.dispose();
        iconSquad2.dispose();
        iconSquad3.dispose();
        iconSquad4.dispose();
    }
}
