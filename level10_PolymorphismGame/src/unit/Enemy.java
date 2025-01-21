package unit;

import java.awt.Taskbar.State;

public abstract class Enemy extends Unit {

	public Enemy(int hp, int power, String name, State state) {
		super(hp, power, name, state);
	}
}
