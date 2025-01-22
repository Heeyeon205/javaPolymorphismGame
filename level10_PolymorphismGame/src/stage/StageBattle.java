package stage;

import java.util.ArrayList;

import function.Stages;
import function.State;
import manager.GameManager;
import manager.UnitManager;
import unit.Enemy;
import unit.Player;
import unit.Unit;

public class StageBattle extends Stage {
	UnitManager um;

	public void init() {
		um = new UnitManager();
	}

	private void printUnit() {
		System.out.println("\n===========[Player]===========");
		for (Player p : um.getpList()) {
			p.printData();
		}
		System.out.println("\n===========[Enemy]===========");
		for (Enemy e : um.geteList()) {
			e.printData();
		}
		System.out.println();
	}

	private void playerAtk(int index) {
		Player attacker = um.getpList().get(index);
		if (attacker.getHp() <= 0 || attacker.getState().equals(State.STUN)) {
			return;
		}
		Enemy target = selectTarget(um.geteList());
		System.out.printf("%s: [1] 공격 [2] 스킬", um.getpList().get(index).getName());
		int sel = GameManager.getInt("", 1, 3);
		if (sel == 1) {
			um.getpList().get(index).attack(target);
		} else if (sel == 2) {
			if (attacker.getName().equals("전사")) {
				attacker.skill(target);
			} else if (attacker.getName().equals("마법사")) {
				System.out.printf("%s의 광역 마법!\n", attacker.getName());
				for (int i = 0; i < um.geteList().size(); i++) {
					if (um.geteList().get(i).getHp() > 0) {
						attacker.skill(um.geteList().get(i));
					}
				}
			} else if (attacker.getName().equals("힐러")) {
				int heal = GameManager.getInt("회복할 아군을 선택하세요: [1] 전사 [2] 마법사\n", 1, 3);
				attacker.skill(um.getpList().get(heal - 1));
			}
		}
		printUnit();
	}

	private void enemyAtk(int index) {
		Enemy attacker = um.geteList().get(index);
		if (attacker.getHp() <= 0) {
			return;
		}
		Player target = selectTarget(um.getpList());
		attacker.attack(target);
	}

	private <T extends Unit> boolean isAllDead(ArrayList<T> units) {
		for (T unit : units) {
			if (unit.getState().equals(State.NOMAL)) {
				return false;
			}
		}
		return true;
	}

	private <T extends Unit> T selectTarget(ArrayList<T> unit) {
		while (true) {
			int idx = GameManager.getRandom(unit.size());
			if (unit.get(idx).getHp() > 0) {
				return unit.get(idx);
			}
		}
	}

	private int randomTarget() {
		while (true) {
			while (true) {
				int idx = GameManager.getRandom(um.getpList().size());
				if (um.getpList().get(idx).getState().equals(State.DEATH)) {
					continue;
				}
				return idx;
			}
		}
	}

	private void crowdControlClear() {
		for (int i = 0; i < um.getpList().size(); i++) {
			if (um.getpList().get(i).getState().equals(State.STUN)) {
				um.getpList().get(i).setState(State.NOMAL);
				System.out.printf("%s 상태이상 회복!\n", um.getpList().get(i).getName());
			}
		}
	}

	private void playerTurn() {
		for (int i = 0; i < um.getpList().size(); i++) {
			if (um.getpList().get(i).getState().equals(State.NOMAL)) {
				playerAtk(i);
			}
		}
	}

	private void enemyTurn() {
		for (int i = 0; i < um.geteList().size(); i++) {
			Unit attacker = um.geteList().get(i);
			int skill = GameManager.getRandom(4);
			if (attacker.getHp() > 0) {
				if (skill == 0) {
					int idx = randomTarget();
					if (attacker.getName().equals("오크")) {
						attacker.skill(um.getpList().get(idx));
					} else if (attacker.getName().equals("박쥐")) {
						attacker.skill(um.getpList().get(idx));
					} else if (attacker.getName().equals("늑대")) {
						System.out.println("늑대의 표호!");
						for (int j = 0; j < um.getpList().size(); j++) {
							attacker.skill(um.getpList().get(j));
						}
					}
				} else {
					enemyAtk(i);
				}
			}
		}
	}

	@Override
	public boolean update() {
		init();
		while (true) {
			printUnit();
			playerTurn();
			if (isAllDead(um.geteList())) {
				System.out.println("[Player Win] 적을 모두 물리쳤습니다!");
				int sel = GameManager.getInt("[1] 새로운 게임 [2] 게임 종료", 1, 3);
				if (sel == 1) {
					init();
					continue;
				} else if (sel == 2) {
					GameManager.nextStage = Stages.STAND_BY;
					return false;
				}
			}
			crowdControlClear();
			enemyTurn();
			if (isAllDead(um.getpList())) {
				System.out.println("[Game Over] 모든 영웅 사망!");
				GameManager.nextStage = Stages.STAND_BY;
				return false;
			}
		}
	}
}
