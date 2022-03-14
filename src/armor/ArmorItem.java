package armor;

import bags.Collectible;

public class ArmorItem implements Collectible {
    public static final String PROTECTION_STAT_STRING = "protection";

    private String name;
    private float armorValue;

    protected int weight;

    public ArmorItem(String name, float armorValue) {
        this.name = name;
        this.armorValue = armorValue;
        this.weight = 4;
    }

    public String getName() {
        return name;
    }

    public float getArmorValue() {
        return armorValue;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + '(' + armorValue + ')';
    }
}
