package graphics;

import bags.Collectible;
import consumables.drinks.SmallStamPotion;
import consumables.food.SuperBerry;
import javafx.scene.image.Image;

public class CollectibleFactory {
    public static Image getImageFor(Collectible collectible) {
        if (collectible instanceof SmallStamPotion) {
            return ImageFactory.getSprites(ImageFactory.SPRITES_ID.SMALL_STAM_POTION)[0];
        }
        if (collectible instanceof SuperBerry) {
            return ImageFactory.getSprites(ImageFactory.SPRITES_ID.SUPER_BERRY)[0];
        }

        return null;
    }
}
