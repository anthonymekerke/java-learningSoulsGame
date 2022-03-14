package buffs.rings;

import armor.ArmorItem;
import armor.DragonSlayerLeggings;

public class DragonSlayerRing extends Ring {
	
	public DragonSlayerRing() {
		super("Dragon Slayer Ring", 14) ;
	}

	/*
	Valeur du buff: 14
	Fonctionnement: Le buff est actif uniquement si le Hero
		à l'item d'armure DragonSlayerLeggings équipé.
	Cible: Hero
	 */
	@Override
	public float computeBuffValue() {
		if(hero != null && hasDragonsSlayerItem()){
			return power ;
		}else return 0 ;
	}

	private boolean hasDragonsSlayerItem(){
		ArmorItem[] items = hero.getArmorItems() ;
		for(ArmorItem item: items){
			if(item instanceof DragonSlayerLeggings) return true ; 
		}
		return false ;
	}
	
}
