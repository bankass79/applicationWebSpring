package laBataille;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import laBatailleTest.DeckTest32;

public class Deck {
	
	int nbVals;
	int val;
	String couleur;
	
	LinkedList <Integer> deck= new LinkedList<Integer> ();

	public Deck() {
		
		deck= new LinkedList <Integer> ();
	}
   

	


	public Deck(int nbVals) {
		
		deck= new LinkedList<Integer> ();
		
		for(int i=0; i<=nbVals; i++){
			
			for(int j=0; j<=4; j++){
			
				deck.add(j);
			
			}
		}
	}

	public Deck(int[] a){
		deck = new LinkedList<Integer> ();
		for(Integer card:a){
			this.deck.addLast(card);
		}
	}

	public Deck(String s){
		Scanner sc = new Scanner(s);
		deck = new LinkedList<Integer> () ;
		while (sc.hasNextInt()) {
	          deck.addLast(sc.nextInt());
	      }
		sc.close();
	}

	// renvoie true si le paquet est vide sinon false;
	
	public boolean isEmpty (){
		
		for (int i=0; i<deck.size(); i++){
		
			if( this.deck.isEmpty()){
			
			   return true;
			}else {
		
		
		return false;
			}
		}
		return false;
	}
	
	
	// cette méthode renvoie le nombre de carte que contient le paquet
	
	public int size(){
		
		return  this.deck.size();
		
	}
	
	
	// cette méthode renvoie une chaîne de caractères representant le paquet.
	@Override
	public String toString() {
		return deck.size() + " " +(deck.size()==0||deck.size()==1?"carte":"cartes")+" "+deck;
	}



	// Si le paquet d n'est pas vide, supprime sa première carte, l'ajoute à
	//la fin du paquet courtant, puis renvoie sa valeur courante, sinon renvoie
	//null tandis que les paquets restent inchangés.
	
	public Integer pick (Deck d){
		
		if (d.isEmpty()){
			
			return null;
		}
		
		Integer card=d.deck.removeFirst();
		
		this.deck.addLast(card);
		
		return card;

		
	}
	//Cette méthode supprime une à une toutes les cartes du paquet d et les ajoutes à la fin du paquet courant.
	
		public void  pickAll(Deck d){
	
		int card=0;
		
		for (int i=0; i<d.size(); i++){
			
			card=d.deck.removeFirst();
			
			d.deck.addLast(card);
			
		}
		
	}
	
  // Cette méthode renvoie true  si l'objet courant est un paquet valide
	
		public boolean isDeck (int nbVals){
	
		int [] compteur= new int [nbVals]; 
		
		for (Integer x: deck ){
		
		if(x<1 || x>nbVals){
			
			compteur [x-1]++;
			
			return false;
		}
		}
		
		for(int i=0; i<nbVals; i++){
			
			
			if(compteur [i]>4){
				
				return false;
			}
		}
		return true;
	}
	
	//la méthode cut() renvoie le nombre de cartes du "premier" paquet
	
		public int cut (){
		
		int n =0;
		
		for(int i=0; i<size (); i++){
			
			if(Math.random()<0.5){
				
				n++;
				
			}
		
		}
		return n;
	}
// coupe la carte 
	public Deck split() {
		
		Deck r = new Deck();
		int c=cut();
		for(int i=0; i<c; i++)
			r.pick(this);
		return r;

	}

	public Deck copy() {
		Deck d = new Deck();
		for(Integer card:this.deck)
			d.deck.addLast(card);
		return d;

	}
	// melange les cartes
	public void riffleWith(Deck k){
		
		Deck resultat =new Deck ();
		
	   double proba=0.0;
	   
	   
	   while (!deck.isEmpty() && !k.isEmpty() ){
		   
		   
		   proba= (k.size()/(k.size()+ deck.size()));
		   
		   if(Math.random()<proba){
			   
			   resultat.pick(k);
		   } else{
			     
			   resultat.pick(this);
		   }
	   }
	}
	
	
	// obtenir le pli qui s'est formé durant un tour
		private static Deck trickAfterRound(Battle before, Battle after) {
			if (after.get_player1().size() - before.get_player1().size() > 0) {
				// joueur 1 a ajouté le pli à  sa main
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
			assert (result == expected) : "\ntest.oneRound aurait dÃ» renvoyer "
					+ expected + " au lieu de " + result + "\navant le tour :\n"
							+ (before.toString())
							+ "\naprÃ¨s le tour :\n"
							+ (after.toString());
			int n1 = after.get_player1().size() - before.get_player1().size(); // System.out.println("n1 = "+n1);
			int n2 = after.get_player2().size() - before.get_player2().size(); // System.out.println("n2 = "+n2);
			assert (!(n1 > 0 && n2 > 0)) : "\nAu terme du tour, les deux joueurs ont gagnÃ© des cartes"
					+ "\navant le tour :\n"
					+ (before.toString())
					+ "\naprÃ¨s le tour :\n"
					+ (after.toString());
			Deck trick_copy = trickAfterRound(before,after);
			assert (trick_copy != null):"trick_copy == null";
			if (n1 > 0) {
				// joueur 1 a gagné des cartes, donc le tour
				// nombre de cartes non jouÃ©es dans p1
				Deck remaining_of_player1_before = remaining(-n2,before.get_player1().copy());
				Deck initial_of_player1_after = initial(remaining_of_player1_before.size(),after.get_player1().copy());
				assert (remaining_of_player1_before.equals(initial_of_player1_after)) : ""
						+ "\nLes cartes du pli n'ont peut-Ãªtre pas été ajoutées à  la fin du paquet du joueur 1"
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
						"\nLes cartes du pli n'ont peut-Ãªtre pas été ajoutées dans l'ordre spécifié par la règle du jeu"
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



			// tester la méthode oneRound
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

	

	public int[] countCards(int i) {
		int[] count = new int[nbVals+1];
		for (Integer x : deck) {
			if ((x < 1) || (x > nbVals))
				count[0]++;
			else count[x]++;
		}
		return count;

	}
	
	public boolean extractSubdeck(Deck d) {
		Iterator<Integer> it = d.deck.iterator ();
		for(Integer card : this.deck){
			if (!it.hasNext()) return false;
			while(it.next() != card){
				if (!it.hasNext()) return false;
			}
			it.remove();
		};
		return true;

	}
	
   public void riffleShuffle (int m){
	  
	   for (int i=0; i<=m; i++){
		   Deck d = split(); // on coupe la carte 
		  
		   riffleWith(d);// puis on les mélange m fois
	   }
	   
   }


public int[] toArray() {
	int [] ret = new int[size()] ;
	int counter = 0 ;
	for(int card:this.deck){
		ret[counter] = card ;
		counter++;
	}
	return ret;

}


public boolean sameCards(Deck d){
	int nbVals = this.highestVal();
	int a[] = this.countCards(nbVals);
	int b[] = d.countCards(nbVals);
	for(int i = 0 ; i <= nbVals ; i++){
		if(a[i] != b[i]) return false;
	}
	return true;
}
public boolean isFull(int nbVals){
	return ((deck.size() == 4*nbVals) && isDeck(nbVals)); 
}

public boolean equals(Deck d){
	Iterator<Integer> it1 = deck.iterator() ;
	Iterator<Integer> it2 = d.deck.iterator() ;
	while(it1.hasNext() && it2.hasNext()){
		if(it1.next() != it2.next()) return false;
	}
	return (!(it1.hasNext() || it2.hasNext())) ;
}
public int highestVal(){
	int r = 0;
	for(Integer card:deck){
		if(card > r) r = card;
	}
	return r;
}

	
}
