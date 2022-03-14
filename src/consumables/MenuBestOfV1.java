package consumables;

import consumables.drinks.Coffee;
import consumables.drinks.Whisky;
import consumables.drinks.Wine;
import consumables.food.Americain;
import consumables.food.Hamburger;

public class MenuBestOfV1 {
    private Consumable[] menu;

    public MenuBestOfV1(){
        menu = new Consumable[5];

        menu[0] = new Hamburger();
        menu[1] = new Wine();
        menu[2] = new Americain();
        menu[3] = new Coffee();
        menu[4] = new Whisky();
    }

    @Override
    public String toString() {
        String str = "MenuBestOfV1: \n";

        for(int i = 0; i < menu.length; i++){
            str += (i+1) + ": " + menu[i].toString() + "\n";
        }

        return str;
    }

    public static void main(String[] args){
        MenuBestOfV1 menuBestOfV1 = new MenuBestOfV1();

        System.out.println(menuBestOfV1);
    }
}
