package consumables;

import consumables.drinks.Coffee;
import consumables.drinks.Whisky;
import consumables.drinks.Wine;
import consumables.food.Americain;
import consumables.food.Hamburger;

import java.util.HashSet;

public class MenuBestOfV3 extends HashSet<Consumable> {

    public MenuBestOfV3(){
        add(new Hamburger());
        add(new Wine());
        add(new Americain());
        add(new Coffee());
        add(new Whisky());
    }

    @Override
    public String toString() {
        String str = "MenuBestOfV3: \n";
        int i = 1;

        for(Consumable m : this){
            str += i + ": " + m.toString() + "\n";
            i++;
        }

        return str;
    }

    public static void main(String[] args){
        MenuBestOfV3 menuBestOf = new MenuBestOfV3();

        System.out.println(menuBestOf);
    }
}
