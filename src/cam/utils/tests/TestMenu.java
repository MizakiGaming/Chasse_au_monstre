package cam.utils.tests;

import cam.utils.main.Menu;

public class TestMenu {

	public static void main(String[] args) {
		Menu.menu();
		System.out.println(Menu.getChoixMenu());
		System.out.println(Menu.getNomChasseur());
		System.out.println(Menu.getNomMonstre());

	}

}