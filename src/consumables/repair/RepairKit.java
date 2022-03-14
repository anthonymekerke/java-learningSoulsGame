package consumables.repair;

import consumables.Consumable;
import weapons.Weapon;

public class RepairKit extends Consumable {
    public RepairKit() {
        super("repair Kit", 10, Weapon.DURABILITY_STAT_STRING);
    }

    @Override
    public int use(){
        int capacity = getCapacity();

        if(capacity == 0) {return 0;}

        capacity -= 1;
        setCapacity(capacity);

        return 1;
    }
}
