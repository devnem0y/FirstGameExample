package com.devnem0y.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.devnem0y.Application;
import com.devnem0y.managers.GameScreenManager;

import static com.devnem0y.screens.GameScreen.buttonsHud;
import static com.devnem0y.screens.GameScreen.dialogManager;

public class GameLogic {

    private float food; // еда
    private float wood; // древесина
    private float rock; // камень
    private float iron; // железо
    private int day;
    private int peopleMax, peopleIdle, peopleJob;
    private int peasant, feller, mason, miner;
    private int army, armyAttack, armyDefends;
    private boolean attack1Actively, attack2Actively, attack3Actively, attack4Actively;
    private boolean attack1Improved, attack2Improved, attack3Improved, attack4Improved;
    private boolean def1Actively, def2Actively, def3Actively, def4Actively;
    private boolean def1Improved, def2Improved, def3Improved, def4Improved;

    private boolean anvilActively, axeActively, carrotActively, foodActively, minelActively;
    private boolean anvilImproved, axeImproved, carrotImproved, foodImproved, minelImproved;

    private boolean anchorActively, goldActively;
    private boolean anchorImproved, goldImproved;

    private int enemyHP, enemyAttack, enemyDefends;
    private int rndKillPeopleArms, rndKillPeasant, rndKillFeller, rndKillMason, rndKillMiner, rndKillPeople;

    private boolean millActively, fortActively, bighouseActively, portActively;
    private boolean millBuild, fortBuild, bighouseBuild, portBuild;
    private boolean bookOpen, armyOpen, castleOpen, millOpen, fortOpen, houseOpen, portOpen;
    private int currentDay = 0;
    private float timerday = 0;

    private TextureAtlas objMapAtlas;
    private boolean outlineC, outlineF, outlineOct, outlineL, outlineM, outlineSp, outlineS, outlinePr, outlineScor, outlineAs, outlineSig;

    public PlayerArmy playerArmy;
    public EnemyArmy enemyArmy;
    private boolean battleStart;
    private int currentWeek = 0;
    private float speedGame = 5;

    public void createGame() {
        day = 0;
        food = 9700;
        wood = 9700;
        rock = 9700;
        iron = 9700;
        peopleMax = 50;
        peopleJob = 0;
        peopleIdle = 0;

        peasant = 0;
        feller = 0;
        mason = 0;
        miner = 0;

        army = 0;
        armyAttack = 0;
        armyDefends = 0;

        objMapAtlas = new TextureAtlas("image/objMap.atlas");
        bookOpen = true;
        armyOpen = true;
        castleOpen = true;
        fortOpen = true;
        millOpen = true;
        houseOpen = true;
        portOpen = true;

        playerArmy = new PlayerArmy(127, 340, 89);
        playerArmy.update(127, 340, 89);
        enemyArmy = new EnemyArmy(140, 299, 112);
    }

    public void update(Application app, float dt) {
        if (getPeopleMax() == 0) {
            timerday = 0;
            app.gsm.setScreen(GameScreenManager.STATE.GAME_OVER);
        } else if (timerday >= buttonsHud.getSliderGameSpeed().getValue()) {
            stepNextDay();
            timerday = 0;
        }
        timerday += dt;

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (speedGame > 0) speedGame--;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            if (speedGame <= 15) speedGame++;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            dialogManager.battle.show();
        }

        //exampleBattle();

