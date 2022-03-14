package buffs.rings;

import characters.Hero;
import weapons.Sword;

public class RingOfSwords extends Ring{
	
	public RingOfSwords() {
		super("Ring of Swords", 10) ;
	}

	/*
	Valeur du buff: 10
	Fonctionnement: Le buff est actif uniquement si le Hero est equipé d'une Epee (classe Sword)
	Cible: Hero
	 */
	@Override
	public float computeBuffValue() {
		if (hero != null && (hero.getWeapon() instanceof Sword) )  return power ;
		else return 0f ;
		
	}
	
	/**
	 * Un test...
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		Hero hero = new Hero() ;
		RingOfSwords r = new RingOfSwords() ;
		hero.setRing(r, 1);
		hero.setWeapon(new Sword());
		System.out.println(r);
	}
}
