package graphics.widgets.skills;

import consumables.Consumable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;

public class ConsumableTrigger extends SkillTrigger {
    private Consumable consumable;

    public ConsumableTrigger(KeyCode code, String text, Consumable consumable, SkillAction action){
        super(code, text, null,action);

        setConsumable(consumable);

        this.getStyleClass().addAll("consumable");
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;

        if(this.consumable != null) {
            this.consumable.isEmptyProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    setDisable(t1);
                }
            });
        }
    }
}
