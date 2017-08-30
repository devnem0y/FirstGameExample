package com.devnem0y.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.handler.input.dialog.battle.Battle;
import com.devnem0y.handler.input.dialog.building.Castle;
import com.devnem0y.handler.input.dialog.building.Fort;
import com.devnem0y.handler.input.dialog.building.Mill;
import com.devnem0y.handler.input.dialog.building.Port;
import com.devnem0y.handler.input.dialog.hud.Army;
import com.devnem0y.handler.input.dialog.hud.Book;
import com.devnem0y.handler.input.dialog.messages.ArmyIsCrushed;
import com.devnem0y.handler.input.dialog.messages.AttackRepulsed;
import com.devnem0y.handler.input.dialog.messages.DeathCivilians;
import com.devnem0y.handler.input.dialog.messages.Info;
import com.devnem0y.handler.input.dialog.messages.InfoMouse;
import com.devnem0y.handler.input.dialog.messages.Victory;
import com.devnem0y.handler.input.dialog.messages.Reminder;

public class DialogManager {

    // HUD
    public Book book;
    public Army army;

    // Building
    public Castle castle;
    public Fort fort;
    public Mill mill;
    public Port port;

    // Messages
    public Victory victory;
    public ArmyIsCrushed armyIsCrushed;
    public AttackRepulsed attackRepulsed;
    public DeathCivilians deathCivilians;
    public Reminder reminder;
    public Info info;
    public InfoMouse infoMouse;

    // Battle
    public Battle battle;

    public DialogManager(Stage stage) {
        book = new Book(480, 520, "КНИГА", null, Align.center, true, stage);
        army = new Army(480, 300, "АРМИЯ", null, Align.center, true, stage);
        castle = new Castle(480, 480, "ЗАМОК", null, Align.center, true, stage);
        fort = new Fort(480, 450, "ФОРТ", null, Align.center, true, stage);
        mill = new Mill(480, 400, "МЕЛЬНИЦА", null, Align.center, true, stage);
        port = new Port(480, 320, "ПОРТ", null, Align.center, true, stage);
        victory = new Victory(480, 260, "VICTORY!", null, Align.center, true, stage);
        armyIsCrushed = new ArmyIsCrushed(480, 260, "YOUR ARMY IS CRUSHED...", null, Align.center, true, stage);
        attackRepulsed = new AttackRepulsed(480, 120, " ", null, Align.center, true, stage);
        deathCivilians = new DeathCivilians(500, 70, null, null, Align.center, false, stage);
        reminder = new Reminder(500, 70, null, null, Align.center, false, stage);
        info = new Info(200, 200, null, null, Align.left, false, stage);
        infoMouse = new InfoMouse(90, 60, null, null, Align.left, false, stage);
        battle = new Battle(642, 390, null, null, Align.center, false, stage);
    }

    public void render(SpriteBatch batch) {
        book.draw(batch);
        army.draw(batch);
        castle.draw(batch);
        fort.draw(batch);
        mill.draw(batch);
        port.draw(batch);
        victory.draw(batch);
        armyIsCrushed.draw(batch);
        attackRepulsed.draw(batch);
        deathCivilians.draw(batch);
        reminder.draw(batch);
        info.draw(batch);
        infoMouse.draw(batch);
        battle.draw(batch);
    }

    public void dispose() {
        book.dispose();
        army.dispose();
        castle.dispose();
        fort.dispose();
        mill.dispose();
        port.dispose();
        victory.dispose();
        armyIsCrushed.dispose();
        attackRepulsed.dispose();
        deathCivilians.dispose();
        reminder.dispose();
        info.dispose();
        infoMouse.dispose();
        battle.dispose();
    }
}
