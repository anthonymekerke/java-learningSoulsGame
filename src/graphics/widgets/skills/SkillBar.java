package graphics.widgets.skills;


import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.util.LinkedHashMap;

public class SkillBar extends HBox {
    private static LinkedHashMap<KeyCode, String> DEFAULT_BINDING = new LinkedHashMap<>();
    static{ //permet d'executer le code dans la JVM (avant toute instanciation)
        DEFAULT_BINDING.put(KeyCode.A, "a");
        DEFAULT_BINDING.put(KeyCode.Z, "z");
        DEFAULT_BINDING.put(KeyCode.E, "e");
        DEFAULT_BINDING.put(KeyCode.R, "r");
        DEFAULT_BINDING.put(KeyCode.T, "t");
    }

    private SkillTrigger[] triggers;
    private ConsumableTrigger consumableTrigger = new ConsumableTrigger(KeyCode.C, "c", null, null);

    public SkillBar(){
        this.setSpacing(10);
        this.setPrefHeight(110);
        this.setAlignment(Pos.BASELINE_CENTER);

        init();
    }

    private void init(){
        triggers = new SkillTrigger[DEFAULT_BINDING.size()];

        int i = 0;
        for(KeyCode key : DEFAULT_BINDING.keySet()){
            SkillTrigger trigger = new SkillTrigger(key, DEFAULT_BINDING.get(key), null, null);
            triggers[i] = trigger;
            this.getChildren().add(triggers[i]);
            i++;
        }

        Rectangle rect = new Rectangle();
        rect.setArcHeight(30);
        this.getChildren().addAll(rect, consumableTrigger);
    }

    public void process(KeyCode code){
        System.out.println("process function called");
        if(!isDisabled()){
            for(SkillTrigger trig : triggers){
                if(trig.getKeyCode() == code) {trig.trigger();}
            }

            if(consumableTrigger.getKeyCode() == code) {consumableTrigger.trigger();}
        }
    }

    public SkillTrigger getTrigger(int slot){
        return triggers[slot];
    }

    public ConsumableTrigger getConsumableTrigger() {
        return consumableTrigger;
    }
}
