package graphics.widgets.characters.renderers;

import javafx.scene.image.Image;
import graphics.ImageFactory;

public class ZombieKidRenderer extends CharacterRenderer {

    public ZombieKidRenderer() {
        super();
        double rate = 0.5 ;
        getTimeline().setRate(rate);
    }

    @Override
    protected Image[] loadAttackSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_KID_ATTACK_ANIMATION) ;
    }

    @Override
    protected Image[] loadHurtSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_KID_HURT_ANIMATION) ;
    }

    @Override
    protected Image[] loadDieSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_KID_DIE_ANIMATION) ;
    }

    @Override
    protected Image[] loadIdleSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_KID_IDLE_ANIMATION) ;
    }

    @Override
    protected Image[] loadWalkSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_KID_WALK_ANIMATION) ;
    }

}
