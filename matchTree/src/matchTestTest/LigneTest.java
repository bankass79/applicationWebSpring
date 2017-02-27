package matchTestTest;

import matchTree.Ligne;

public class LigneTest {
	
	private static void test_equals(boolean e, Ligne l1, Ligne l2){
		boolean b = l1.equals(l2);
		assert (e == b) : "\nLa ligne\n" + l1 + (e ? "est " : "n'est pas ") + "egale a la ligne\n" + l2;
	}

	private static void test_estValide(boolean e, Ligne l){
		boolean b = l.estValide();
		assert (e == b) : "\nLa ligne\n" + l + (e ? "est " : "n'est pas ") + "valide\n";
	}

	private static void test_sontEmpilables(boolean e, Ligne l1, Ligne l2, Ligne l3){
		boolean b = l1.sontEmpilables(l2, l3);
		assert (e == b) : "\nLes lignes\n" + l1 + l2 + l3 + (e ? "sont " : "ne sont pas ") + "empilables\n";
	}

	public static void main(String[] args) {
		//Pour s'assurer que les assert's sont activées
		if (!LigneTest.class.desiredAssertionStatus()) {
			System.err.println("Vous devez activer l'option -ea de la JVM");
		        System.err.println("(Run As -> Run configurations -> Arguments -> VM Arguments)");
			System.exit(1);
		}

		System.out.print("tester la methode ajouteFruit... ");

		Ligne l = new Ligne(new int[]{0,0,1,0,1});

		l = l.ajouteFruit(1);
		test_equals(true, l, new Ligne(new int[]{0,0,1,0,1,1}));
		test_equals(false, l, new Ligne(new int[]{0,0,1,0,1}));
		test_equals(false, l, new Ligne(new int[]{0,0,1,0}));

		l = l.ajouteFruit(0);
		test_equals(true, l, new Ligne(new int[]{0,0,1,0,1,1,0}));
		test_equals(false, l, new Ligne(new int[]{0,0,1,0,1,1,1}));
		test_equals(false, l, new Ligne(new int[]{0,0,1,0,1,1}));
		test_equals(false, l, new Ligne(new int[]{0,0,1,0,1}));

		System.out.println("\t\t[OK]");

		System.out.print("tester la methode estValide...   ");

		test_estValide(true, new Ligne(new int[]{}));
		test_estValide(true, new Ligne(new int[]{1}));
		test_estValide(true, new Ligne(new int[]{1,0}));
		test_estValide(true, new Ligne(new int[]{1,1}));
		test_estValide(true, new Ligne(new int[]{1,1,0}));
		test_estValide(true, new Ligne(new int[]{0,1,0}));
		test_estValide(true, new Ligne(new int[]{0,1,1}));
		test_estValide(true, new Ligne(new int[]{0,1,1}));
		test_estValide(false, new Ligne(new int[]{1,1,1}));
		test_estValide(false, new Ligne(new int[]{0,0,0}));
		test_estValide(false, new Ligne(new int[]{0,0,0,1}));
		test_estValide(false, new Ligne(new int[]{1,0,0,0}));
		test_estValide(true, new Ligne(new int[]{1,0,0,1}));
		test_estValide(true, new Ligne(new int[]{1,0,1,1}));

		System.out.println("\t\t[OK]");

		System.out.print("tester la methode sontEmpilables... ");

		test_sontEmpilables(false,
				new Ligne(new int[]{1,0,1}),
				new Ligne(new int[]{0,1,1,0}),
				new Ligne(new int[]{1,1,0,0}));
		test_sontEmpilables(true,
				new Ligne(new int[]{1,0,1,1}),
				new Ligne(new int[]{0,1,1,0}),
				new Ligne(new int[]{1,1,0,0}));
		test_sontEmpilables(false,
				new Ligne(new int[]{1,0,1,1}),
				new Ligne(new int[]{0,1,1,0}),
				new Ligne(new int[]{1,1,1,0}));
		test_sontEmpilables(true,
				new Ligne(new int[]{0,0,0,1}),
				new Ligne(new int[]{0,1,1,0}),
				new Ligne(new int[]{1,1,0,0}));
		test_sontEmpilables(false,
				new Ligne(new int[]{1,0,1,0}),
				new Ligne(new int[]{0,1,1,0}),
				new Ligne(new int[]{1,1,0,0}));
		test_sontEmpilables(false,
				new Ligne(new int[]{1,0,1,0}),
				new Ligne(new int[]{0,1,1,0}),
				new Ligne(new int[]{1,1,1,0}));

		System.out.println("\t\t[OK]");
	}

}
