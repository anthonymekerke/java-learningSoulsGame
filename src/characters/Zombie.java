package characters;

import bags.*;
import consumables.Consumable;
import consumables.Potion;
import exceptions.BagFullException;
import weapons.Weapon;

public class Zombie extends Monster {
    public static final String ZOMBIE_VANILLA_TYPE = "Zombie";
    public static final String ZOMBIE_GIRL_TYPE = "Zombie Girl";
    public static final String ZOMBIE_KID_TYPE = "Zombie Kid";
    public static final String ZOMBIE_DISMEMBER_TYPE = "Zombie Dismembered";
    public static final String ZOMBIE_PUNK_TYPE = "Zombie Punk";

    public Zombie(String name, double indexLife, double indexStamina, double indexSkinThickness, int indexDmg){
        super(name);
        this.life *= indexLife;
        this.maxLife *= indexLife;
        this.stamina *= indexStamina;
        this.maxStamina *= indexStamina;
        this.skinThickness *= indexSkinThickness;

        this.weapon = new Weapon("Zombie's hand",5*indexDmg, 20*indexDmg, 1, 1000);
    }

    public static String generateZombieType(){
        if(INSTANCES_COUNT % 7 == 0){
            return ZOMBIE_PUNK_TYPE;
        }
        if(INSTANCES_COUNT % 5 == 0){
            return ZOMBIE_DISMEMBER_TYPE;
        }
        if(INSTANCES_COUNT % 3 == 0){
            return ZOMBIE_KID_TYPE;
        }
        if(INSTANCES_COUNT % 2 == 0){
            return ZOMBIE_GIRL_TYPE;
        }
        else{
            return ZOMBIE_VANILLA_TYPE;
        }
    }

    public static Bag drop(String type){
        Bag loot;

        switch (type){
            case ZOMBIE_VANILLA_TYPE:
                loot = generateMinorBag();
                break;

            case ZOMBIE_GIRL_TYPE:
                loot = generateSmallBag();
                break;

            case ZOMBIE_KID_TYPE:
                loot = generateMediumBag();
                break;

            case ZOMBIE_DISMEMBER_TYPE:
                loot = generateSuperBag();
                break;

            case ZOMBIE_PUNK_TYPE:
                loot = generateEpicBag();
                break;

            default:
                loot = null;
        }

        return loot;
    }

    private static Bag generateMinorBag(){
        Bag bag = new MinorBag();
        Consumable lifePotion = new Potion(Potion.MN_BLOOD);
        Consumable maxLifePotion = new Potion(Potion.MN_VITALITY);
        Consumable stamPotion = new Potion(Potion.MN_STRENGTH);
        Consumable maxStamPotion = new Potion(Potion.MN_MIGHT);

        try{
            bag.push(lifePotion);
            bag.push(maxLifePotion);
            bag.push(stamPotion);
            bag.push(maxStamPotion);
        }catch(BagFullException ignored){}

        return bag;
    }

    private static Bag generateSmallBag(){
        Bag bag = new SmallBag();

        return bag;
    }

    private static Bag generateMediumBag(){
        Bag bag = new MediumBag();

        return bag;
    }

    private static Bag generateSuperBag(){
        Bag bag = new SuperBag();

        return bag;
    }

    private static Bag generateEpicBag(){
        Bag bag = new EpicBag();

        return bag;
    }

    public static void main(String[] args){
        Zombie[] zombies = new Zombie[20];

        for(int i = 0; i < zombies.length; i++){
            zombies[i] = new Zombie(Zombie.generateZombieType(), 1,1,1,1);

            System.out.println(zombies[i]);
        }
    }
}
