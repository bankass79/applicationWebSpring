package matchTree;

import java.util.LinkedList;
import java.util.Vector;

public class TableDeHachage {

	final static int M = 50000; // M correspond au nombre de "seaux" de la
								// table,
	// le nombre de case du tableau vector est fixé arbitrairement à 50000

	Vector<LinkedList<Quadruplet>> buckets;

	public TableDeHachage() {

		this.buckets = new Vector<LinkedList<Quadruplet>>(M);

		for (int i = 1; i < buckets.size(); i++) {

			this.buckets = new Vector<LinkedList<Quadruplet>>();

		}
	}

	// ici on calcule la valeur arbitraire du HacheCode
	public int hasCode(Ligne l1, Ligne l2, int hauteur) {

		int valHachage = 0;

		valHachage += (l1.hashCode() * l1.hashCode() + l2.hashCode() * l2.hashCode() + hauteur * hauteur);

		return valHachage;

	}

	// la valeur de hashCode(l1,l2,hauteur) modulo M.

	public int bucket(Ligne l1, Ligne l2, int hauteur) {

		int valHachageModulo = hasCode(l1, l2, hauteur) % M;

		return (valHachageModulo >= 0 ? valHachageModulo : valHachageModulo + M);

	}
 // permet d'ajouter une entrée dans la table de hachage
	public void ajouter(Ligne l1, Ligne l2, int hauteur, long resultat) {
		
		Quadruplet q = new Quadruplet(l1, l2, hauteur, resultat);
		
		long i = bucket(l1, l2, hauteur);
		
		this.buckets.get((int) i).push(q);

	}
	
	public Quadruplet trouver(Ligne l1, Ligne l2, int hauteur){
		
		int i = bucket(l1, l2, hauteur);
		
		for (Quadruplet q: this.buckets.get(i)){
			if (q.l1.equals(l1) && q.l2.equals(l2) && q.hauteur==hauteur);
			
			return q;
			
		}
				
				
		
		return null;
		
		
	}
	
}
