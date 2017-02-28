package matchTestTest;

import matchTree.CompteGrilles;
import matchTree.Ligne;

public class CompteGrillesTest {
	
	public static void main(String[] args) {
		//Pour s'assurer que les assert's sont activés
		if (!CompteGrillesTest.class.desiredAssertionStatus()) {
			System.err.println("Vous devez activer l'option -ea de la JVM");
		        System.err.println("(Run As -> Run configurations -> Arguments -> VM Arguments)");
			System.exit(1);
		}

		int[] nums = new int[]{2,4,6,10,16,26,42,68,110,178};

		for(int n = 1; n <= 10; ++n) {
			CompteGrilles c = new CompteGrilles(n);

			System.out.print("tester les lignes de largeur " + n + "... ");
			for(Ligne l : c.toutesLesLignes) {
				assert (l.estValide()) : "\nLa ligne " + l + " n'est pas valide\n";
			}

			int cnt = c.toutesLesLignes.size();
			System.out.print(cnt);
			assert (cnt == nums[n-1]) : "\nIl y a " + nums[n-1] + " lignes de largeur " + n + "\n";

			System.out.println("\t\t[OK]");
		}
	}


}
