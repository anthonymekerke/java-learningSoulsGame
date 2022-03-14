package consumables.drinks;

import consumables.Consumable;
import characters.Character;

public class Drink extends Consumable {

    public Drink(String name, int capacity){
        super(name, capacity, Character.STAM_STAT_STRING);
    }

    public static void main(String[] args){
        Coffee coffee = new Coffee();
        Wine wine = new Wine();
        Whisky whisky = new Whisky();

        System.out.println(coffee);
        System.out.println(wine);
        System.out.println(whisky);
    }
}
