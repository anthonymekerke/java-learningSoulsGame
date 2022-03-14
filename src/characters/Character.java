package characters;

import armor.ArmorItem;
import bags.Bag;
import bags.Collectible;
import bags.SmallBag;
import buffs.BuffItem;
import consumables.Consumable;
import consumables.drinks.Drink;
import consumables.food.Food;
import consumables.repair.RepairKit;
import exceptions.*;
import helpers.Dice;
import javafx.beans.property.SimpleDoubleProperty;
import weapons.Sword;
import weapons.Weapon;

import java.util.Locale;

public abstract class Character {
    public static final String LIFE_STAT_STRING = "life";
    public static final String STAM_STAT_STRING = "stamina";
    public static final String MAX_LIFE_STAT_STRING = "maxLife";
    public static final String MAX_STAM_STAT_STRING = "maxStamina";

    protected String name;
    protected int life;
    protected int maxLife;
    protected int stamina;
    protected int maxStamina;

    protected SimpleDoubleProperty lifeRate;
    protected SimpleDoubleProperty stamRate;

    protected Dice dice;
    protected Weapon weapon;
    protected Consumable consumable;

    protected Bag bag;

    public Character(String name) {
        this.name = name;

        this.dice = new Dice(101);
        this.lifeRate = new SimpleDoubleProperty(1.0);
        this.stamRate = new SimpleDoubleProperty(1.0);
        this.weapon = new Sword();
        this.bag = new SmallBag();
    }

    public Character() {
        this("Character");
    }

    public boolean isAlive(){
        return life > 0;
    }

    @Override
    public String toString(){
        String status = isAlive() ? "ALIVE" : "DEAD";

        return String.format(Locale.US, "[ %s ] %-20s%s: %-10d%s: %-10d%s: %-10s%s: %-10s(%s)",
                getClass().getSimpleName(), name,
                LIFE_STAT_STRING, life,
                STAM_STAT_STRING, stamina,
                ArmorItem.PROTECTION_STAT_STRING, computeProtection(),
                BuffItem.BUFF_STAT_STRING, computeBuff(),
                status);
    }

    public void printStats(){
        System.out.println(toString());
    }

    public void printWeapon() {System.out.println("WEAPON : " + getWeapon());}

    public void printConsumable() {System.out.println("CONSUMABLE : " + getConsumable());}

    public void printBag() {System.out.println("BAG : " + bag);}

    public void use(Consumable consumable)
            throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeapon
    {
        if(consumable == null) {throw new ConsumeNullException();}
        if(consumable.getCapacity() == 0) {throw new ConsumeEmptyException(consumable); }

        if(consumable instanceof  Food) {eat((Food)consumable);}
        else if(consumable instanceof Drink) {drink((Drink)consumable);}
        else if(consumable instanceof RepairKit) {
            try {
                repairWeaponWith((RepairKit) consumable);
            }catch(WeaponNullException e){
                throw new ConsumeRepairNullWeapon(consumable);
            }
        }
    }

    private void repairWeaponWith(RepairKit kit)
            throws WeaponNullException, ConsumeNullException
    {
        if(weapon == null) {throw new WeaponNullException();}
        if(kit == null) {throw new ConsumeNullException();}

        System.out.println(name + " repairs " + weapon.toString() + " with " + kit);
        weapon.repairWith(kit);
    }

    private void eat(Food f)
            throws ConsumeNullException
    {
        if(f == null) {throw new ConsumeNullException();}

        System.out.println(name + " eats " + f);
        int extraLife = f.use();
        setLife(Math.min(life + extraLife, maxLife));
    }

    private void drink(Drink d)
            throws ConsumeNullException
    {
        if(d == null) {throw new ConsumeNullException();}

        System.out.println(name + " drinks " + d);
        int extraStam = d.use();
        setStamina(Math.min(stamina + extraStam, maxStamina));
    }

    public void consume()
            throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeapon
    {
        use(consumable);
    }

    private <T extends Consumable> T fastUseFirst(Class<T> type)
            throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeapon, NoBagException
    {
        T firstConsumable = null;
        Collectible[] items = getBagItems();

        for(Collectible collectible : items){
            if(type.isInstance(collectible)){
                firstConsumable = (T) collectible;

                use(firstConsumable);
                if(firstConsumable.getCapacity() <= 0) {
                    pullOut(firstConsumable);
                    System.out.print("\n");
                }

                break;
            }
        }

        return firstConsumable;
    }

