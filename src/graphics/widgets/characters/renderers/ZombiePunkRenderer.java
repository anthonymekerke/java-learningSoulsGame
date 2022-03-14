package graphics.widgets.characters.renderers;

import javafx.scene.image.Image;
import graphics.ImageFactory;

public class ZombiePunkRenderer extends CharacterRenderer {

    public ZombiePunkRenderer() {
        super();
        double rate = 0.5 ;
        getTimeline().setRate(rate);
    }

    @Override
    protected Image[] loadAttackSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_PUNK_ATTACK_ANIMATION) ;
    }

    @Override
    protected Image[] loadHurtSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_PUNK_HURT_ANIMATION) ;
    }

    @Override
    protected Image[] loadDieSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_PUNK_DIE_ANIMATION) ;
    }

    @Override
    protected Image[] loadIdleSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_PUNK_IDLE_ANIMATION) ;
    }

    @Override
    protected Image[] loadWalkSprites(){
        return ImageFactory.getSprites(ImageFactory.SPRITES_ID.ZOMBIE_PUNK_WALK_ANIMATION) ;
    }

}
