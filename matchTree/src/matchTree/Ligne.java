package matchTree;

public class Ligne {

	private final int[] fruits;

	public Ligne(int[] fruits) {
		super();
		this.fruits = fruits;
	}

	public Ligne ajouteFruit(int fruit) {

		// création de nouveau table de fruits
		int[] f = new int[fruits.length + 1];

		for (int i = 0; i < fruits.length; i++) {

			f[i] = fruits[i];

			f[fruits.length] = fruit;

		}

		return new Ligne(f);

	}

	public boolean estValide() {
		
		for(int i=0; i<fruits.length-2; i++){
			
			
			if((fruits[i]==fruits[i-1]) && (fruits[i]==fruits[i+1])){
				
				
			}
		}
		
		return false;

	}

	public boolean sontEmpilables(Ligne l1, Ligne l2) {
	
		for(int i=0; i<this.fruits.length; i++){
			
			if(this.fruits [i]==l1.fruits[i] && this.fruits[i]== l2.fruits [i]){
			
				return false;
			}
			
			
		}
		
		return true;
		
		
	}

	public boolean equals(Object o) {
		Ligne l = (Ligne) o;

		if (this.fruits.length != l.fruits.length)
			return false;
		for (int i = 0; i < this.fruits.length; ++i) {
			if (this.fruits[i] != l.fruits[i])
				return false;
		}
		return true;
	}

	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < fruits.length; ++i) {
			hash = 2 * hash + fruits[i];
		}
		return hash;
	}

	public String toString() {
		StringBuffer s = new StringBuffer();

		for (int i = 0; i < fruits.length; ++i)
			s.append(" _");
		s.append("\n");

		for (int i = 0; i < fruits.length; ++i)
			s.append("|" + fruits[i]);
		s.append("|\n");

		return s.toString();
	}

}
