package buffs.rings;

import characters.Hero;

public class RingOfDeath extends Ring{
	
	private static float LIMIT = 0.5f ; 

	public RingOfDeath() {
		super("Ring of Death", 10000) ;
	}

	/*
	Valeur du buff: 10000
	Fonctionnement: Le buff est actif uniquement si le Hero
		a des HP inférieurs à 50% de sa vie max.
	Cible: Hero
	 */
	@Override
	public float computeBuffValue() {
		if (hero != null){
			float gauge = hero.getLife() / (float)hero.getMaxLife();
			if(gauge <= LIMIT) return power ;
			else return 0f ;
		}else return 0f ;
	}
	
	/**
	 * Un test...
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		Hero hero = new Hero() ;
		Ring r = new RingOfDeath() ;
		hero.setRing(r, 1);
		hero.getHitWith(60); // pour abaisser les PV du hero
		hero.printStats();
	}
	
}
