package graphics.widgets.characters.renderers.statbars;

import graphics.ImageFactory;
import graphics.widgets.texts.GameLabel;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class StatBar extends BorderPane {
    ImageView avatar;
    GameLabel name;
    ProgressBar lifeBar;
    ProgressBar stamBar;

    public StatBar(){
        this.setPrefHeight(100);
        this.setPrefWidth(350);

        Image[] images = ImageFactory.getSprites(ImageFactory.SPRITES_ID.HERO_HEAD);
        avatar = new ImageView(images[0]);
        avatar.setFitHeight(100);
        avatar.setPreserveRatio(true);

        name = new GameLabel("name");
        name.setStyle("-fx-font-size: 33px");

        lifeBar = new ProgressBar();
        lifeBar.setMaxWidth(Double.MAX_VALUE);
        lifeBar.setStyle("-fx-accent: red");

        stamBar = new ProgressBar();
        stamBar.setMaxWidth(Double.MAX_VALUE);
        stamBar.setStyle("-fx-accent: greenyellow");

        VBox box = new VBox();
        box.getChildren().addAll(name, lifeBar, stamBar);
        setLeft(avatar);
        setCenter(box);

        setPadding(new Insets(20, 0,0,0));
    }

    public void flip(){
        this.setScaleX(-this.getScaleX());
        name.setScaleX(-name.getScaleX());
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public GameLabel getName() {
        return name;
    }

    public ProgressBar getLifeBar() {
        return lifeBar;
    }

    public ProgressBar getStamBar() {
        return stamBar;
    }
}
