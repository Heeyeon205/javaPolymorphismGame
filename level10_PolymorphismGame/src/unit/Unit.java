package unit;

import java.awt.Taskbar.State;

import funtion.Skillable;

public abstract class Unit implements Skillable{
	public final int MAX_HP;
	private int hp;
	private int power;
	private String name;
	private State state = State.NORMAL;
	
	public Unit(int hp, int power, String name, State state) {
		super();
		this.hp = hp;
		this.power = power;
		this.name = name;
		this.state = state;
		MAX_HP = hp;
	}

	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getName() {
		return name;
	}
}
