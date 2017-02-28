package matchTestTest;

import matchTree.CompteGrilles;

public class CompteGrillesTest2 {
	
	public static void main(String[] args) {
		//Pour s'assurer que les assert's sont activés
		if (!CompteGrillesTest2.class.desiredAssertionStatus()) {
			System.err.println("Vous devez activer l'option -ea de la JVM");
		        System.err.println("(Run As -> Run configurations -> Arguments -> VM Arguments)");
			System.exit(1);
		}

		long[] nums = new long[]{16L,102L,2030L,60232L,3858082L,446672706L,101578277384L,43680343039806L,36133311325799774L};

		for(int n = 2; n <= 10; ++n) {
			CompteGrilles c = new CompteGrilles(n);

			System.out.print("tester les tableaux " + n + "x" + n + "... ");

			long cnt = c.compte(n);
			System.out.print(cnt);
			assert (cnt == nums[n-2]) : "\nIl y a " + nums[n-2] + " configurations stables dans un tableau  " + n + "x" + n +  "\n";

			System.out.println("\t\t[OK]");
		}


	}


}
