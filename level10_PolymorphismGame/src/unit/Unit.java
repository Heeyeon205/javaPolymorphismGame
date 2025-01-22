package unit;

import function.Combatable;
import function.State;

public abstract class Unit implements Combatable{
	public final int MAX_HP;
	private int hp;
	private int power;
	private String name;
	private State state = State.NOMAL;
	
	public Unit(String name, int hp, int power, State state) {
		super();
		this.name = name;
		this.hp = hp;
		this.power = power;
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
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public int getPower() {
		return power;
	}
	public int getMAX_HP() {
		return MAX_HP;
	}

	@Override
	public void attack(Unit target) {
		target.setHp(target.getHp()-power);
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.println(target.getName() + " 전투 불능!");
			
		}
	}
	
	public void printData() {
		System.out.printf("[%-3s] HP: %-4d/%-4d, ATK: %-2d\n", name, hp, MAX_HP, power);
	}
}
