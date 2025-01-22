package stage;

import function.Stages;
import manager.GameManager;

public class StageLobby extends Stage {

	@Override
	public boolean update() {
		System.out.println("==========[Lobby Menu]==========");
		int sel = GameManager.getInt("[1] 전투 시작 [2] 게임 종료", 0, 3);
		if(sel == 1) {
			GameManager.nextStage = Stages.BATTLE;
			return false;
		}else if(sel == 2) {
			GameManager.nextStage = Stages.STAND_BY;
			return false;
		}
		return true;
	}

	@Override
	public void init() {
		
	}
}
