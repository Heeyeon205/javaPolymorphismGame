package unit;

import function.State;

public class Wolf extends Enemy {

	public Wolf(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void Skill(Unit unit) {
			unit.setHp(unit.getHp()-(int)(this.getPower()*0.5));
			System.out.printf("%s가 %s를 공격했습니다! (피해량: %d)\n", this.getName(), unit.getName(), (int)(this.getPower()*0.5));
			if(unit.getHp() <= 0) {
				unit.setHp(0);
				unit.setState(State.DEATH);
				System.out.printf("%s는 %s의 공격으로 전투 불능!\n", unit.getName(), this.getName());
			}
	}
}
