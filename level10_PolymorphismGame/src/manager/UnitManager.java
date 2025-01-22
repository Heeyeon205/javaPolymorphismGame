package manager;

import java.util.ArrayList;
import function.State;
import unit.Bat;
import unit.Enemy;
import unit.Healer;
import unit.Orc;
import unit.Player;
import unit.Warrior;
import unit.Wizard;
import unit.Wolf;

public class UnitManager {
	private ArrayList<Player> pList = new ArrayList<>();
	private ArrayList<Enemy> eList = new ArrayList<>();

	public ArrayList<Player> getpList() {
		return pList;
	}
	public ArrayList<Enemy> geteList() {
		return eList;
	}

	public UnitManager() {
		pList.add(new Warrior("전사", 800, 50, State.NOMAL));
		pList.add(new Wizard("마법사", 600, 70, State.NOMAL));
		pList.add(new Healer("힐러", 400, 30, State.NOMAL));

		eList.add(new Bat("박쥐", 300, 20, State.NOMAL));
		eList.add(new Orc("오크", 500, 30, State.NOMAL));
		eList.add(new Wolf("늑대", 350, 35, State.NOMAL));
	}
}
