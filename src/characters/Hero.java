package characters;

import armor.ArmorItem;
import armor.BlackWitchVeil;
import armor.DragonSlayerLeggings;
import bags.Collectible;
import buffs.rings.Ring;
import exceptions.NoBagException;

public class Hero extends Character {
    private static final int MAX_ARMOR_PIECES = 3;
    private static final int MAX_BUFF_RINGS = 2;

    private ArmorItem[] armor = new ArmorItem[MAX_ARMOR_PIECES];
    private Ring[] rings = new Ring[MAX_BUFF_RINGS];

    private int stamRegen = 10;

    public Hero(String name) {
        super(name);
        this.life = 100;
        this.maxLife = 100;

        this.stamina = 50;
        this.maxStamina = 50;
    }

    public Hero() {
        this("Gregooninator");
    }

    public void setArmorItem(ArmorItem item, int slot){
        if(slot < 0 || slot > MAX_ARMOR_PIECES) {
            return;
        }

        armor[slot - 1] = item;
    }

    public ArmorItem[] getArmorItems() {
        return this.armor;
    }

    public void setRing(Ring ring, int slot){
        if(slot < 0 || slot > MAX_BUFF_RINGS) {
            return;
        }

        rings[slot - 1] = ring;
        rings[slot - 1].setHero(this);
    }

    public float getTotalArmor(){
        float totalArmor = 0.0f;

        for(int i = 0; i < MAX_ARMOR_PIECES; i++){
            if(armor[i] != null) {
                totalArmor+= armor[i].getArmorValue();
            }
        }

        return totalArmor;
    }

    public float getTotalBuffs(){
        float totalBuffs = 0.0f;

        for(int i = 0; i < MAX_BUFF_RINGS; i++){
            if(rings[i] != null) {
                totalBuffs+= rings[i].computeBuffValue();
            }
        }

        return totalBuffs;
    }

    public Ring[] getRings(){
        return rings;
    }

    public int getStamRegen() {
        return stamRegen;
    }

    public void setStamRegen(int stamRegen) {
        this.stamRegen = stamRegen;
    }

    public void recuperate(){
        setStamina(Math.min(getStamina() + getStamRegen(), getMaxStamina()));
    }

    public void equip(ArmorItem item, int slot)
            throws NoBagException
    {
        Collectible pulledOut = pullOut(item);

        if(pulledOut != null){
            this.setArmorItem(item, slot);
            System.out.println(" and equips it !");
        }
    }

    public void equip(Ring ring, int slot)
            throws NoBagException
    {
        Collectible pulledOut = pullOut(ring);

        if(pulledOut != null){
            this.setRing(ring, slot);
            System.out.println(" and equips it !");
        }
    }

    public String armorToString(){
        String str = "ARMOR \t";

        for(int i = 0; i < MAX_ARMOR_PIECES; i++){
            if(armor[i] != null) {str+= i+1 + ":" + armor[i] + "\t";}
            else {str+= i+1 + ":empty \t";}
        }

        return str;
    }

    public String ringsToString(){
        String str = "RINGS \t";

        for(int i = 0; i < MAX_BUFF_RINGS; i++){
            if(rings[i] != null) {str+= i+1 + ":" + rings[i] + "\t";}
            else {str+= i+1 + ":empty \t";}
        }

        return str;
    }

    public void printRings() {System.out.println(ringsToString());}

    public void printArmor() {System.out.println(armorToString());}

    @Override
    public float computeProtection(){
        return getTotalArmor();
    }

    @Override
    public float computeBuff() {
        return getTotalBuffs();
    }

    public static void main(String[] args){
        Hero hero = new Hero();
        BlackWitchVeil bwv = new BlackWitchVeil();
        DragonSlayerLeggings dsl = new DragonSlayerLeggings();

        hero.setArmorItem(bwv, 1);
        hero.setArmorItem(dsl, 3);

        String str = hero.armorToString();
        System.out.println(str);
        float total = hero.getTotalArmor();
        System.out.println("TOTAL:" + total);
    }
}
