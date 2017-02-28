package matchTree;

import java.util.LinkedList;
import java.util.Vector;

public class CompteGrilles {

	public LinkedList<Ligne> toutesLesLignes;
	
	private TableDeHachage memo;

	public CompteGrilles(int largeur) {

		Vector<LinkedList<Ligne>> lignes = new Vector<LinkedList<Ligne>>(largeur + 1);

		for (int i = 0; i < largeur + 1; ++i) {
			lignes.add(new LinkedList<Ligne>());

		}

		lignes.get(0).add(new Ligne(new int[] {}));

		for (int i = 1; i < largeur + 1; i++) {

			for (Ligne l : lignes.get(i - 1)) {

				for (int j = 0; j < 2; ++j) {

					Ligne nouvelleLigne = l.ajouteFruit(j);
					if (nouvelleLigne.estValide()) {

						lignes.get(i).add(nouvelleLigne);
					}
				}
			}

		}
		toutesLesLignes = lignes.get(largeur);
		
		this.memo = new TableDeHachage();

	}

	public long compte(Ligne l1, Ligne l2, int hauteur) {

		if (hauteur <= 1) {

			return 0;
		}

		if (hauteur == 2) {

			return 1;
		} else {
			 long resultat = 0;
			for (Ligne l3 : toutesLesLignes) {

				if (l3.sontEmpilables(l1, l2)) {

					resultat =  compte(l2, l3, hauteur - 1);

				}

			}
				memo.ajouter(l1, l2, hauteur, resultat);
			return resultat;

		}
	}

	public long compte(int hauteur) {
		
	 long resultat2=0;
		 
	 switch (hauteur){
	 case 0 : return  0;
	 case 1: return toutesLesLignes.size();
	 
	 }
	 
	 for (Ligne l1 : toutesLesLignes){
		 
		 for(Ligne l2: toutesLesLignes){
			 
			 resultat2 += compte (l1, l2, hauteur);
		 }
		 
		 
	 }
		return resultat2;
		
	}

}
