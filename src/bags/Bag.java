package bags;

import armor.DragonSlayerLeggings;
import armor.RingedKnightArmor;
import consumables.food.Americain;
import exceptions.BagFullException;
import weapons.ShotGun;

import java.util.HashSet;

public class Bag {

    private int capacity;
    private int weight;
    private HashSet<Collectible> items;

    public Bag(int c){
        capacity = c;
        weight = 0;
        items = new HashSet<>();
    }

    public static void transfer(Bag from, Bag into){

        if(from == null || into == null) {return;}

        Collectible[] fromItems = from.getItems();
        Collectible currentItem;
        int i = 0;

        while(i < fromItems.length){
            currentItem = fromItems[i];
            i++;

            try{
                into.push(currentItem);
                from.pop(currentItem);
            }catch(BagFullException e){
                break;
            }
        }
    }

    public void push(Collectible item)
            throws BagFullException
    {
        if(item.getWeight() + weight <= capacity){
            items.add(item);
            weight += item.getWeight();
        }
        else {throw new BagFullException(this);}
    }

    public Collectible pop(Collectible item){
        for(Collectible c : items){
            if(c.equals(item)){
                weight -= c.getWeight();
                items.remove(item);
                return c;
            }
        }
        return null;
    }

    public boolean contains(Collectible item){
        for(Collectible c : items){
            if(c.equals(item)){
                return true;
            }
        }
        return false;
    }

    public Collectible[] getItems(){
        int size = items.size();
        Collectible[] collectibles = new Collectible[size];
        int i = 0;
        for(Collectible item : items){
            collectibles[i] = item;
            i++;
        }

        return collectibles;
    }

    @Override
    public String toString(){
        String str = getClass().getSimpleName() + " [ " + items.size() + " items | " + getWeight() + "/" + getCapacity() + " kg ]\n";
        if(weight > 0) {
            for (Collectible item : items) {
                str += "\u2219 " + item + " [" + item.getWeight() + " kg]" + "\n";
            }
        }
        else{
            str += "\u2219 (empty)";
        }

        return str;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getWeight() {
        return weight;
    }

    public static void main(String[] args) {
        Bag bag1 = new Bag(10);
        Bag bag2 = new Bag(5);
        ShotGun shotGun = new ShotGun();
        DragonSlayerLeggings dragonSlayerLeggings = new DragonSlayerLeggings();
        RingedKnightArmor ringedKnightArmor = new RingedKnightArmor();
        Americain americain = new Americain();

        try {
            bag1.push(shotGun);
            bag1.push(dragonSlayerLeggings);
            bag1.push(ringedKnightArmor);
            bag1.push(americain);
        }catch(BagFullException e){
            e.printStackTrace();
        }

        System.out.println("Avant transfert");
        System.out.println(bag1);
        System.out.println(bag2);

        transfer(bag1, bag2);
        System.out.println("\nAprès transfert!!!");
        System.out.println(bag1);
        System.out.println(bag2);
    }
}
