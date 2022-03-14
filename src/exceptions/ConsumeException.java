package exceptions;

import consumables.Consumable;

public abstract class ConsumeException extends Exception {
    private Consumable consumable;

    public ConsumeException(String message, Consumable consumable){
        super(message);
        this.consumable = consumable;
    }

    public Consumable getConsumable() {
        return consumable;
    }
}
