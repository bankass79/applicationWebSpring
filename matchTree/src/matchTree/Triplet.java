package matchTree;

public class Triplet {

	Ligne l1;
	Ligne l2;
	int hauteur;

	
	public Triplet(Ligne l1, Ligne l2, int hauteur) {
		
		
		this.l1= l1;
		this.l2=l2;
		this.hauteur=hauteur;
			
		}
	
	public boolean equals(Object o) {
		
		Triplet t = (Triplet) (o);
		
		if(this.l1.equals(t.l1) && this.l2.equals(t.l2) && this.hauteur==t.hauteur){
			
			
		}
		return true;
		
		
	}
		public int hashCode(){
			
			
			return (l1.hashCode()*l1.hashCode()+ l2.hashCode()*l2.hashCode()+ hauteur*hauteur);
			
		}
	

}
