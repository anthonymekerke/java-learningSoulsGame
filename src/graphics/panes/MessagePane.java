package graphics.panes;

import graphics.widgets.texts.GameLabel;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MessagePane extends VBox {
    private static final Duration ANIMATION_DURATION = Duration.millis(3000);

    public MessagePane(){
        setAlignment(Pos.CENTER);
    }

    public void showMessage(String msg){
        GameLabel gameLabel = new GameLabel(msg);
        this.getChildren().add(gameLabel);

        TranslateTransition tt = new TranslateTransition(ANIMATION_DURATION);
        tt.setByY(-200);

        FadeTransition fd = new FadeTransition(ANIMATION_DURATION);
        fd.setToValue(0.0);

        ParallelTransition pt = new ParallelTransition(tt, fd);


        pt.setNode(gameLabel);
        pt.setCycleCount(1); //nb repetition de l'effet
        pt.setOnFinished(event -> getChildren().removeAll(gameLabel));
        pt.play();
    }
}
