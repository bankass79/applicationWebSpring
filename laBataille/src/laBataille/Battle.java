package laBataille;

public class Battle {

	private int nbVals;
	private Deck trick;
	private Deck player1;
	private Deck player2;

	public Deck get_player1() {
		return player1;
	}

	public Deck get_player2() {
		return player2;
	}

	public Deck get_trick() {
		return trick;
	}

	public int get_nbVals() {
		return nbVals;
	}

	public String toString() {
		return "Joueur 1 a " + player1 + "\n" + "Joueur 2 a " + player2 + "\n"
				+ "Le pli "
				+ (trick.isEmpty() ? "est vide" : ("contient " + trick));
	}

	public boolean equals(Battle b) {
		return player1.equals(b.player1) && player2.equals(b.player2)
				&& trick.equals(b.trick);
	}

	// La bataille sans cartes
	public Battle() {
		nbVals = 0;
		player1 = new Deck();
		player2 = new Deck();
		trick = new Deck();
	}

	// Dupliquer une partie
	public Battle copy() {
		Battle r = new Battle();
		r.nbVals = this.get_nbVals();
		r.player1 = this.player1.copy();
		r.player2 = this.player2.copy();
		r.trick = this.trick.copy();
		return r;
	}

	// Partie truquée (pour les tests)
	public Battle(int nbVals, Deck player1, Deck player2) {
		this.nbVals = nbVals;
		this.player1 = player1;
		this.player2 = player2;
		trick = new Deck();
	}

	public Battle(int nbVals, String player1, String player2) {
		this.nbVals = nbVals;
		this.player1 = new Deck(player1);
		this.player2 = new Deck(player2);
		this.trick = new Deck();
	}

	public Battle(int nbVals, Deck player1, Deck player2, Deck trick) {
		this.nbVals = nbVals;
		this.player1 = player1;
		this.player2 = player2;
		this.trick = trick;
	}

	public Battle(int nbVals, String player1, String player2, String trick) {
		this.nbVals = nbVals;
		this.player1 = new Deck(player1);
		this.player2 = new Deck(player2);
		this.trick = new Deck(trick);
	}

	
	public Battle(int nbVals) {
		super();
		this.nbVals = nbVals;
		
		Deck d = new Deck(nbVals);
		
		// création de jeu avec deux joueur ne disposant pas de cartes en main
		
		player1= new Deck ();
		
		player2= new Deck();
		
		// création de plis vide
		
		trick= new Deck ();
		
		// puis on mélange les cartes
		
		d.riffleShuffle(10);
		
		// les cartes sont distribuées entre les deux joueurs
		
		for (int i=0; i<2*nbVals; i++){
			
			player1.pick(d);
			player2.pick(d);
		// la partie peut ainsi commencer
			
		}
		
	}

	// vérifie si un des jouers n'a plus de carte pour continuer la partie, la partie peut-elle continuée?
 
	public boolean isOver(){
	
	 return ((player1.isEmpty()|| player2.isEmpty()));
	 
 }
 
 //premier tour de la partie
 public boolean oneRound (){

	 if(this.isOver()){
		 return  false;
	 }
	 
	 //
	 int p= trick.pick(player1);
	 int p2=trick.pick(player2);
	 
	 while(p==p2){

		for(int i=0; i<2; i++){
			

			 if(this.isOver()){
				 
				 p=trick.pick(player1);
				 p2=trick.pick(player2);
				 return false;
			 }
			 
		}
		if(p>p2){
			
			player1.pickAll(trick);
		}else{
			
			player2.pickAll(trick);
			
		}
	 }
	return true;
	 
 }
//Qui a gagné la partie de jeu ?
	public int winner() {
		int x1 = player1.size();
		int x2 = player2.size();
		if (x1 == x2) return 0 ;
		if (x1 > x2) return 1; 
				else return 2;
	}

	
	// Une partie avec un nombre maximum de coups fixé à  l'avance
	public int game(int turns) {
		while ((turns > 0) && (this.oneRound()))
			turns--;
		return this.winner();
	}


	// Une partie sans limite de coups avec détection des parties infinies
	public int game() {
		// this est le lièvre
		// la tortue en est une copie
		Battle turtle = this.copy();
		do {
			// Le lièvre joue deux tours...
			if(!this.oneRound()) return this.winner();
			if(!this.oneRound()) return this.winner();
			// ...alors que la tortue n'en joue qu'un.
			turtle.oneRound();
			// La tortue n'est utilisée que pour 
			// la détection des parties infinies.
		} while(!this.equals(turtle));
		return 3;
	}

	
	public static void stats(int nbVals, int nb_of_games){
		int player1_wins = 0 ;
		int player2_wins = 0 ;
		int out_of_cards = 0 ;
		int unfinished = 0 ;
		int result = 0;
		Battle b = null;
		for(int i = 0; i < nb_of_games ; i++){
			b = new Battle(nbVals);
			result = b.game();
			if(result == 1)player1_wins++;
			if(result == 2)player2_wins++;
			if(result == 0)out_of_cards++;
			if(result == 3)unfinished++;
		}
		System.out.println(""+player1_wins+" parties sur "+nb_of_games+" ont été gagnées par le premier joueur.");
		System.out.println(""+player2_wins+" parties sur "+nb_of_games+" ont été gagnées par le deuxième joueur.");
		System.out.println(""+unfinished+" parties infinies sur "+nb_of_games);
		System.out.println(""+out_of_cards+" parties nulles sur "+nb_of_games);
	}
	


}
