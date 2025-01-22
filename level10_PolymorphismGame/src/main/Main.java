package main;

import manager.GameManager;

public class Main {

	public static void main(String[] args) {
		GameManager gm = new GameManager();
		boolean run = true;
		while(true) {
			run = gm.changeStage();
			if(run == false) {
				break;
			}
		}
		System.out.println("[게임 종료]");
	}

}
