package rechercheDeValeur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MaxMin {

	public static void main(String[] args) {
		
		ArrayList <Integer> val= new ArrayList<Integer> ();
		
		
		Scanner sc= new Scanner (System.in);
		
		for (int j=0; j<5; j++){	
			
			
			System.out.println("Saisissez les valeurs de la liste");
	
			int value=sc.nextInt();
			
			val.add(value);
		}
		
		int max=0;
		int min=0;
		
		for(int i=0; i<val.size(); i++){
			
			
			max=Collections.max(val);
			
			min=Collections.min(val);
			
		}
		System.out.println("la valeur max est:"+ max+ " "+  "et la valeur minimale est:"+ min);

	}

}