    public Drink fastDrink()
            throws ConsumeNullException, ConsumeEmptyException, NoBagException
    {
        try {
            System.out.println(getName() + " drinks FAST :");
            return fastUseFirst(Drink.class);
        }catch(ConsumeRepairNullWeapon e) {return null;}
    }

    public Food fastEat()
            throws ConsumeNullException, ConsumeEmptyException, NoBagException
    {
        try{
            System.out.println(getName() + " eats FAST :");
            return fastUseFirst(Food.class);
        }catch(ConsumeRepairNullWeapon e){return null;}
    }

    public RepairKit fastRepair()
            throws ConsumeNullException, ConsumeEmptyException, ConsumeRepairNullWeapon, NoBagException
    {
        System.out.println(getName() + " repairs FAST :");
        return fastUseFirst(RepairKit.class);
    }

    public int attack()
            throws WeaponNullException, WeaponBrokenException, StaminaEmptyException
    {
        int damage = attackWith(weapon);

        weapon.use();

        return damage;
    }

    private int attackWith(Weapon weapon)
            throws WeaponNullException, WeaponBrokenException, StaminaEmptyException
    {
        float buff = computeBuff();
        int damage;
        int stam = getStamina();

        if(weapon == null) {throw new WeaponNullException();}
        if(weapon.isBroken()) {throw new WeaponBrokenException(weapon);}
        if(getStamina() == 0) {throw new StaminaEmptyException();}

        damage = (int) (weapon.getMinDamage() +
                        Math.round(dice.roll() * (weapon.getMaxDamage() - weapon.getMinDamage()) / (double)dice.getFace()));

        if(stamina > weapon.getStamCost()){
            stam -= weapon.getStamCost();
        }
        else{
            damage = (int) Math.round(stamina * (damage / (double)weapon.getStamCost()));
            stam = 0;
        }

        setStamina(stam);

        damage = Math.round(damage * (1 + buff/100f));

        return damage;
    }

    public int getHitWith(int value){
        float armor = computeProtection();
        int l;

        int hp_loss = Math.round(value * (1 - armor/100f));

        hp_loss = Math.min(hp_loss, life);

        l = hp_loss < life ? life - hp_loss : 0;

        this.setLife(l);

        return hp_loss;
    }

    public abstract float computeProtection();

    public abstract float computeBuff();

    public void pickUp(Collectible item)
            throws NoBagException, BagFullException
    {
        if(bag == null) {throw new NoBagException();}

        bag.push(item);
        System.out.println(name + " picks up " + item);
    }

    public Collectible pullOut(Collectible item)
            throws NoBagException
    {
        if(bag == null) {throw new NoBagException();}

        Collectible pullOutItem = bag.pop(item);

        if(pullOutItem != null) {System.out.print(name + " pulls out " + pullOutItem);}
        else {System.out.println("this item is not in the bag");}

        return pullOutItem;
    }

    public void equip(Weapon weapon)
            throws NoBagException
    {
        Collectible pulledOut = pullOut(weapon);

        if(pulledOut != null) {
            this.setWeapon(weapon);
            System.out.println(" and equips it !");
        }
    }

    public void equip(Consumable consumable)
            throws NoBagException
    {
        Collectible pulledOut = pullOut(consumable);

        if(pulledOut != null) {
            this.setConsumable(consumable);
            System.out.println(" and equips it !");
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
        lifeRate.set((double)life/maxLife);
    }

    public SimpleDoubleProperty lifeRateProperty() {
        return lifeRate;
    }

    public SimpleDoubleProperty stamRateProperty() {
        return stamRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;

        lifeRate.set((double)life/maxLife);
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;

        stamRate.set((double)this.stamina/maxStamina);
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;

        stamRate.set((double)this.stamina/maxStamina);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

    public int getBagCapacity()
            throws NoBagException
    {
        if(bag == null) {throw new NoBagException();}

        return this.bag.getCapacity();
    }

    public int getBagWeight()
            throws NoBagException
    {
        if(bag == null) {throw new NoBagException();}

        return this.bag.getWeight();
    }

    public Collectible[] getBagItems()
            throws NoBagException
    {
        if(bag == null) {throw new NoBagException();}

        return this.bag.getItems();
    }

    public Bag setBag(Bag bag){

        if(bag == null) {
            this.bag = null;
            return null;
        }

        Bag oldBag;
        Bag.transfer(this.bag, bag);

        System.out.println(name + " changes " +
                this.bag.getClass().getSimpleName() +
                " for " + bag.getClass().getSimpleName());

        oldBag = this.bag;
        this.bag = bag;

        return oldBag;
    }
}
