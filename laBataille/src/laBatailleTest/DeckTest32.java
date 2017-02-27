package laBatailleTest;

import laBataille.Battle;
import laBataille.Deck;

public class DeckTest32 {
	
	// obtenir le pli qui s'est formé durant un tour
		private static Deck trickAfterRound(Battle before, Battle after) {
			if (after.get_player1().size() - before.get_player1().size() > 0) {
				// joueur 1 a ajouté le pli à  sa main
				return remaining(
						after.get_player2().size(), 
						after.get_player1().copy()
						);
			}
			if (after.get_player2().size() - before.get_player2().size() > 0) { 
				// joueur 2 a ajouté le pli à sa main
				return remaining(
						after.get_player1().size(), 
						after.get_player2().copy()
						);
			}
			return after.get_trick().copy();
		}

		private static Deck initial(int c, Deck d) {
			Deck r = new Deck();
			for (int i = 0; i < c; i++)
				r.pick(d);
			return r;
		}

		private static Deck remaining(int c, Deck d) {
			Deck r = new Deck();
			for (int i = 0; i < c; i++)
				r.pick(d);
			return d;
		}

		private static void test_isOver(int nbVals, int[] player1, int[] player2,
				boolean expected) {
			Battle test = new Battle(nbVals, new Deck(player1), new Deck(player2));
			boolean result = test.isOver();
			assert (result == expected) : "\ntest.isOver devrait renvoyer "
					+ expected + " au lieu de " + result;
		}


		private static void test_oneRound(int nbVals, int[] player1, int[] player2,
				boolean expected, int winner) {
			Battle before = new Battle(nbVals, new Deck(player1), new Deck(player2));
			Battle after = before.copy();
			boolean result = after.oneRound(); // jouer un tour
			assert (result == expected) : "\ntest.oneRound aurait du» renvoyer "
					+ expected + " au lieu de " + result + "\navant le tour :\n"
							+ (before.toString())
							+ "\naprès le tour :\n"
							+ (after.toString());
			int n1 = after.get_player1().size() - before.get_player1().size(); // System.out.println("n1 = "+n1);
			int n2 = after.get_player2().size() - before.get_player2().size(); // System.out.println("n2 = "+n2);
			assert (!(n1 > 0 && n2 > 0)) : "\nAu terme du tour, les deux joueurs ont gagné des cartes"
					+ "\navant le tour :\n"
					+ (before.toString())
					+ "\naprès le tour :\n"
					+ (after.toString());
			Deck trick_copy = trickAfterRound(before,after);
			assert (trick_copy != null):"trick_copy == null";
			if (n1 > 0) {
				// joueur 1 a gagné des cartes, donc le tour
				// nombre de cartes non joués dans p1
				Deck remaining_of_player1_before = remaining(-n2,before.get_player1().copy());
				Deck initial_of_player1_after = initial(remaining_of_player1_before.size(),after.get_player1().copy());
				assert (remaining_of_player1_before.equals(initial_of_player1_after)) : ""
						+ "\nLes cartes du pli n'ont peut-être pas été ajoutées à  la fin du paquet du joueur 1"
						+ "\navant le tour :\n"
						+ (before.toString())
						+ "\naprès le tour :\n"
						+ (after.toString());
				// vérifier que les cartes du pli sont entrelacées
				Deck trash = new Deck() ;
				Deck x1 = before.get_player1().copy();
				Deck x2 = before.get_player2().copy();
				for (int i = 0; i < n1; i++) {
					Integer a = trash.pick(x1);
					Integer b = trash.pick(x2);
					Integer c = trash.pick(trick_copy);
					Integer d = trash.pick(trick_copy);
					assert ((a == c && b == d) || (a == d && b == c)) : 
						"\nLes cartes du pli n'ont peut-être pas été ajoutées dans l'ordre spécifié par la règle du jeu"
						+ "\navant le tour :\n"
						+ (before.toString())
						+ "\naprès le tour :\n"
						+ (after.toString());				;
				}
			}
			;

		}

		public static void main(String[] args) {

			if (!DeckTest32.class.desiredAssertionStatus()) {
		        System.err.println("Vous devez activer l'option -ea de la JVM");
		        System.err.println("(Run As -> Run configurations -> Arguments -> VM Arguments)");
		        System.exit(1);
		      }
			
			// tester la méthode isOver
			test_isOver(0, new int[] {}, new int[] {}, true);
			test_isOver(1, new int[] { 1, 1, 1, 1 }, new int[] {}, true);
			test_isOver(1, new int[] {}, new int[] { 1, 1, 1, 1 }, true);
			test_isOver(1, new int[] { 1, 1 }, new int[] { 1, 1 }, false);
			System.out.println("La méthode isOver a passé les tests avec succès");



			// tester la mÃ©thode oneRound
			test_oneRound(1, new int[] { 1, 1 }, new int[] { 1, 1 }, false, 0);
			test_oneRound(1, new int[] { 1 }, new int[] { 1, 1, 1 }, false, 1);
			test_oneRound(1, new int[] { 1, 1, 1 }, new int[] { 1 }, false, 2);
			test_oneRound(2, new int[] { 2, 2, 2, 2 }, new int[] { 1, 1, 1, 1 },
					true, 0);
			test_oneRound(4, new int[] { 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 4, 
					3, 2, 1 }, new int[] { 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 
					1, 2, 3, 4 }, true, 1);
			System.out.println("La méthode oneRound a passé les tests avec succès");
		}


}