        if (getArmy() != 0) initFort();
        initBuilding();
        if (portBuild) initPort();
        if (millBuild) initMill();


    }

    public void exampleBattle() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            System.out.println("Режим битвы\n\nВаш враг:\nАтака - " + enemyArmy.getAttack() + "\nЗащита - " + enemyArmy.getDefense());
            System.out.println("\nАтакавать Z");
            battleStart = true;
        }

        if (battleStart) {
            if (playerArmy.isAlive() | enemyArmy.isAlive()) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
                    int rndAttackP = MathUtils.random(playerArmy.getAttack());
                    int rndAttackE = MathUtils.random(enemyArmy.getAttack());
                    System.out.println("Вы нанесли " + rndAttackP + " ед. урона\nВраг получил " + enemyArmy.damage(rndAttackP) + " ед. урона");
                    System.out.println("Враг нанес " + rndAttackE + " ед. урона\nВы получили " + playerArmy.damage(rndAttackE) + " ед. урона");
                    System.out.println("Player alive: " + playerArmy.isAlive());
                    System.out.println("Enemy alive: " + enemyArmy.isAlive());
                }
            } else battleStart = false;
        }
    }

    public void renderMap(SpriteBatch batch) {
        if (outlineC) batch.draw(objMapAtlas.findRegion("castle-outline"), 100, 368);
        else batch.draw(objMapAtlas.findRegion("castle"), 100, 368);
        if (outlinePr) batch.draw(objMapAtlas.findRegion("pointer-road-outline"), 612, 407);
        else batch.draw(objMapAtlas.findRegion("pointer-road"), 612, 407);
        if (outlineSig) batch.draw(objMapAtlas.findRegion("signpost-outline"), 1032, 367);
        else batch.draw(objMapAtlas.findRegion("signpost"), 1032, 367);
        if (outlineAs) batch.draw(objMapAtlas.findRegion("animal-skull-outline"), 1054, 612);
        else batch.draw(objMapAtlas.findRegion("animal-skull"), 1054, 612);
        if (outlineSp) batch.draw(objMapAtlas.findRegion("ship-pirates-outline"), 388, 5);
        else batch.draw(objMapAtlas.findRegion("ship-pirates"), 388, 5);
        if (outlineOct) batch.draw(objMapAtlas.findRegion("octopus-outline"), 164, 637);
        else batch.draw(objMapAtlas.findRegion("octopus"), 164, 637);
        if (outlineScor) batch.draw(objMapAtlas.findRegion("scorpion-outline"), 995, 150);
        else batch.draw(objMapAtlas.findRegion("scorpion"), 995, 150);
        if (outlineS) batch.draw(objMapAtlas.findRegion("spider-outline"), 608, 505);
        else batch.draw(objMapAtlas.findRegion("spider"), 608, 505);
        if (fortBuild) {
            if (outlineF) batch.draw(objMapAtlas.findRegion("fort-outline"), 317, 230);
            else batch.draw(objMapAtlas.findRegion("fort"), 317, 230);
        }
        if (millBuild) {
            if (outlineM) batch.draw(objMapAtlas.findRegion("mill-outline"), 176, 247);
            else batch.draw(objMapAtlas.findRegion("mill"), 176, 247);
        }
        if (bighouseBuild) batch.draw(objMapAtlas.findRegion("house"), 310, 380);
        if (portBuild) {
            if (outlineL) batch.draw(objMapAtlas.findRegion("lighthouse-outline"), 75, 157);
            else batch.draw(objMapAtlas.findRegion("lighthouse"), 75, 157);
        }
        if (anchorImproved) batch.draw(objMapAtlas.findRegion("ship"), 20, 37);

    }

    private void initFort() {
        if (!attack1Improved) attack1Actively = iron >= 800 && wood >= 350;
        if (!attack2Improved) attack2Actively = (attack1Improved | def1Improved) && iron >= 1700 && wood >= 900;
        if (!attack3Improved) attack3Actively = def1Improved && iron >= 650 && wood >= 1900;
        if (!attack4Improved) attack4Actively = def2Improved && def3Improved && iron >= 3000 && wood >= 600 && rock >= 700;

        if (!def1Improved) def1Actively = food >= 500 && wood >= 200;
        if (!def2Improved) def2Actively = attack1Improved && food >= 500 && wood >= 1450 && iron >= 100;
        if (!def3Improved) def3Actively = attack2Improved && wood >= 370 && iron >= 4500 && rock >= 250;
        if (!def4Improved) def4Actively = def3Improved && attack3Improved && food >= 700 && wood >= 450 && iron >= 3200;
    }

    private void initBuilding() {
        if (!millBuild) millActively = wood >= 300 && rock >= 520;
        if (!fortBuild) fortActively = food >= 120 && wood >= 270 && rock >= 800 && iron >= 200;
        if (!bighouseBuild) bighouseActively = food >= 50 && wood >= 350 && rock >= 100;
        if (!portBuild) portActively = food >= 310 && wood >= 2600 && rock >= 950 && iron >= 470;
    }

    private void initPort() {
        if (!anchorImproved) anchorActively = getArmy() != 0 && food >= 1100 && wood >= 4700 && iron >= 360;
        if (!goldImproved) goldActively = food >= 1850 && wood >= 1200 && iron >= 270;
    }

    private void initMill() {
        if (!anvilImproved) anvilActively = food >= 900 && wood >= 3000 && rock >= 4800 && iron >= 360;
        if (!axeImproved) axeActively = food >= 400 && wood >= 350 && rock >= 300 && iron >= 1800;
        if (!carrotImproved) carrotActively = food >= 70 && wood >= 2000 && iron >= 340;
        if (!foodImproved) foodActively = food >= 2370 && wood >= 1700 && rock >= 1250 && iron >= 600;
        if (!minelImproved) minelActively = wood >= 800 && iron >= 270;
    }

    public void activFood() {
        setFood(getFood() - 2370);
        setWood(getWood() - 1700);
        setRock(getRock() - 1250);
        setIron(getIron() - 600);
        foodImproved = true;
        dialogManager.reminder.show("CATTLE BREEDING unlocked", 1.5f);
    }

    public void activMine() {
        setWood(getWood() - 800);
        setIron(getIron() - 270);
        minelImproved = true;
        dialogManager.reminder.show("BARROW unlocked", 1.5f);
    }

    public void activAnvil() {
        setFood(getFood() - 900);
        setWood(getWood() - 4700);
        setRock(getRock() - 4800);
        setIron(getIron() - 360);
        anvilImproved = true;
        dialogManager.reminder.show("FORGE unlocked", 1.5f);
    }

    public void activAxe() {
        setFood(getFood() - 400);
        setWood(getWood() - 350);
        setRock(getRock() - 300);
        setIron(getIron() - 1800);
        axeImproved = true;
        dialogManager.reminder.show("AX WITH TWO BLADES unlocked", 1.5f);
    }

    public void activCarrot() {
        setFood(getFood() - 70);
        setWood(getWood() - 2000);
        setIron(getIron() - 340);
        carrotImproved = true;
        dialogManager.reminder.show("SIZE OF FIELDS unlocked", 1.5f);
    }

    public void activAnchor() {
        setArmyAttack(getArmyAttack() + 92);
        setArmyDefends(getArmyDefends() + 80);
        setFood(getFood() - 1100);
        setIron(getIron() - 360);
        setWood(getWood() - 4700);
        anchorImproved = true;
        dialogManager.reminder.show("FLEET unlocked", 1.5f);
    }

    public void activGold() {
        setFood(getFood() - 1850);
        setIron(getIron() - 270);
        setWood(getWood() - 1200);
        goldImproved = true;
        dialogManager.reminder.show("TRADE unlocked", 1.5f);
    }

    public void activAttack1() {
        if (getArmy() != 0) {
            setArmyAttack(getArmyAttack() + 4 * getArmy());
            setIron(getIron() - 800);
            setWood(getWood() - 350);
            attack1Improved = true;
        }
        dialogManager.reminder.show("LIGHT AX unlocked", 1.5f);
    }

    public void activAttack2() {
        if (getArmy() != 0) {
            setArmyAttack(getArmyAttack() + 6 * getArmy());
            setIron(getIron() - 1700);
            setWood(getWood() - 900);
            attack2Improved = true;
        }
        dialogManager.reminder.show("WEAPON IN TWO HANDS unlocked", 1.5f);
    }

    public void activAttack3() {
        if (getArmy() != 0) {
            setArmyAttack(getArmyAttack() + 7 * getArmy());
            setIron(getIron() - 650);
            setWood(getWood() - 1900);
            attack3Improved = true;
        }
        dialogManager.reminder.show("ARCHER unlocked", 1.5f);
    }

    public void activAttack4() {
        if (getArmy() != 0) {
            setArmyAttack(getArmyAttack() + 8 * getArmy());
            setIron(getIron() - 3000);
            setWood(getWood() - 600);
            setRock(getRock() - 700);
            attack4Improved = true;
        }
        dialogManager.reminder.show("BERSERK unlocked", 1.5f);
    }

    public void activDef1() {
        if (getArmy() != 0) {
            setArmyDefends(getArmyDefends() + 3 * getArmy());
            setFood(getFood() - 500);
            setWood(getWood() - 200);
            def1Improved = true;
        }
        dialogManager.reminder.show("SIMPLE ARMOR unlocked", 1.5f);
    }

    public void activDef2() {
        if (getArmy() != 0) {
            setArmyDefends(getArmyDefends() + 5 * getArmy());
            setFood(getFood() - 500);
            setWood(getWood() - 1450);
            setIron(getIron() - 100);
            def2Improved = true;
        }
        dialogManager.reminder.show("SHIELD unlocked", 1.5f);
    }

    public void activDef3() {
        if (getArmy() != 0) {
            setArmyDefends(getArmyDefends() + 6 * getArmy());
            setWood(getWood() - 370);
            setIron(getIron() - 4500);
            setRock(getRock() - 250);
            def3Improved = true;
        }
        dialogManager.reminder.show("PALADIN'S ARMOR unlocked", 1.5f);
    }

    public void activDef4() {
        if (getArmy() != 0) {
            setArmyDefends(getArmyDefends() + 7 * getArmy());
            setFood(getFood() - 700);
            setWood(getWood() - 450);
            setIron(getIron() - 3200);
            def4Improved = true;
        }
        dialogManager.reminder.show("BERSERK ARMOR unlocked", 1.5f);
    }

    public void buildMill() {
        setWood(getWood() - 300);
        setRock(getRock() - 520);
        millBuild = true;
        dialogManager.reminder.show("Mill building", 1.5f);
    }

    public void buildFort() {
        setFood(getFood() - 120);
        setWood(getWood() - 270);
        setRock(getRock() - 800);
        setIron(getIron() - 200);
        fortBuild = true;
        dialogManager.reminder.show("Fort building", 1.5f);
    }

    public void buildHouse() {
        setFood(getFood() - 50);
        setWood(getWood() - 350);
        setRock(getRock() - 100);
        bighouseBuild = true;
        dialogManager.reminder.show("Big house building", 1.5f);
    }

    public void buildPort() {
        setFood(getFood() - 310);
        setWood(getWood() - 2600);
        setRock(getRock() - 950);
        setIron(getIron() - 470);
        portBuild = true;
        dialogManager.reminder.show("Port building", 1.5f);
    }

    public void remArmy() {
        if (getArmy() > 0) {
            setArmy(getArmy() - 1);
            setArmyAttack(getArmyAttack() - 3);
            if (attack1Actively) setArmyAttack(getArmyAttack() - 4);
            if (attack2Actively) setArmyAttack(getArmyAttack() - 6);
            if (attack3Actively) setArmyAttack(getArmyAttack() - 7);
            if (attack4Actively) setArmyAttack(getArmyAttack() - 8);
            setArmyDefends(getArmyDefends() - 1);
            if (def1Actively) setArmyDefends(getArmyDefends() - 3);
            if (def2Actively) setArmyDefends(getArmyDefends() - 5);
            if (def3Actively) setArmyDefends(getArmyDefends() - 6);
            if (def4Actively) setArmyDefends(getArmyDefends() - 7);
            setPeopleJob(getPeopleJob() - 1);
        }
    }

    public void addArmy() {
        if (peopleIdle > 0) {
            setArmy(getArmy() + 1);
            setArmyAttack(getArmyAttack() + 3);
            if (attack1Actively) setArmyAttack(getArmyAttack() + 4);
            if (attack2Actively) setArmyAttack(getArmyAttack() + 6);
            if (attack3Actively) setArmyAttack(getArmyAttack() + 7);
            if (attack4Actively) setArmyAttack(getArmyAttack() + 8);
            setArmyDefends(getArmyDefends() + 1);
            if (def1Actively) setArmyDefends(getArmyDefends() + 3);
            if (def2Actively) setArmyDefends(getArmyDefends() + 5);
            if (def3Actively) setArmyDefends(getArmyDefends() + 6);
            if (def4Actively) setArmyDefends(getArmyDefends() + 7);
            setPeopleJob(getPeopleJob() + 1);
        }
    }

    private void battle() {
        createEnemy();
        if (getPeopleMax() > 0) {
            if (getArmy() > 0) {
                int rndPAttack = MathUtils.random(getArmyAttack());
                int rndPDefends = MathUtils.random(getArmyDefends());
                int rndEAttack =  MathUtils.random(getEnemyAttack());
                int rndEDefends = MathUtils.random(getEnemyDefends());
                if (rndPAttack > rndEAttack) {
                    if (rndPDefends > rndEDefends) {
                        dialogManager.victory.show();
                    } else {
                        if (getArmy() > 0) {
                            rndKillPeopleArms = MathUtils.random(getArmy());
                            killArmy(rndKillPeopleArms);
                            if (getRndKillPeopleArms() == 1) dialogManager.attackRepulsed.show("Attack of the enemy repulsed.\nYou lost 1 war");
                            else dialogManager.attackRepulsed.show("Attack of the enemy repulsed.\nYou lost " + getRndKillPeopleArms() + " war");
                            if (getArmy() < 0) setArmy(0);
                        }
                    }
                } else {
                    if (rndPDefends < rndEDefends) {
                        killArmy(getArmy());
                        dialogManager.armyIsCrushed.show();
                    } else {
                        if (getArmy() > 0) {
                            rndKillPeopleArms = MathUtils.random(getArmy());
                            killArmy(rndKillPeopleArms);
                            if (getRndKillPeopleArms() == 1) dialogManager.attackRepulsed.show("Attack of the enemy repulsed.\nYou lost 1 war");
                            else dialogManager.attackRepulsed.show("Attack of the enemy repulsed.\nYou lost " + getRndKillPeopleArms() + " war");
                            if (getArmy() < 0) setArmy(0);
                        }
                    }
                }
            } else killPeople();
        }
    }

    private void killPeople() {
        if (getPeopleJob() > 0) {
            while (true) {
                int rnd = MathUtils.random(1, 5);
                if (rnd == 1 && getPeasant() > 0) {
                    rndKillPeasant = MathUtils.random(getPeasant());
                    setPeasant(getPeasant() - rndKillPeasant);
                    setPeopleMax(getPeopleMax() - rndKillPeasant);
                    setPeopleJob(getPeopleJob() - rndKillPeasant);
                    dialogManager.deathCivilians.show("Not enough resources.\nYou lost " + getRndKillPeasant() + " civilians.", 2f);
                    break;
                } else if (rnd == 2 && getFeller() > 0) {
                    rndKillFeller = MathUtils.random(getFeller());
                    setFeller(getFeller() - rndKillFeller);
                    setPeopleMax(getPeopleMax() - rndKillFeller);
                    setPeopleJob(getPeopleJob() - rndKillFeller);
                    dialogManager.deathCivilians.show("Not enough resources.\nYou lost " + getRndKillFeller() + " civilians.", 2f);
                    break;
                } else if (rnd == 3 && getMason() > 0) {
                    rndKillMason = MathUtils.random(getMason());
                    setMason(getMason() - rndKillMason);
                    setPeopleMax(getPeopleMax() - rndKillMason);
                    setPeopleJob(getPeopleJob() - rndKillMason);
                    dialogManager.deathCivilians.show("Not enough resources.\nYou lost " + getRndKillMason() + " civilians.", 2f);
                    break;
                } else if (rnd == 4 && getMiner() > 0) {
                    rndKillMiner = MathUtils.random(getMiner());
                    setMiner(getMiner() - rndKillMiner);
                    setPeopleMax(getPeopleMax() - rndKillMiner);
                    setPeopleJob(getPeopleJob() - rndKillMiner);
                    dialogManager.deathCivilians.show("Not enough resources.\nYou lost " + getRndKillMiner() + " civilians.", 2f);
                    break;
                } else if (rnd == 5 && getPeopleIdle() > 0) {
                    rndKillPeople = MathUtils.random(getPeopleMax());
                    setPeopleMax(getPeopleMax() - rndKillPeople);
                    dialogManager.deathCivilians.show("Not enough resources.\nYou lost " + getRndKillPeople() + " civilians.", 2f);
                    break;
                }
            }
        } else {
            rndKillPeople = MathUtils.random(getPeopleMax());
            setPeopleMax(getPeopleMax() - rndKillPeople);
            dialogManager.deathCivilians.show("Not enough resources.\nYou lost " + getRndKillPeople() + " civilians.", 2f);
        }
    }

    public void remMiner() {
        if (getMiner() > 0) {
            setMiner(getMiner() - 1);
            setPeopleJob(getPeopleJob() - 1);
        }
    }

    public void addMiner() {
        if (peopleIdle > 0) {
            setMiner(getMiner() + 1);
            setPeopleJob(getPeopleJob() + 1);
        }
    }

    public void remMason() {
        if (getMason() > 0) {
            setMason(getMason() - 1);
            setPeopleJob(getPeopleJob() - 1);
        }
    }

    public void addMason() {
        if (peopleIdle > 0) {
            setMason(getMason() + 1);
            setPeopleJob(getPeopleJob() + 1);
        }
    }

    public void remFeller() {
        if (getFeller() > 0) {
            setFeller(getFeller() - 1);
            setPeopleJob(getPeopleJob() - 1);
        }
    }

    public void addFeller() {
        if (peopleIdle > 0) {
            setFeller(getFeller() + 1);
            setPeopleJob(getPeopleJob() + 1);
        }
    }

    public void remPeasant() {
        if (getPeasant() > 0) {
            setPeasant(getPeasant() - 1);
            setPeopleJob(getPeopleJob() - 1);
        }
    }

    public void addPeasant() {
        if (peopleIdle > 0) {
            setPeasant(getPeasant() + 1);
            setPeopleJob(getPeopleJob() + 1);
        }
    }

    private void killArmy(int val) {
        setPeopleMax(getPeopleMax() - val);
        setPeopleJob(getPeopleJob() - val);
        setArmyAttack(getArmyAttack() - 3 * val);
        if (attack1Actively) setArmyAttack(getArmyAttack() - 4 * val);
        if (attack2Actively) setArmyAttack(getArmyAttack() - 6 * val);
        if (attack3Actively) setArmyAttack(getArmyAttack() - 7 * val);
        if (attack4Actively) setArmyAttack(getArmyAttack() - 8 * val);
        setArmyDefends(getArmyDefends() - val);
        if (def1Actively) setArmyDefends(getArmyDefends() - 3 * val);
        if (def2Actively) setArmyDefends(getArmyDefends() - 5 * val);
        if (def3Actively) setArmyDefends(getArmyDefends() - 6 * val);
        if (def4Actively) setArmyDefends(getArmyDefends() - 7 * val);
        setArmy(getArmy() - val);
    }

    public void stepNextDay() { // Затраты на одного человека за 1 день и добыча одного человека за 1 день
        if (getPeopleMax() != 0) {
            day++;
            // затраты
            setFood(getFood() - 2f * getPeopleMax());
            setWood(getWood() - 1f * getPeopleMax());
            if (getArmy() > 0) {
                setIron(getIron() - 1f * getArmy());
            }
            // добыча
            if (minelImproved) {
                setFood(getFood() + 10f * getPeasant());
                setWood(getWood() + 10f * getFeller());
                setRock(getRock() + 10f * getMason());
                setIron(getIron() + 10f * getMiner());
            }
            if (carrotImproved) setFood(getFood() + 5f * getPeasant());
            if (foodImproved) setFood(getFood() + 7f * getPeasant());
            if (axeImproved) setWood(getWood() + 15f * getFeller());
            if (minelImproved) setIron(getIron() + 30f * getMiner());
            setFood(getFood() + 5f * getPeasant());
            setWood(getWood() + 5f * getFeller());
            setRock(getRock() + 2f * getMason());
            setIron(getIron() + 3f * getMiner());

            if (food < 0 || wood < 0) killPeople();
        }
        //if (MathUtils.random(2) == 1) battle();

        // Прибавляем людей
        if (bighouseBuild && currentDay == 2) {
            setPeopleMax(getPeopleMax() + 3);
            currentDay = 0;
        } else if (currentDay == 2) {
            setPeopleMax(getPeopleMax() + 1);
            currentDay = 0;
        }
        currentDay++;
        if (goldImproved) {
            if (currentWeek == 6) {
                setFood(getFood() + 1500);
                setIron(getIron() + 1500);
                setRock(getRock() + 1500);
                setWood(getWood() + 1500);
                currentWeek = 0;
            }
            currentWeek++;
        }
    }

    private void createEnemy() {
        int rnd = MathUtils.random(1, 3);
        if (rnd == 1) {
            enemyHP = 3;
            enemyAttack = 6;
            enemyDefends = 3;
        } else if (rnd == 2) {
            enemyHP = 5;
            enemyAttack = 10;
            enemyDefends = 5;
        } else if (rnd == 3) {
            enemyHP = 40;
            enemyAttack = 120;
            enemyDefends = 40;
        }
    }

    public float getFood() {
        return food;
    }

    public void setFood(float food) {
        this.food = food;
    }

    public float getWood() {
        return wood;
    }

    public void setWood(float wood) {
        this.wood = wood;
    }

    public float getRock() {
        return rock;
    }

    public void setRock(float rock) {
        this.rock = rock;
    }

    public float getIron() {
        return iron;
    }

    public void setIron(float iron) {
        this.iron = iron;
    }

    public int getPeopleMax() {
        return peopleMax;
    }

    public void setPeopleMax(int peopleMax) {
        this.peopleMax = peopleMax;
    }

    public int getPeopleIdle() {
        if (peopleIdle < 0) return peopleIdle = 0;
        else return peopleIdle = getPeopleMax() - getPeopleJob();
    }

    public int getPeopleJob() {
        return peopleJob;
    }

    public void setPeopleJob(int peopleJob) {
        this.peopleJob = peopleJob;
    }

    public int getPeasant() {
        return peasant;
    }

    public void setPeasant(int peasant) {
        this.peasant = peasant;
    }

    public int getFeller() {
        return feller;
    }

    public void setFeller(int feller) {
        this.feller = feller;
    }

    public int getMason() {
        return mason;
    }

    public void setMason(int mason) {
        this.mason = mason;
    }

    public int getMiner() {
        return miner;
    }

    public void setMiner(int miner) {
        this.miner = miner;
    }

    public int getArmy() {
        return army;
    }

    public void setArmy(int army) {
        this.army = army;
    }

    public int getArmyAttack() {
        return armyAttack;
    }

    public void setArmyAttack(int armyAttack) {
        this.armyAttack = armyAttack;
    }

    public int getArmyDefends() {
        return armyDefends;
    }

    public void setArmyDefends(int armyDefends) {
        this.armyDefends = armyDefends;
    }

    public boolean isAttack1Actively() {
        return attack1Actively;
    }

    public boolean isAttack2Actively() {
        return attack2Actively;
    }

    public boolean isAttack3Actively() {
        return attack3Actively;
    }

    public boolean isAttack4Actively() {
        return attack4Actively;
    }

    public boolean isAttack1Improved() {
        return attack1Improved;
    }

    public boolean isAttack2Improved() {
        return attack2Improved;
    }

    public boolean isAttack3Improved() {
        return attack3Improved;
    }

    public boolean isAttack4Improved() {
        return attack4Improved;
    }

    public boolean isDef1Actively() {
        return def1Actively;
    }

    public boolean isDef2Actively() {
        return def2Actively;
    }

    public boolean isDef3Actively() {
        return def3Actively;
    }

    public boolean isDef4Actively() {
        return def4Actively;
    }

    public boolean isDef1Improved() {
        return def1Improved;
    }

    public boolean isDef2Improved() {
        return def2Improved;
    }

    public boolean isDef3Improved() {
        return def3Improved;
    }

    public boolean isDef4Improved() {
        return def4Improved;
    }

    public int getEnemyHP() {
        return enemyHP;
    }

    public void setEnemyHP(int enemyHP) {
        this.enemyHP = enemyHP;
    }

    public int getEnemyAttack() {
        return enemyAttack;
    }

    public void setEnemyAttack(int enemyAttack) {
        this.enemyAttack = enemyAttack;
    }

    public int getEnemyDefends() {
        return enemyDefends;
    }

    public void setEnemyDefends(int enemyDefends) {
        this.enemyDefends = enemyDefends;
    }

    public int getDay() {
        return day;
    }

    public int getRndKillPeopleArms() {
        return rndKillPeopleArms;
    }

    public int getRndKillPeasant() {
        return rndKillPeasant;
    }

    public int getRndKillFeller() {
        return rndKillFeller;
    }

    public int getRndKillMason() {
        return rndKillMason;
    }

    public int getRndKillMiner() {
        return rndKillMiner;
    }

    public int getRndKillPeople() {
        return rndKillPeople;
    }

    public boolean isMillActively() {
        return millActively;
    }

    public void setMillActively(boolean millActively) {
        this.millActively = millActively;
    }

    public boolean isFortActively() {
        return fortActively;
    }

    public void setFortActively(boolean fortActively) {
        this.fortActively = fortActively;
    }

    public boolean isBighouseActively() {
        return bighouseActively;
    }

    public void setBighouseActively(boolean bighouseActively) {
        this.bighouseActively = bighouseActively;
    }

    public boolean isPortActively() {
        return portActively;
    }

    public void setPortActively(boolean portActively) {
        this.portActively = portActively;
    }

    public boolean isMillBuild() {
        return millBuild;
    }

    public void setMillBuild(boolean millBuild) {
        this.millBuild = millBuild;
    }

    public boolean isFortBuild() {
        return fortBuild;
    }

    public void setFortBuild(boolean fortBuild) {
        this.fortBuild = fortBuild;
    }

    public boolean isBighouseBuild() {
        return bighouseBuild;
    }

    public void setBighouseBuild(boolean bighouseBuild) {
        this.bighouseBuild = bighouseBuild;
    }

    public boolean isPortBuild() {
        return portBuild;
    }

    public void setPortBuild(boolean portBuild) {
        this.portBuild = portBuild;
    }

    public boolean isCastleOpen() {
        return castleOpen;
    }

    public boolean isMillOpen() {
        return millOpen;
    }

    public boolean isFortOpen() {
        return fortOpen;
    }

    public boolean isHouseOpen() {
        return houseOpen;
    }

    public boolean isPortOpen() {
        return portOpen;
    }

    public void setCastleOpen(boolean castleOpen) {
        this.castleOpen = castleOpen;
    }

    public void setMillOpen(boolean millOpen) {
        this.millOpen = millOpen;
    }

    public void setFortOpen(boolean fortOpen) {
        this.fortOpen = fortOpen;
    }

    public void setHouseOpen(boolean houseOpen) {
        this.houseOpen = houseOpen;
    }

    public void setPortOpen(boolean portOpen) {
        this.portOpen = portOpen;
    }

    public boolean isBookOpen() {
        return bookOpen;
    }

    public void setBookOpen(boolean bookOpen) {
        this.bookOpen = bookOpen;
    }

    public boolean isArmyOpen() {
        return armyOpen;
    }

    public void setArmyOpen(boolean armyOpen) {
        this.armyOpen = armyOpen;
    }

    public boolean isAnchorActively() {
        return anchorActively;
    }

    public boolean isGoldActively() {
        return goldActively;
    }

    public boolean isAnchorImproved() {
        return anchorImproved;
    }

    public boolean isGoldImproved() {
        return goldImproved;
    }

    public boolean isAnvilActively() {
        return anvilActively;
    }

    public boolean isAxeActively() {
        return axeActively;
    }

    public boolean isCarrotActively() {
        return carrotActively;
    }

    public boolean isFoodActively() {
        return foodActively;
    }

    public boolean isMinelActively() {
        return minelActively;
    }

    public boolean isAnvilImproved() {
        return anvilImproved;
    }

    public boolean isAxeImproved() {
        return axeImproved;
    }

    public boolean isCarrotImproved() {
        return carrotImproved;
    }

    public boolean isFoodImproved() {
        return foodImproved;
    }

    public boolean isMinelImproved() {
        return minelImproved;
    }

    public void setOutlineC(boolean outlineC) {
        this.outlineC = outlineC;
    }

    public void setOutlineF(boolean outlineF) {
        this.outlineF = outlineF;
    }

    public void setOutlineOct(boolean outlineOct) {
        this.outlineOct = outlineOct;
    }

    public void setOutlineL(boolean outlineL) {
        this.outlineL = outlineL;
    }

    public void setOutlineM(boolean outlineM) {
        this.outlineM = outlineM;
    }

    public void setOutlineSp(boolean outlineSp) {
        this.outlineSp = outlineSp;
    }

    public void setOutlineS(boolean outlineS) {
        this.outlineS = outlineS;
    }

    public void setOutlinePr(boolean outlinePr) {
        this.outlinePr = outlinePr;
    }

    public void setOutlineScor(boolean outlineScor) {
        this.outlineScor = outlineScor;
    }

    public void setOutlineAs(boolean outlineAs) {
        this.outlineAs = outlineAs;
    }

    public void setOutlineSig(boolean outlineSig) {
        this.outlineSig = outlineSig;
    }

    public void dispose() {
        objMapAtlas.dispose();
    }
}