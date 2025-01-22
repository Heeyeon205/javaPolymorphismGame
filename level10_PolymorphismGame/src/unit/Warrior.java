package unit;

import function.State;

public class Warrior extends Player {

	public Warrior(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void Skill(Unit target) {
		System.out.printf("%s의 강공격!\n", this.getName());
		target.setHp(target.getHp()-this.getPower()*2);
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.println(target.getName() + " 전투 불능!");
			
		}
	}
}
