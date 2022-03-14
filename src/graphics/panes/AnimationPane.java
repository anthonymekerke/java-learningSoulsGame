package graphics.panes;

import characters.Zombie;
import graphics.widgets.characters.renderers.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AnimationPane extends Pane {

    private double groundY = 0.8 ;

    private AnchorPane parent ;

    /**
     * Crée un panneau d'animation qui occupe tout l'espace de son parent
     * @param parent
     */
    public AnimationPane(AnchorPane parent) {
        this.parent = parent ;
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        this.maxWidthProperty().bind(parent.maxWidthProperty());
        this.prefWidthProperty().bind(parent.widthProperty());
    }

    public HeroRenderer createHeroRenderer(){
        HeroRenderer hero = new HeroRenderer() ;

        this.getChildren().add(hero) ;

        double size = 480 ;
        hero.setFitHeight(size);
        hero.setFitWidth(size);
        hero.setX(-size);

        hero.yProperty().bind(this.heightProperty().multiply(groundY).subtract(hero.fitHeightProperty().multiply(0.76)));

        return hero ;
    }

    public CharacterRenderer createZombieRenderer(String type){
        CharacterRenderer monster;

        switch (type) {
            case Zombie.ZOMBIE_GIRL_TYPE:
                monster = new ZombieGirlRenderer();
                break;
            case Zombie.ZOMBIE_KID_TYPE:
                monster = new ZombieKidRenderer();
                break;
            case Zombie.ZOMBIE_PUNK_TYPE:
                monster = new ZombiePunkRenderer();
                break;
            case Zombie.ZOMBIE_DISMEMBER_TYPE:
                monster = new ZombieDismemberRenderer();
                break;
            default:
                monster = new ZombieRenderer();
                break;
        }

        double size = 400; //add possibility to change size of zombie ex: giant zombie kid
        monster.setFitHeight(size);
        monster.setFitWidth(size);
        this.getChildren().add(monster);
        monster.setX(this.getPrefWidth());

        monster.yProperty().bind(this.heightProperty().multiply(groundY).subtract(monster.fitHeightProperty()));

        return monster;
    }


    /**
     * Juste pour le fun : fait entrer un héro et un Zombie
     */
    public void startDemo(){
        HeroRenderer heroRenderer = createHeroRenderer() ;
        heroRenderer.goTo(this.getPrefWidth()*0.5 - heroRenderer.getFitWidth()*0.65, null);
        CharacterRenderer zombieRenderer = createZombieRenderer("vanilla") ;
        zombieRenderer.goTo(this.getPrefWidth()*0.5 - zombieRenderer.getBoundsInLocal().getWidth() * 0.15, null); ;
    }

    /**
     * Termine la démo (vide le pane)
     */
    public void endDemo(){
        this.getChildren().removeAll(getChildren()) ;
    }

}

