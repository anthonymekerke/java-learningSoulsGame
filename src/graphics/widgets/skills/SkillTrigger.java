package graphics.widgets.skills;

import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SkillTrigger extends AnchorPane {
    private static final Duration ANIMATION_DURATION = Duration.millis(100);
    private static final double ZOOM_SCALE = 1.3;

    private ImageView view;
    private Label text;
    private KeyCode keyCode;
    private SkillAction action;

    private ColorAdjust desaturate;

    public SkillTrigger(KeyCode keyCode, String text, Image image, SkillAction action){
        this.view = new ImageView(image);
        this.keyCode = keyCode;
        this.text = new Label(text);
        this.action = action;

        this.desaturate = new ColorAdjust();
        this.desaturate.setSaturation(-1.0);
        this.desaturate.setBrightness(0.6);

        buildUI();
        addListeners();
    }

    private void buildUI(){
        this.getStyleClass().addAll("skill");

        view.setFitHeight(50);
        view.setFitWidth(50);

        this.getChildren().addAll(view, text);

        AnchorPane.setRightAnchor(view, 0.0);
        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);

        AnchorPane.setRightAnchor(text, 0.0);
        AnchorPane.setTopAnchor(text, 0.0);
        AnchorPane.setBottomAnchor(text, 0.0);
        AnchorPane.setLeftAnchor(text, 0.0);
    }

    private void addListeners(){
        this.setOnMouseClicked(event -> trigger());
        this.disabledProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1.equals(true)) {view.setEffect(desaturate);}
                else {view.setEffect(null);}
            }
        });
    }

    public void trigger(){
        if(!isDisabled()){
            animate();
            if(action != null) {action.execute();}
        }
    }

    private void animate(){
        ScaleTransition st = new ScaleTransition(ANIMATION_DURATION);
        st.setToX(ZOOM_SCALE);
        st.setToY(ZOOM_SCALE);
        st.setNode(this);
        st.setCycleCount(2);
        st.setAutoReverse(true);

        st.play();
    }

    public Image getImage(){
        return this.view.getImage();
    }

    public void setImage(Image image){
        this.view.setImage(image);
    }

    public Label getText() {
        return text;
    }

    public void setText(Label text) {
        this.text = text;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    public SkillAction getAction() {
        return action;
    }

    public void setAction(SkillAction action) {
        this.action = action;
    }
}
