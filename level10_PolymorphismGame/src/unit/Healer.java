package unit;

import function.State;

public class Healer extends Player {

	public Healer(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void Skill(Unit target) {
			target.setHp(target.getHp()+this.getPower());
			if(target.getHp() > target.MAX_HP) {
				target.setHp(target.MAX_HP);
			System.out.printf("%s가 %s를 %d HP 회복!\n", this.getName(), target.getName(), this.getPower());
			System.out.printf("[%s] HP: %d/%d\n", target.getName(), target.getHp(), target.getMAX_HP());
		}
	}
}
