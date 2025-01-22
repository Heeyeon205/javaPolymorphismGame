package manager;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import function.Stages;
import stage.Stage;
import stage.StageBattle;
import stage.StageLobby;
import stage.StageTitle;

public class GameManager {
	private static Scanner sc = new Scanner(System.in);
	private static Random rd = new Random();
	public static Stages curStage;
	public static Stages nextStage;
	private Map<Stages, Stage> sList = new HashMap<Stages, Stage>();

	public GameManager() {
		sList.put(Stages.TITLE, new StageTitle());
		sList.put(Stages.LOBBY, new StageLobby());
		sList.put(Stages.BATTLE, new StageBattle());
		curStage = Stages.STAND_BY;
		nextStage = Stages.TITLE;
	}

	public static String getStirng(String msg) {
		System.out.println(msg);
		return sc.nextLine();
	}

	public static int getInt(String msg, int min, int max) {
		while (true) {
			try {
				System.out.println(msg);
				int num = sc.nextInt();
				sc.nextLine();
				if (num < min || num >= max) {
					System.out.println("입력 범위 오류입니다.");
					continue;
				}
				return num;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력 가능합니다.");
				sc.nextLine();
				continue;
			}
		}
	}

	public static int getRandom(int size) {
		return rd.nextInt(size);
	}

	public boolean changeStage() {
		System.out.println("-----[" + curStage + "] => [" + nextStage + "]-----\n");
		if (curStage.equals(nextStage)) {
			return true;
		}
		curStage = nextStage;
		Stage stage = sList.get(curStage);
		boolean run = true;
		while (true) {
			run = stage.update();
			if (run == false) {
				break;
			}
		}
		if (nextStage.equals(Stages.STAND_BY)) {
			return false;
		} else {
			return true;
		}
	}
}
