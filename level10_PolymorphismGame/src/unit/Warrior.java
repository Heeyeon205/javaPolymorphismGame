package unit;

import function.State;

public class Warrior extends Player {

	public Warrior(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void skill(Unit target) {	// 공격력 2배 데미지
		target.setHp(target.getHp()-this.getPower()*2);
		System.out.printf("%s가 %s를 %d의 데미지로 강공격!\n", this.getName(), target.getName(), this.getPower()*2);
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.println(target.getName() + " 전투 불능!");
			
		}
	}
}
