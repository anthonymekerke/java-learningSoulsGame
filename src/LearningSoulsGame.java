import armor.DragonSlayerLeggings;
import bags.Bag;
import bags.SmallBag;
import buffs.rings.DragonSlayerRing;
import buffs.rings.RingOfDeath;
import characters.Hero;
import characters.Monster;
import characters.Character;
import characters.Zombie;
import consumables.food.Hamburger;
import exceptions.*;
import weapons.Sword;
import weapons.Weapon;

import java.util.Scanner;

public class LearningSoulsGame {

    public static final String BULLET_POINT = "\u2219";

    private Hero hero;
    private Monster monster;

    private Scanner scanner = new Scanner(System.in);

    public static void title(){
        System.out.println("###############################");
        System.out.println("### THE LEARNING SOULS GAME ###");
        System.out.println("###############################");
    }


    public void refresh(){
        hero.printStats();
        hero.printArmor();
        hero.printRings();
        hero.printConsumable();
        hero.printWeapon();
        hero.printBag();
        System.out.println();

        monster.printStats();
        monster.printWeapon();
        System.out.println();
    }

    public void fight1v1(){
        int input, monsterCount = 0;
        refresh();

        while(hero.isAlive() && monsterCount < 7){

            do{
                System.out.println("Hero's action for next move > (1) attack | (2) consume | (3) equip");
                input = scanner.nextInt();
            }while(input != 1 && input != 2 && input != 3);

            if(input == 1) {attackMove(hero, monster);}

            if(input == 2) {consumeMove();}

            if(input == 3) {equipMove();}

            if(monster.isAlive()){
                attackMove(monster, hero);

                if(!hero.isAlive()) {System.out.println("------- " + monster.getName() + " WINS !!! -------");}
            }
            else{
                System.out.println(hero.getName() + " defeated " + monster.getName());
                Bag loot = Zombie.drop(Zombie.generateZombieType());

                if(loot != null){
                    System.out.println(monster.getName() + " dropped " + loot.getClass().getSimpleName());
                    System.out.println(hero.getName() + " loots " + loot);
                    hero.setBag(loot);
                }

                System.out.println("A new monster enters the ring");
                monsterCount++;
                monster = new Zombie(Zombie.generateZombieType(), monsterCount, monsterCount, monsterCount, monsterCount);
                refresh();
            }
        }

        System.out.println("------- " + hero.getName() + " WINS !!! -------");
    }

    private void equipMove(){
        System.out.println("TODO: implements equipMove()");
    }

    private void attackMove(Character attacker, Character target){
        int atk, dmg;
        String result;

        try {
            atk = attacker.attack();
        }catch(WeaponNullException e){
            atk = 0;
            if(attacker == hero) {System.out.println("WARNING : no weapon has been equipped !\n");}
        }catch(WeaponBrokenException e){
            atk = 0;
            if(attacker == hero) {System.out.println("WARNING : " + e.getWeapon().getName() + " is broken !\n");}
        }
        catch(StaminaEmptyException e){
            atk = 0;
            if(attacker == hero) {System.out.println("ACTION HAS NO EFFECT : no more stamina !\n");}
        }

        dmg = target.getHitWith(atk);
        result = String.format("%s attacks %s with %s (ATTACK:%s | DMG:%s)\n", attacker.getName(), target.getName(), attacker.getWeapon(), atk, dmg);
        System.out.println(result);
        refresh();
    }

    public void consumeMove(){
        try {
            hero.consume();
        }catch(ConsumeNullException e){
            System.out.println("IMPOSSIBLE ACTION : no consumable has been equipped !\n");
        }catch(ConsumeEmptyException e){
            System.out.println("ACTION HAS NO EFFECT : " + e.getConsumable().getName() + " is empty !\n");
        }catch(ConsumeRepairNullWeapon e){
            System.out.println("IMPOSSIBLE ACTION : no weapon has been equipped !\n");
        }
        refresh();
    }

    public void init(){
        hero = new Hero("Gregooninator");
        monster = new Zombie(Zombie.generateZombieType(), 1, 1, 1, 1);

        Weapon wp_hero = new Sword();

        hero.setWeapon(wp_hero);
    }

    public void testException(){
        DragonSlayerLeggings dsl = new DragonSlayerLeggings();
        RingOfDeath rod = new RingOfDeath();
        DragonSlayerRing dsr = new DragonSlayerRing();
        SmallBag bag = new SmallBag();
        Hamburger burger = new Hamburger();

        hero.setBag(bag);
        hero.setArmorItem(dsl, 1);
        hero.setRing(rod, 1);
        hero.setRing(dsr, 2);
        hero.setConsumable(burger);

        //hero.setWeapon(null);
        //Weapon shovel = new Weapon("pelle casse", 0, 100, 2, 0);
        //hero.setWeapon(shovel);
        //hero.setStamina(0);
        //hero.setConsumable(null);
        //Consumable cons = new Consumable("chocolate package",0, "stamina");
        //hero.setConsumable(cons);
        //Consumable kit = new RepairKit();
        //hero.setConsumable(kit);
        //hero.setWeapon(null);
        //hero.setBag(null);

        fight1v1();
    }

    public static void main(String[] args){
        LearningSoulsGame lsg = new LearningSoulsGame();
        lsg.init();

        lsg.testException();
    }
}
