package buffs;

import bags.Collectible;

import java.util.Locale;

public abstract class BuffItem implements Collectible {
	public static final String BUFF_STAT_STRING = "buff";
	
	private String name ;
	private int weight;
	
	public BuffItem(String name) {
		this.name = name ;
		this.weight = 1;
	}
	
	public abstract float computeBuffValue() ;
	
	public String getName() {
		return name;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return String.format(Locale.US, "[%s, %.2f]", getName(), computeBuffValue()) ;
	}
	
}
