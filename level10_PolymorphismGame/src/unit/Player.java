package unit;

import function.State;

public abstract class Player extends Unit {

	public Player(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}
}
