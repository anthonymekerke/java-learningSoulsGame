package exceptions;

public class ConsumeNullException extends ConsumeException {
    public ConsumeNullException(){
        super("Consumable is null !", null);
    }
}
