package consumables;

import characters.Character;

public class Potion extends Consumable {
    public static final String MN_VITALITY = "Minor Vitality Potion";
    public static final String SM_VITALITY = "Small Vitality Potion";
    public static final String MD_VITALITY = "Medium Vitality Potion";
    public static final String SP_VITALITY = "Super Vitality Potion";
    public static final String EP_VITALITY = "Epic Vitality Potion";
    public static final String MN_MIGHT = "Minor Might Potion";
    public static final String SM_MIGHT = "Small Might Potion";
    public static final String MD_MIGHT = "Medium Might Potion";
    public static final String SP_MIGHT = "Super Might Potion";
    public static final String EP_MIGHT = "Epic Might Potion";
    public static final String MN_BLOOD = "Minor Blood Potion";
    public static final String SM_BLOOD = "Small Blood Potion";
    public static final String MD_BLOOD = "Medium Blood Potion";
    public static final String SP_BLOOD = "Super Blood Potion";
    public static final String EP_BLOOD = "Epic Blood Potion";
    public static final String MN_STRENGTH = "Minor Strength Potion";
    public static final String SM_STRENGTH = "Small Strength Potion";
    public static final String MD_STRENGTH = "Medium Strength Potion";
    public static final String SP_STRENGTH = "Super Strength Potion";
    public static final String EP_STRENGTH = "Epic Strength Potion";

    public Potion(String name){
        super(name, 0, "");

        if(this.name.contains("MN")){this.capacity = 10;}
        if(this.name.contains("SM")){this.capacity = 20;}
        if(this.name.contains("MD")){this.capacity = 30;}
        if(this.name.contains("SP")){this.capacity = 40;}
        if(this.name.contains("EP")){this.capacity = 50;}

        if(this.name.contains("BLOOD")){this.stat = Character.LIFE_STAT_STRING;}
        if(this.name.contains("MIGHT")){this.stat = Character.MAX_STAM_STAT_STRING;}
        if(this.name.contains("STRENGTH")){this.stat = Character.STAM_STAT_STRING;}
        if(this.name.contains("VITALITY")){this.stat = Character.MAX_LIFE_STAT_STRING;}
    }
}
