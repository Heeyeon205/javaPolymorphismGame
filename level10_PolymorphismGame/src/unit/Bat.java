package unit;
import function.State;

public class Bat extends Enemy {

	public Bat(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void Skill(Unit target) {
		System.out.printf("%s의 흡혈 공격! (피해량: %d)\n", this.getName(), this.getPower());
		target.setHp(target.getHp()-this.getPower());
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.println(target.getName() + " 전투 불능!");
		}
		this.setHp(this.getHp()+(int)(this.getPower()*0.3));
		System.out.printf("%s는 흡혈로 HP %d 회복!\n", this.getName(), (int)(this.getPower()*0.3));
		if(this.getHp() > this.getMAX_HP()) {
			this.setHp(this.getMAX_HP());
		}
	}
}
