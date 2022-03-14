package graphics.panes;

import graphics.widgets.characters.renderers.statbars.StatBar;
import graphics.widgets.skills.SkillBar;
import graphics.widgets.texts.GameLabel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class HUDPane extends BorderPane {
    private MessagePane messagePane;
    private StatBar heroStatBar;
    private StatBar monsterStatBar;
    private SkillBar skillBar;
    private GameLabel scoreLabel;

    private IntegerProperty score = new SimpleIntegerProperty();

    public HUDPane(){
        buildCenter();
        buildTop();
        buildBottom();

        this.score.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                scoreLabel.setText(t1.toString());
            }
        });
    }

    private void buildCenter(){
        this.messagePane = new MessagePane();
        //this.getChildren().add(messagePane); //no need to add the pane to children, function setCenter does it

        setCenter(getMessagePane());
    }

    private void buildTop(){
        BorderPane topPane = new BorderPane();
        this.heroStatBar = new StatBar();
        this.monsterStatBar = new StatBar();
        this.scoreLabel = new GameLabel("0");

        this.scoreLabel.setTranslateY(40);
        this.scoreLabel.setScaleX(1.3);
        this.scoreLabel.setScaleY(1.3);

        topPane.setLeft(getHeroStatBar());
        topPane.setRight(getMonsterStatBar());
        topPane.setCenter(scoreLabel);
        setTop(topPane);


    }

    private void buildBottom(){
        this.skillBar = new SkillBar();

        setBottom(getSkillBar());
    }


    public StatBar getHeroStatBar() {
        return heroStatBar;
    }

    public StatBar getMonsterStatBar() {
        return monsterStatBar;
    }

    public SkillBar getSkillBar() {
        return skillBar;
    }

    public MessagePane getMessagePane() {
        return messagePane;
    }


    public IntegerProperty scoreProperty() {
        return score;
    }
}
