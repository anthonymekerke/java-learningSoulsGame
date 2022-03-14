package graphics.panes;

import graphics.widgets.texts.GameLabel;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CreationPane extends VBox {
    private static final Duration ANIMATION_DURATION = Duration.millis(1500);

    private TextField nameField;
    private GameLabel nameLabel;

    public CreationPane() {
        this.nameField = new TextField();
        this.nameLabel = new GameLabel("Player name");

        this.getChildren().addAll(nameLabel, nameField);

        this.setFillWidth(false);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }

    public void fadeIn(EventHandler<ActionEvent> finishedHandler){
        FadeTransition fd = new FadeTransition(ANIMATION_DURATION);
        fd.setToValue(1.0);

        fd.setNode(this);
        fd.setCycleCount(1);
        fd.setOnFinished(finishedHandler);
        fd.play();
    }

    public TextField getNameField() {
        return nameField;
    }
}
