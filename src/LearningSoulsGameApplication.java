import characters.Character;
import characters.Hero;
import characters.Zombie;
import consumables.food.SuperBerry;
import exceptions.*;
import graphics.CSSFactory;
import graphics.CollectibleFactory;
import graphics.ImageFactory;
import graphics.panes.CreationPane;
import graphics.panes.HUDPane;
import graphics.panes.TitlePane;
import graphics.widgets.characters.renderers.*;
import graphics.widgets.skills.SkillBar;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import graphics.panes.AnimationPane;
import weapons.Sword;

public class LearningSoulsGameApplication extends Application {
    private Scene scene;
    private AnchorPane root;
    private TitlePane gameTitle;
    private CreationPane creationPane;
    private AnimationPane animationPane;
    private HUDPane hudPane;

    private String heroName;
    private SkillBar skillBar;

    private BooleanProperty heroCanPlay = new SimpleBooleanProperty(!false);
    private IntegerProperty score = new SimpleIntegerProperty();

    private Hero hero;
    private HeroRenderer heroRenderer;
    private Zombie zombie;
    private CharacterRenderer zombieRenderer;

    @Override
    public void start(Stage stage){
        stage.setTitle("LearningSoulsGame");

        root = new AnchorPane();
        scene = new Scene(root, 1200, 650); //original size: 1200 800

        stage.setScene(scene);
        stage.setResizable(false);

        buildUI(stage);
        addListeners();

        stage.show();
        startGame();
    }

    private void buildUI(Stage stage){
        scene.getStylesheets().addAll(
                CSSFactory.getStyleSheet("LSG.css"),
                CSSFactory.getStyleSheet("LSGFont.css"),
                CSSFactory.getStyleSheet("SkillTrigger.css"),
                CSSFactory.getStyleSheet("ConsumableTrigger.css")
        );

        gameTitle = new TitlePane(scene, "LearningSoulsGame");
        AnchorPane.setLeftAnchor(gameTitle, 0.0);
        AnchorPane.setRightAnchor(gameTitle, 0.0);
        AnchorPane.setTopAnchor(gameTitle, 0.0);
        root.getChildren().add(gameTitle);

        creationPane = new CreationPane();
        creationPane.setOpacity(0.0);
        AnchorPane.setRightAnchor(creationPane, 0.0);
        AnchorPane.setTopAnchor(creationPane, 0.0);
        AnchorPane.setLeftAnchor(creationPane, 0.0);
        AnchorPane.setBottomAnchor(creationPane, 0.0);
        root.getChildren().add(creationPane);

        animationPane = new AnimationPane(root);

        hudPane = new HUDPane();
        AnchorPane.setRightAnchor(hudPane, 0.0);
        AnchorPane.setTopAnchor(hudPane, 0.0);
        AnchorPane.setLeftAnchor(hudPane, 0.0);
        AnchorPane.setBottomAnchor(hudPane, 0.0);
    }

    public void addListeners(){
        creationPane.getNameField().setOnAction(event -> {
            heroName = creationPane.getNameField().getText();
            System.out.println("Nom du héro: " + heroName);

            if(!heroName.isEmpty()){
                root.getChildren().removeAll(creationPane);
                gameTitle.zoomOut(event1 -> this.play());
            }
        });
    }

    public void startGame(){
        gameTitle.zoomIn((event ->
                creationPane.fadeIn(event1 ->
                        ImageFactory.preloadAll(() ->
                                System.out.println("Pré chargement des images terminé !")))));
    }

    private void play(){
        root.getChildren().addAll(animationPane, hudPane);

        createHero();
        createSkills();

        hudPane.getMonsterStatBar().flip();//flip monsterStatBar only at first 'createMonster' call instead of everytime
        createMonster(event -> {
            hudPane.getMessagePane().showMessage("FIGHT !!!!!!!!");
            heroCanPlay.setValue(!true);
        });

        hudPane.scoreProperty().bind(score);
    }

    private void createHero(){
        hero = new Hero(heroName);
        Sword sword = new Sword();
        SuperBerry berry = new SuperBerry();
        hero.setWeapon(sword);
        hero.setConsumable(berry);

        heroRenderer = animationPane.createHeroRenderer();
        heroRenderer.goTo(animationPane.getPrefWidth()*0.5 - heroRenderer.getFitWidth()*0.65, null);

        Image[] images = ImageFactory.getSprites(ImageFactory.SPRITES_ID.HERO_HEAD);
        hudPane.getHeroStatBar().getAvatar().setImage(images[0]);
        hudPane.getHeroStatBar().getName().setText(heroName);

        hudPane.getHeroStatBar().getLifeBar().progressProperty().bind(hero.lifeRateProperty());
        hudPane.getHeroStatBar().getStamBar().progressProperty().bind(hero.stamRateProperty());
    }

