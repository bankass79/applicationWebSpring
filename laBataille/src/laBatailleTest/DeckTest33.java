package laBatailleTest;

import laBataille.Battle;
import laBataille.Deck;

public class DeckTest33 {
	
	private static void test_winner(int nbVals, String player1, String player2,
			int expected) {
		Battle test = new Battle(nbVals, new Deck(player1), new Deck(player2));
		int result = test.winner();
		assert (result == expected) : "\ntest.winner a renvoyé "
				+ result + " au lieu de " + expected;
	}

	
	private static void test_game(int turns, int nbVals, String p1, String p2, int expected){
		Deck player1 = new Deck(p1);
		Deck player2 = new Deck(p2);
		Battle b = new Battle(nbVals,player1,player2);
		System.out.println("état intial :\n"+b.toString());
		int result = b.game(turns) ;
		assert( result==0 || result==1 || result==2 ):"\nL'entier renvoyé par game doit être 0, 1, ou 2.";
		assert( result==expected ):"\nLa méthode game a renvoyé "+result+" au lieu de "+expected;
		System.out.println("Maximum "+turns+" "+(turns==1?"coup":"coups"));
		System.out.println("état final :\n"+b.toString());
		//assert(result == expected):"\nLa méthode game devrait renvoyer "+expected;
		if (result==0) {System.out.println("Partie nulle.\n");return;}
		if (result==1) {System.out.println("Joueur 1 gagne.\n");return;}
		if (result==2) {System.out.println("Joueur 2 gagne.\n");return;}
	}
	
	public static void main(String[] args) {

		if (!DeckTest33.class.desiredAssertionStatus()) {
	        System.err.println("Vous devez activer l'option -ea de la JVM");
	        System.err.println("(Run As -> Run configurations -> Arguments -> VM Arguments)");
	        System.exit(1);
	      }
		
		// tester la méthode winner()
		
		test_winner(0, "", "", 0);
		test_winner(1, "1 1 1 1", "" , 1);
		test_winner(1, "" , "1 1 1 1", 2);
		test_winner(1, "1 1", "1 1", 0);
		System.out.println("La méthode winner a passé les tests avec succès\n");

		// tester la méthode game(int turns)
		
		test_game(1,0,"","",0);
		test_game(1,1,"1 1","1 1",0);
		test_game(1,1,"1 1 1","1",1);
		test_game(1,1,"1","1 1 1",2);

		test_game(1,2,"1 2 1 2","2 1 2 1",2);
		test_game(2,2,"1 2 1 2","2 1 2 1",0);
		test_game(3,2,"1 2 1 2","2 1 2 1",2);
		test_game(4,2,"1 2 1 2","2 1 2 1",0);
		test_game(5,2,"1 2 1 2","2 1 2 1",1);
		test_game(6,2,"1 2 1 2","2 1 2 1",0);

		System.out.println("La méthode game(int turns) a passé les tests avec succès");
		
	}


}
