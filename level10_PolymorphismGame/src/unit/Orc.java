package unit;

import function.State;

public class Orc extends Enemy {

	public Orc(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void Skill(Unit target) {
		System.out.printf("%s의 기절 공격! (피해량: %d)\n", this.getName(), this.getPower()*2);
		target.setHp(target.getHp()-this.getPower()*2);
		target.setState(State.STUN);
		System.out.printf("%s는 1턴 동안 Stun 상태!\n", target.getName());
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.println(target.getName() + " 전투 불능!");
			
		}
	}
}
