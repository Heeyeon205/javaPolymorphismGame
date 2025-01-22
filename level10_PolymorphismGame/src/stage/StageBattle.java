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
		if (attacker.getHp() <= 0 || attacker.getState().equals(State.STUN) || attacker.getState().equals(State.SILENCE)) {
			return;
		}
		
		Enemy target = selectTarget(um.geteList());
		System.out.printf("[%s] [1] 공격 [2] 스킬", um.getpList().get(index).getName());
		int sel = GameManager.getInt("", 1, 3);
		
		if (sel == 1) {
			um.getpList().get(index).attack(target);
			System.out.printf("%s가 %s를 공격! (피해량: %d)\n", attacker.getName(), target.getName(), attacker.getPower());
			
		} else if (sel == 2) {
			if(attacker.getName().equals("전사")) {
				attacker.Skill(target);
				System.out.printf("%s가 %s를 공격! (피해량: %d)\n", attacker.getName(), target.getName(), attacker.getPower()*2);
			}else if(attacker.getName().equals("마법사")) {
				System.out.printf("%s의 광역 마법!\n", attacker.getName());
				for(int i = 0; i < um.geteList().size(); i++) {
					if(um.geteList().get(i).getHp() > 0) {
					attacker.Skill(um.geteList().get(i));
					System.out.printf("%s가 %s를 공격! (피해량: %d)\n", attacker.getName(), um.geteList().get(i).getName(), (int)(attacker.getPower()*0.7));
					}
				}
			}else if(attacker.getName().equals("힐러")) {
				int heal = GameManager.getInt("회복할 아군을 선택하세요: [1] 전사 [2] 마법사\n", 1, 3);
				attacker.Skill(um.getpList().get(heal-1));
			}
		}
		printUnit();
	}

	private void enemyAtk(int index) {
		Enemy attacker = um.geteList().get(index);
		if (attacker.getHp() <= 0) {
			System.out.println(attacker.getName() + "는 전투 불능 상태입니다.");
			return;
		}
		Player target = selectTarget(um.getpList());
		attacker.attack(target);
		System.out.printf("%s가 %s를 공격했습니다! (피해량: %d)\n", attacker.getName(), target.getName(), attacker.getPower());
	}

	private <T extends Unit> boolean isAllDead(ArrayList<T> units) {
		for (T unit : units) {
			if (unit.getHp() > 0) {
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

	@Override
	public boolean update() {
		init();
		while (true) {
			printUnit();
			
			for (int i = 0; i < um.getpList().size(); i++) {
				if (um.getpList().get(i).getHp() > 0) {
					playerAtk(i);
				}
			}
			
			if (isAllDead(um.geteList())) {
				System.out.println("[Player Win] 적을 모두 물리쳤습니다!");
				int sel = GameManager.getInt("[1] 이어서 진행 [2] 게임 종료", 1, 3);
				if (sel == 1) {
					init();
					continue;
				} else if (sel == 2) {
					GameManager.nextStage = Stages.STAND_BY;
					return false;
				}
			}
			
			for (int i = 0; i < um.getpList().size(); i++) {
				if (um.getpList().get(i).getState().equals(State.STUN) || um.getpList().get(i).getState().equals(State.SILENCE)) {
					um.getpList().get(i).setState(State.NOMAL);
					System.out.printf("%s 상태이상 회복!\n", um.getpList().get(i).getName());
				}
			}
			
			int skill = GameManager.getRandom(4);
			for (int i = 0; i < um.geteList().size(); i++) {
				if (um.geteList().get(i).getHp() > 0) {
					if(skill == 0) {
						if(um.geteList().get(i).getName().equals("오크")){
							um.geteList().get(i).Skill(um.getpList().get(i));
						}else if(um.geteList().get(i).getName().equals("박쥐")) {
							um.geteList().get(i).Skill(um.getpList().get(i));
						}else if(um.geteList().get(i).getName().equals("늑대")) {
							for(int j = 0; i < um.getpList().size(); i++) {
								System.out.println("늑대의 표호!");
								um.geteList().get(i).Skill(um.getpList().get(j));
							}
						}
					}else {
						enemyAtk(i);
					}
				}
			}
			
			if (isAllDead(um.getpList())) {
				System.out.println("[Game Over] 모든 영웅 사망!");
				GameManager.nextStage = Stages.STAND_BY;
				return false;
			}
		}
	}

}
