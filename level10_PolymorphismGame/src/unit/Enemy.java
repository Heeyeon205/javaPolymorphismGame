package unit;

import function.State;

public abstract class Enemy extends Unit {

	public Enemy(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}
}
