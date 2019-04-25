package cam.utils.plateau;

import java.util.ArrayList;
import java.util.List;

import cam.utils.cases.Case;
import cam.utils.personnages.Chasseur;
import cam.utils.personnages.Monstre;

public class Plateau {
	/**
	 * Classe qui détermine le fonctionnement du plateau de jeu
	 * @author Hugo Wieder
	 */
	 
	// ATTRIBUTS
	private Monstre monstre;
	private Chasseur chasseur;
	private Case[][] plateau;
	
	// CONSTRUCTEURS
	/**
	 * Constructeur d'une instance de Plateau
	 * @param monstre
	 * @param chasseur
	 * @param nbLignes
	 * @param nbColonnes
	 */
	public Plateau(Monstre monstre, Chasseur chasseur, int nbLignes, int nbColonnes) {
		this.monstre = monstre;
		this.chasseur = chasseur;
		this.plateau = new Case[nbLignes][nbColonnes];
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				plateau[i][j] = new Case();
			}
		}
	}
	/**
	 * Constructeur d'une instance de Plateau
	 * @param monstre
	 * @param chasseur
	 * @param unPlateau
	 */
	public Plateau(Monstre monstre, Chasseur chasseur, Case[][] unPlateau) {
		this.monstre = monstre;
		this.chasseur = chasseur;
		this.plateau = unPlateau;
	}
	
	public Case[][] getPlateau() {
		return plateau;
	}
	
	// METHODES
	/**
	 * Renvoie la liste de toutes les cases où le déplacement est possible en partant de la case mise en paramètre
	 * @param caseDeDepart
	 * @return uneListe
	 */
	public List<Case> deplacementsPossible(){
		Case caseDeDepart = chercheCase(this.monstre.getPosition().getX(), this.monstre.getPosition().getY());
		List<Case> res = new ArrayList<Case>();
		int[] coordonneesCase = this.chercheCase(caseDeDepart);
		if (coordonneesCase == null) return null;
		int i = coordonneesCase[0], j = coordonneesCase[1];
		
		res.addAll(deplacementDiagonale(i, j));
		res.addAll(deplacementVertical(i,j));
		res.addAll(deplacementHorizontal(i, j));
		return res;
	}
	/**
	 * 
	 * @param i
	 * @param j
	 * @return Renvoie tous les deplacements diagonnaux possibles
	 */
	private List<Case> deplacementDiagonale(int i, int j){
		List<Case> maListe = new ArrayList<Case>();
		
		return maListe;
	}
	/**7
	 * 
	 * @param i
	 * @param j
	 * @return Renvoie tous les deplacements Verticaux possibles
	 */
	private List<Case> deplacementVertical(int i, int j){
		List<Case> maListe = new ArrayList<Case>();
		
		return maListe;
	}
	/**
	 * 
	 * @param i
	 * @param j
	 * @return Renvoie tous les deplacements horizontaux possibles
	 */
	private List<Case> deplacementHorizontal(int i, int j){
		List<Case> maListe = new ArrayList<Case>();
		
		
		return maListe;
	}
	
	/**
	 * Methode privee permettant de renvoyer les coordonnees de la case mise en parametre
	 * @exception Renvoie null si la case n'appartient pas au plateau
	 * @param uneCase
	 * @return coordonnees
	 */
	private int[] chercheCase(Case uneCase) {
		for (int i = 0; i<this.plateau.length; i++) {
			for (int j=0; j<this.plateau[0].length; i++) {
				if(this.plateau[i][j] == uneCase) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
	
	/**
	 * Methode privee permettant de renvoyer la case de coordonees rentrees en parametre
	 * @exception renvoie null si les coordonnees ne sont pas valides(n'appartiennent pas au plateau)
	 * @param i
	 * @param j
	 * @return
	 */
	private Case chercheCase(int i, int j) {
		if((i<this.plateau.length && j<this.plateau[0].length) && (i>=0 && j>=0)) {
			return this.plateau[i][j];
		}
		return null;
	}
	
	public void printPlateau() {
		char[][] plateau = new char[this.plateau.length][this.plateau[0].length];
		
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[0].length; j++) {
				if(this.plateau[i][j].isVisited()) {
					plateau[i][j] = 'V';
				}
				else {
					plateau[i][j] = ' ';
				}
			}
		}
		
		for(Case cas : ) {
			int[] coord = chercheCase(cas);
			plateau[coord[0]][coord[1]] = 'P';
		}
		
		plateau[monstre.getPosition().getX()][monstre.getPosition().getY()] = 'M';
		
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[0].length; j++) {
				System.out.print("| " + plateau[i][j] + " |");
			}
			System.out.println();
		}
	}
}
