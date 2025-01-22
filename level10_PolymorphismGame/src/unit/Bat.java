package unit;
import function.State;

public class Bat extends Enemy {

	public Bat(String name, int hp, int power, State state) {
		super(name, hp, power, state);
	}

	@Override
	public void skill(Unit target) {	// 공격력의 30% 흡혈
		System.out.printf("%s가 %s를 %d 데미지로 흡혈 공격!\n", this.getName(), target.getName(), this.getPower());
		target.setHp(target.getHp()-this.getPower());
		if(target.getHp() <= 0) {
			target.setHp(0);
			target.setState(State.DEATH);
			System.out.println(target.getName() + " 전투 불능!");
		}
		this.setHp(this.getHp()+(int)(this.getPower()*0.3));
		System.out.printf("%s는 흡혈로 HP %d 회복! (HP: %d/%d)\n", this.getName(), (int)(this.getPower()*0.3), this.getHp(), this.getMAX_HP());
		if(this.getHp() > this.getMAX_HP()) {
			this.setHp(this.getMAX_HP());
		}
	}
}
