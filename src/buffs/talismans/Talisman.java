package buffs.talismans;

import java.util.Calendar;

import buffs.BuffItem;

public class Talisman extends BuffItem {
	
	private float buff ;
	private int start, end ; 
	
	public Talisman(String name, float buff, int start, int end) {
		super(name) ;
		this.buff = buff ;
		this.start = start ;
		this.end = end ;
	}

	/*
	Valeur du buff: définie dans les sous-classe
	fonctionnement: Les Talismans ont des buffs qui ne fonctionnent qu'a certains horaires,
		ceux-ci sont définies dans les sous-classes de Talisman lors de l'instanciation.
	Cible: Monster
	*/
	@Override
	public float computeBuffValue() {
		int now = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) ;
		if(start <= end){
			if(now >= start && now < end) return buff ;
			else return 0f ;
		}else{
			if( (now <= 23 && now >= start) || (now >=0 && now < end)) return buff ;
			else return 0f ;
		}
	}
	
}
