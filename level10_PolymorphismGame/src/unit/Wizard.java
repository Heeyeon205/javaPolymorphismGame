package unit;

import function.State;

public class Wizard extends Player{

	public Wizard(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void Skill(Unit target) {
		target.setHp(target.getHp()-(int)(this.getPower()*0.7));
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.printf("%s 전투 불능!\n", target.getName());
		}
	}
}
