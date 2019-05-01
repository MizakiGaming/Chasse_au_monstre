package cam.utils.main;

import java.util.Scanner;

import cam.utils.cases.Case;
import cam.utils.cases.TrapCase;
import cam.utils.personnages.Chasseur;
import cam.utils.personnages.Monstre;
import cam.utils.plateau.Plateau;

public class Pieds {
	
	private static Scanner in = new Scanner(System.in);
	private static boolean finDuJeu = false;

	public static void main(String[] args) {
		Monstre monstre;
		Chasseur chasseur;
		Plateau plateau;
		int xPiege1, yPiege1;
		int xPiege2, yPiege2;
		int xPiege3, yPiege3;
		int xMonstre, yMonstre;
		String pseudoMonstre, pseudoChasseur;
		
		System.out.println("MONSTRE");
		System.out.println("Votre pseudo :");
		pseudoMonstre = in.nextLine();
		System.out.println("Ligne du monstre (0 ou 9) :");
		xMonstre = Integer.parseInt(in.nextLine());
		System.out.println("Colonne du monstre (0 ou 9) :");
		yMonstre = Integer.parseInt(in.nextLine());
		clearScreen();
		System.out.println("CHASSEUR");
		System.out.println("Votre pseudo :");
		pseudoChasseur = in.nextLine();
		System.out.println("1er piege :");
		System.out.println("Ligne (entre 1 et 8) :");
		xPiege1 = Integer.parseInt(in.nextLine());
		System.out.println("Colonne (entre 1 et 8) :");
		yPiege1 = Integer.parseInt(in.nextLine());
		System.out.println("2eme piege :");
		System.out.println("Ligne (entre 1 et 8) :");
		xPiege2 = Integer.parseInt(in.nextLine());
		System.out.println("Colonne (entre 1 et 8) :");
		yPiege2 = Integer.parseInt(in.nextLine());
		System.out.println("3eme piege :");
		System.out.println("Ligne (entre 1 et 8) :");
		xPiege3 = Integer.parseInt(in.nextLine());
		System.out.println("Colonne (entre 1 et 8) :");
		yPiege3 = Integer.parseInt(in.nextLine());
		clearScreen();
		
		monstre = new Monstre(xMonstre, yMonstre, pseudoMonstre);
		chasseur = new Chasseur(0, 0, pseudoChasseur);
		plateau = new Plateau(monstre, chasseur, initPlateau(10, 10));
		plateau.getPlateau()[xPiege1][yPiege1] = new TrapCase();
		plateau.getPlateau()[xPiege2][yPiege2] = new TrapCase();
		plateau.getPlateau()[xPiege3][yPiege3] = new TrapCase();
		
		while (!finDuJeu) {
			tourDuMonstre(plateau);
			clearScreen();
			tourDuChasseur(plateau, monstre, chasseur);
			clearScreen();
		}
	}
	
	public static void tourDuMonstre(Plateau plateau) {
		
		int nvX, nvY;
		boolean finTour = false;
		
		System.out.println("Tour du monstre");
		in.nextLine();
		while (!finTour) {
			plateau.printPlateau(true);
			System.out.println("Coordonnees de la nouvelle case :");
			System.out.println("Ligne :");
			nvX = Integer.parseInt(in.nextLine());
			System.out.println("Colonne :");
			nvY = Integer.parseInt(in.nextLine());
			finTour = plateau.deplacerMonstre(plateau.chercheCase(nvX, nvY));
		}
		if (plateau.sontToutesVisitée()) {
			finDuJeu = true;
		}
	}
	
	public static void tourDuChasseur(Plateau plateau, Monstre monstre, Chasseur chasseur) {
		int x, y;
		boolean finTour = false;
		
		System.out.println("Tour du chasseur");
		in.nextLine();
		while (!finTour) {
			do {
				plateau.printPlateau(false);
				System.out.println("Coordonnees de la case à chercher :");
				System.out.println("Ligne :");
				x = Integer.parseInt(in.nextLine());
				System.out.println("Colonne :");
				y = Integer.parseInt(in.nextLine());
			} while(!plateau.appartientAuPlateau(x, y));
			if (x == monstre.getPosition().getX() && y == monstre.getPosition().getY()) {
				PierreFeuilleCiseaux pfc = new PierreFeuilleCiseaux(monstre, chasseur);
				pfc.startGame();
				if (pfc.getWinner() instanceof Chasseur) {
					finDuJeu = true;
				}
			} else {
				System.out.println("Case visitée il y a " + plateau.getPlateau()[x][y].getTourVisited() + "tour(s)");
			}
			finTour = true;
		}
	}
	
	public static Case[][] initPlateau(int lig, int col) {
		Case[][] plateau = new Case[lig][col];
		double proba = Math.random();
		
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				if (proba < 0.07) {
					plateau[i][j] = new TrapCase();
				} else {
					plateau[i][j] = new Case();
				}
				proba = Math.random();
			}
		}
		return plateau;
	}
	
	private static void clearScreen() {
		for(int i=0 ; i<100 ; i++) {
			System.out.println("\n");
		}
	}
}