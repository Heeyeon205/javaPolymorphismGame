package stage;

import function.Stages;
import manager.GameManager;

public class StageTitle extends Stage {

	@Override
	public boolean update() {
		System.out.println("==========[Title Menu]==========");
		while(true) {
		String start = GameManager.getStirng("게임을 시작하려면 [시작]을 입력하세요.");
		if (start.equals("시작")) {
			GameManager.nextStage = Stages.LOBBY;
			return false;
		}else {
			continue;
		}
		}
	}

	@Override
	public void init() {}
}
