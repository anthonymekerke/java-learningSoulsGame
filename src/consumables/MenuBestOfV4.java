package consumables;

import consumables.drinks.Coffee;
import consumables.drinks.Whisky;
import consumables.drinks.Wine;
import consumables.food.Americain;
import consumables.food.Hamburger;
import consumables.repair.RepairKit;

import java.util.LinkedHashSet;

public class MenuBestOfV4 extends LinkedHashSet<Consumable> {

    public MenuBestOfV4(){
        add(new Hamburger());
        add(new Wine());
        add(new Americain());
        add(new Coffee());
        add(new Whisky());
        add(new RepairKit());
    }

    @Override
    public String toString() {
        String str = "MenuBestOfV4: \n";
        int i = 1;

        for(Consumable m : this){
            str += i + ": " + m.toString() + "\n";
            i++;
        }

        return str;
    }

    public static void main(String[] args){
        MenuBestOfV4 menuBestOfV4 = new MenuBestOfV4();
        MenuBestOfV3 menuBestOfV3 = new MenuBestOfV3();

        System.out.println(menuBestOfV3);
        System.out.println(menuBestOfV4);
    }
}
