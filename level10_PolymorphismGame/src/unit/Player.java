package unit;

import java.awt.Taskbar.State;

public abstract class Player extends Unit {

	public Player(int hp, int power, String name, State state) {
		super(hp, power, name, state);
	}
}
