package exceptions;

import consumables.Consumable;

public class ConsumeRepairNullWeapon extends ConsumeException {
    public ConsumeRepairNullWeapon(Consumable consumable){
        super("Trying to repair null weapon !", consumable);
    }
}
