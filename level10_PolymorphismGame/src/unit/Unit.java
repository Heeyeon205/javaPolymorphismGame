package unit;

import funtion.Combatable;
import funtion.State;
import manager.UnitManager;


public abstract class Unit implements Combatable{
	public final int MAX_HP;
	private int hp;
	private int power;
	private String name;
	private State state = State.NOMAL;
	private UnitManager uMgr;
	
	public Unit(String name, int hp, int power, State state) {
		super();
		this.name = name;
		this.hp = hp;
		this.power = power;
		this.state = state;
		MAX_HP = hp;
//		uMgr = uMgr.getInstance();
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

	@Override
	public void attack(Unit target) {
		target.setHp(target.getHp()-power);
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(state.DEATH);
			System.out.println(target.getName() + " 전투 불능!");
			
		}
	}
	
	public void printData() {
		System.out.printf("[%s] HP: %d/%d, ATK: %d\n", name, hp, MAX_HP, power);
	}
}
