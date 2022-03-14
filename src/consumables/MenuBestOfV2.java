package consumables;

import consumables.drinks.Coffee;
import consumables.drinks.Whisky;
import consumables.drinks.Wine;
import consumables.food.Americain;
import consumables.food.Hamburger;

import java.util.HashSet;

public class MenuBestOfV2 {
    private HashSet<Consumable> menu;

    public MenuBestOfV2(){
        menu = new HashSet<>(5);

        menu.add(new Hamburger());
        menu.add(new Wine());
        menu.add(new Americain());
        menu.add(new Coffee());
        menu.add(new Whisky());
    }

    @Override
    public String toString() {
        String str = "MenuBestOfV2: \n";
        int i = 1;

        for(Consumable m : menu){
            str += i + ": " + m.toString() + "\n";
            i++;
        }

        return str;
    }

    public static void main(String[] args){
        MenuBestOfV2 menuBestOf = new MenuBestOfV2();

        System.out.println(menuBestOf);
    }
}
