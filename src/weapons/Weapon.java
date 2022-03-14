package weapons;

import bags.Collectible;
import consumables.repair.RepairKit;
import exceptions.ConsumeNullException;

public class Weapon implements Collectible {
    public static final String DURABILITY_STAT_STRING = "durability";

    protected String name;
    protected int minDamage;
    protected int maxDamage;
    protected int stamCost;
    protected int durability;

    protected int weight;

    public Weapon(String name, int minDamage, int maxDamage, int stamCost, int durability) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.stamCost = stamCost;
        this.durability = durability;
        this.weight = 2;
    }

    public void repairWith(RepairKit repairKit)
            throws ConsumeNullException
    {
        if(repairKit == null) {throw new ConsumeNullException();}
        durability += repairKit.use();
    }

    public void use(){
        this.durability--;
    }

    public boolean isBroken(){
        return this.durability <= 0;
    }

    public String getName() {
        return name;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getStamCost() {
        return stamCost;
    }

    public int getDurability() {
        return durability;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    private void setDurability(int d){
        this.durability = d;
    }

    @Override
    public String toString(){

        return String.format("%s (min: %d, max: %d, stam: %d, %s: %d)",
                name, minDamage, maxDamage, stamCost,
                DURABILITY_STAT_STRING, durability);
    }
}
