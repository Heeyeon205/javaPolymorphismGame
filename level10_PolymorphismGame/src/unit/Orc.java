package unit;

import function.State;

public class Orc extends Enemy {

	public Orc(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void skill(Unit target) {	// 공격력 2배 데미지 + 기절 부여
		int strike = this.getPower()*2;
		target.setHp(target.getHp()-strike);
		System.out.printf("%s가 %s를 %d 데미지로 기절 공격!\n", this.getName(), target.getName(), strike);
		target.setState(State.STUN);
		System.out.printf("%s는 1턴 동안 Stun 상태!\n", target.getName());
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.println(target.getName() + " 전투 불능!");
		}
	}
}
