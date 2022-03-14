package characters;

import buffs.talismans.Talisman;

public class Monster extends Character {
    protected static int INSTANCES_COUNT = 0;

    protected float skinThickness;
    protected Talisman talisman;

    public Monster(String name) {
        super(name);
        this.life = 10;
        this.maxLife = 10;

        this.stamina = 10;
        this.maxStamina = 10;

        this.skinThickness = 20;

        INSTANCES_COUNT++;
    }

    public Monster() {
        this("Monster_" + INSTANCES_COUNT);
    }

    public float getSkinThickness() {
        return skinThickness;
    }

    public void setSkinThickness(float skinThickness) {
        this.skinThickness = skinThickness;
    }

    @Override
    public float computeProtection(){
        return getSkinThickness();
    }

    @Override
    public float computeBuff() {
        if(talisman != null) {
            return talisman.computeBuffValue();
        }

        return 0.0f;
    }
}
