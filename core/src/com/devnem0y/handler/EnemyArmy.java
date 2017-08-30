package com.devnem0y.handler;

public class EnemyArmy {

    private int warriorActual;
    private int warriorMax;
    private int attack;
    private int defense;
    private boolean alive = true;

    public EnemyArmy(int warriorMax, int attack, int defense) {
        this.warriorMax = warriorMax;
        this.attack = attack;
        this.defense = defense;
        warriorActual = warriorMax;
    }

    public int damage(int playerDamage) {
        int enemyDamage = 0;
        if (warriorActual > 0) {
            if (defense <= playerDamage) enemyDamage = playerDamage - defense;
            else enemyDamage = defense - playerDamage;
            warriorActual -= enemyDamage;
            if (warriorActual <= 0) {
                warriorActual = 0;
                alive = false;
            }
        }
        return enemyDamage;
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
