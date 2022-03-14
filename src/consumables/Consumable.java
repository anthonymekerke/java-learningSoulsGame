package consumables;

import bags.Collectible;
import javafx.beans.property.SimpleBooleanProperty;

public class Consumable implements Collectible {
    protected String name;
    protected int capacity;
    protected String stat;

    private int weight;

    private SimpleBooleanProperty isEmpty;

    public Consumable(String name, int capacity, String stat) {
        this.name = name;
        this.capacity = capacity;
        this.stat = stat;
        this.weight = 1;

        isEmpty = new SimpleBooleanProperty(this.capacity == 0);
    }

    @Override
    public String toString(){

        return String.format("%s [%s %s point(s)]",
                name, capacity, stat);
    }

    public int use(){
        int cap = capacity;

        setCapacity(0);

        return cap;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;

        isEmpty.setValue(capacity == 0);
    }

    public String getStat() {
        return stat;
    }

    public SimpleBooleanProperty isEmptyProperty() {
        return isEmpty;
    }
}
