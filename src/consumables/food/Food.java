package consumables.food;

import consumables.Consumable;
import characters.Character;

public class Food extends Consumable {

    public Food(String name, int capacity){
        super(name, capacity, Character.LIFE_STAT_STRING);
    }

    public static void main(String[] args){
        Americain americain = new Americain();
        Hamburger hamburger = new Hamburger();

        System.out.println(americain);
        System.out.println(hamburger);
    }
}
