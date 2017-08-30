package com.devnem0y.handler;

public class PlayerArmy {

    private int warriorActual;
    private int warriorMax;
    private int attack;
    private int defense;
    private boolean alive = true;

    public PlayerArmy(int warriorMax, int attack, int defense) {
        update(warriorMax, attack, defense);
    }

    public void update(int warriorMax, int attack, int defense) {
        this.warriorMax = warriorMax;
        this.attack = attack;
        this.defense = defense;
        warriorActual = warriorMax;
    }

    public int damage(int enemyDamage) {
        int playerDamage = 0;
        if (warriorActual > 0) {
            if (defense <= enemyDamage) playerDamage = enemyDamage - defense;
            else playerDamage = defense - enemyDamage;
            warriorActual -= playerDamage;
            if (warriorActual <= 0) {
                warriorActual = 0;
                alive = false;
            }
        }
        return playerDamage;
    }

    public void medic(int val) {
        if (warriorActual < warriorMax) {
            warriorActual += val;
            if (warriorActual > warriorMax) warriorActual = warriorMax;
        }
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public boolean isAlive() {
        return alive;
    }
}
