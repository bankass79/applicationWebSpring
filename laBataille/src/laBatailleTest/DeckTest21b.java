package laBatailleTest;

import laBataille.Deck;

public class DeckTest21b {


		private static Deck REF = new Deck(13);

		private static int N = 100;

		public static void main(String[] args) {
		
			if (!DeckTest21b.class.desiredAssertionStatus()) {
				System.err.println("Vous devez activer l'option -ea de la JVM");
				System.err
						.println("(Run As -> Run configurations -> Arguments -> VM Arguments)");
				System.exit(1);
			}
			Deck a = null;
			Deck b = null;
			for (int i = 0; i < N; i++) {
				b = new Deck(13);
				a = b.split();
				Deck c = a.copy();
				Deck d = b.copy();
				a.pickAll(b);
				assert (a.equals(REF)) : ("Bug split:\na=" + c + "\nb=" + d);
			}

			System.out.println("La méthode split a passé les tests");
		}


	

}