    private void createMonster(EventHandler<ActionEvent> finishedHandler){
        zombie = new Zombie(Zombie.generateZombieType(), 0,0,0,0);

        zombieRenderer = animationPane.createZombieRenderer(Zombie.generateZombieType());
        zombieRenderer.goTo(animationPane.getPrefWidth()*0.5 - zombieRenderer.getBoundsInLocal().getWidth() * 0.15,
                finishedHandler);

        Image[] images = ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_HEAD);
        hudPane.getMonsterStatBar().getAvatar().setImage(images[0]);
        hudPane.getMonsterStatBar().getAvatar().setRotate(30);
        hudPane.getMonsterStatBar().getName().setText("Zombie");

        hudPane.getMonsterStatBar().getLifeBar().progressProperty().bind(zombie.lifeRateProperty());
        hudPane.getMonsterStatBar().getStamBar().progressProperty().bind(zombie.stamRateProperty());
    }

    private void createSkills(){
        skillBar = hudPane.getSkillBar();
        skillBar.setDisable(!heroCanPlay.getValue());
        skillBar.disableProperty().bind(heroCanPlay);

        Image[] images = ImageFactory.getSprites(ImageFactory.SPRITES_ID.ATTACK_SKILL);
        skillBar.getTrigger(0).setImage(images[0]);
        skillBar.getTrigger(0).addEventHandler(MouseEvent.MOUSE_CLICKED ,event -> heroAttack());
        //scene.setOnKeyReleased(keyEvent -> skillBar.process(keyEvent.getCode()));

        images = ImageFactory.getSprites(ImageFactory.SPRITES_ID.RECUPERATE_SKILL);
        skillBar.getTrigger(1).setImage(images[0]);
        skillBar.getTrigger(1).addEventHandler(MouseEvent.MOUSE_CLICKED ,event -> heroRecuperate());

        skillBar.getConsumableTrigger().setConsumable(hero.getConsumable());
        skillBar.getConsumableTrigger().setImage(CollectibleFactory.getImageFor(hero.getConsumable()));
        skillBar.getConsumableTrigger().addEventHandler(MouseEvent.MOUSE_CLICKED ,event -> heroConsume());
    }

    private void characterAttack(Character agressor, CharacterRenderer agressorR,
                                 Character target, CharacterRenderer targetR,
                                 EventHandler<ActionEvent> finishedHandler){
        int atk = 0;
        try{
            atk = agressor.attack();
        }catch(WeaponNullException e){
            if(agressor == hero) {
                hudPane.getMessagePane().showMessage("no weapon has been equipped");
            }
        }catch(WeaponBrokenException e){
            if(agressor == hero) {
                hudPane.getMessagePane().showMessage(agressor.getWeapon().getName() + " is broken");
            }
        }catch(StaminaEmptyException e){
            if(agressor == hero) {
                hudPane.getMessagePane().showMessage("no more stamina !");
            }
        }

        int finalAtk = atk;
        if(finalAtk != 0) {
            agressorR.attack(event -> {
                target.getHitWith(finalAtk);

                if (target.isAlive()) {
                    targetR.hurt(finishedHandler);
                } else {
                    targetR.die(finishedHandler);
                }
            });
        }
        else {agressorR.attack(finishedHandler);}
    }

    private void heroAttack(){
        heroCanPlay.setValue(!false);
        characterAttack(hero, heroRenderer, zombie, zombieRenderer,
                event -> finishTurn());
    }

    private void heroRecuperate(){
        heroCanPlay.setValue(!false);
        hero.recuperate();
        finishTurn();
    }

    private void heroConsume(){
        heroCanPlay.setValue((!false));
        try{
            hero.consume();
        }catch(ConsumeNullException e){
            hudPane.getMessagePane().showMessage("nothing to consume");
        }catch(ConsumeEmptyException e){
            hudPane.getMessagePane().showMessage(hero.getConsumable().getName() + " is empty");
        }catch(ConsumeRepairNullWeapon e){
            hudPane.getMessagePane().showMessage("no weapon equipped");
        }
        finishTurn();
    }

    private void monsterAttack(){
        characterAttack(zombie, zombieRenderer, hero, heroRenderer, event -> {
            if(hero.isAlive()) {heroCanPlay.setValue(!true);}
            else {gameOver();}
        });
    }

    private void finishTurn(){
        if(zombie.isAlive()) {monsterAttack();}
        else{
            animationPane.getChildren().removeAll(zombieRenderer);
            createMonster(event -> monsterAttack());
            score.setValue(score.getValue() + 1);
        }
    }

    private void gameOver(){
        hudPane.getMessagePane().showMessage("GAME OVER");
    }

    public static void main(String[] args){
        launch(args);
    }
}
