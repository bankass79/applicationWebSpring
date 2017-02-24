package laBataille;


import java.util.Iterator;
import java.util.LinkedList;

public class Deck {
	
	int nbVals;
	int val;
	String couleur;
	
	LinkedList <Integer> deck= new LinkedList<Integer> ();

	public Deck() {
		
		deck= new LinkedList <Integer> ();
	}
   

	public Deck(String couleur) {
		super();
		this.couleur = couleur;
	}


	public Deck(int nbVals) {
		
		deck= new LinkedList<Integer> ();
		
		for(int i=0; i<=nbVals; i++){
			
			for(int j=0; j<=4; j++){
			
				deck.add(j);
			
			}
		}
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
	
   
	
}
