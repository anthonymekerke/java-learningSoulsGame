package exceptions;

import weapons.Weapon;

public class WeaponBrokenException extends Exception {
    private Weapon weapon;

    public WeaponBrokenException(Weapon weapon){
        super(weapon.getName() + " is broken !");

        this.weapon = weapon;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }
}
