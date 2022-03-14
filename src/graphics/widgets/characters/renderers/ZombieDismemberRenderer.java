package graphics.widgets.characters.renderers;

import javafx.scene.image.Image;
import graphics.ImageFactory;

public class ZombieDismemberRenderer extends CharacterRenderer {

    public ZombieDismemberRenderer() {
        super();
        double rate = 0.5 ;
        getTimeline().setRate(rate);
    }

    @Override
    protected Image[] loadAttackSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_DISMEMBER_ATTACK_ANIMATION) ;
    }

    @Override
    protected Image[] loadHurtSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_DISMEMBER_HURT_ANIMATION) ;
    }

    @Override
    protected Image[] loadDieSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_DISMEMBER_DIE_ANIMATION) ;
    }

    @Override
    protected Image[] loadIdleSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_DISMEMBER_IDLE_ANIMATION) ;
    }

    @Override
    protected Image[] loadWalkSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_DISMEMBER_WALK_ANIMATION) ;
    }

}
