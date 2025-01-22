package unit;

import function.State;

public class Wolf extends Enemy {

	public Wolf(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void Skill(Unit target) { // 공격력의 50% 적 전체 데미지
		target.setHp(target.getHp() - (int) (this.getPower() * 0.5));
		System.out.printf("%s가 %s를 %d의 데미지로 공격!\n", this.getName(), target.getName(), (int) (this.getPower() * 0.5));
		if (target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.printf("%s는 %s의 공격으로 전투 불능!\n", target.getName(), this.getName());
		}
	}
}
