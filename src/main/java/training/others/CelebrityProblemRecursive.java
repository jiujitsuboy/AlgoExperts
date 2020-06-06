package training.others;

public class CelebrityProblemRecursive {
	// Person with 2 is celebrity 
	private static int MATRIX[][] = {{0, 1, 1, 0}, 
									 {0, 0, 0, 0},
			  			  			 {0, 1, 1, 1}, 			  			  			 
			  			  			 {0, 1, 1, 0}}; 

	public static boolean knows(int a, int b) 
	{ 
		return (MATRIX[a][b]==1)?true:false; 
	} 

	// Returns -1 if celebrity 
	// is not present. If present, 
	// returns id (value from 0 to n-1). 
	public static int findCelebrity(int n) 
	{ 
		//base case 
		if(n == 1) 
		return n - 1; 
		
		//find the celebrity with n-1 
		//persons 
		int id = findCelebrity(n-1); 
		
		//if there are no celebrities 
		if(id == -1) 
			return n-1; 
		
		// if the celebrity knows the 
		//nth person 
		else if(knows(id, n-1) && !knows(n-1, id)) 
		{ 
			return n-1; 
		} 
		//if the nth person knows the 
		//celebrity then return the id 
		else if(knows(n-1, id) && !knows(id, n-1)) 
		{ 
			return id; 
		} 

		//if there is no celebrity 
		return -1; 
	} 

	// Returns -1 if celebrity 
	// is not present. If present, 
	// returns id (value from 0 to n-1). 
	// a wrapper over findCelebrity 
	public static int Celebrity(int n) 
	{ 
		//find the celebrity 
		int id = findCelebrity(n); 
		
		//check if the celebrity found 
		//is really the celebrity 
		if(id == -1) 
			return id; 
		else
		{ 
			int c1=0, c2=0; 

			//check the id is really the 
			//celebrity 
			for(int i=0; i<n; i++) 
			if(i != id) 
			{ 
				c1+=(knows(id, i))?1:0; 
				c2+=(knows(i, id))?1:0; 
			} 
			
			//if the person is known to 
			//everyone. 
			if(c1==0 && c2==n-1) 
				return id; 
			
			return -1; 
		} 
	} 

	// Driver code 
	public static void main(String args[]) 
	{ 
		int n = 4; 
		int id = Celebrity(n); 
		if(id == -1) { 
			System.out.println("No celebrity");  
		}
		else {
			System.out.println("Celebrity ID "+id); 
		}
		
	} 
}
