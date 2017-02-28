package matchTestTest;

import matchTree.Ligne;
import matchTree.Quadruplet;
import matchTree.TableDeHachage;

public class TableDeHachageTest {
	
	public static void main(String[] args) {
		//Pour s'assurer que les assert's sont activés
		if (!TableDeHachageTest.class.desiredAssertionStatus()) {
			System.err.println("Vous devez activer l'option -ea de la JVM");
		        System.err.println("(Run As -> Run configurations -> Arguments -> VM Arguments)");
			System.exit(1);
		}

		System.out.print("tester les methodes ajouter et trouver... ");

		TableDeHachage t = new TableDeHachage();

		Ligne l1 = new Ligne(new int[]{0,0,1,0,1});
		Ligne l2 = new Ligne(new int[]{1,0,1,0,1});
		Ligne l3 = new Ligne(new int[]{0,0,1,1,0});
		Ligne l4 = new Ligne(new int[]{0,1,1,1,0});

		Quadruplet q;

		q = t.trouver(l1, l2, 2);
		assert (q == null) : "\ntrouve une entree dans une table vide !";

		t.ajouter(l1, l2, 2, 23);
		t.ajouter(l3, l4, 3, 42);

		q = t.trouver(l1, l2, 2);
		assert (q != null && q.resultat == 23) : "\nentree existante non trouvee dans la table !";
		q = t.trouver(l3, l4, 3);
		assert (q != null && q.resultat == 42) : "\nentree existante non trouvee dans la table !";

		q = t.trouver(l3, l4, 2);
		assert (q == null) : "\ntrouve une entree avec la mauvaise hauteur !";

		// tests avec des collisions nombreuses
		Ligne[] lignes = new Ligne[1024];
		for (int i = 0; i < 1024; i++) {
			int[] bits = new int[10];
			for (int j = 0; j < 10; j++)
				bits[j] = (i >> j) & 1;
			lignes[i] = new Ligne(bits);
		}
		for (int i1 = 0; i1 < 1024; i1++)
			for (int i2 = 0; i2 < 1024; i2++)
				t.ajouter(lignes[i1], lignes[i2], i1, i2);
		for (int i1 = 0; i1 < 1024; i1++)
			for (int i2 = 0; i2 < 1024; i2++) {
				q = t.trouver(lignes[i1], lignes[i2], i1);
				assert (q != null && q.resultat == i2) : "\nentree existante non trouvee dans la table !";
			}

		// tests qu'on utilise bien equals
		l1 = new Ligne(new int[] {0});
		t.ajouter(l1, l1, 2, 1);
		l2 = new Ligne(new int[] {0});
		assert (t.trouver(l2, l2, 2) != null) : "\nAttention : on doit utiliser equals et pas == pour chercher dans la table";

		System.out.println("\t\t[OK]");
	}


}
