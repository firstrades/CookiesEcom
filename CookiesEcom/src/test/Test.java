package test;

import java.util.Scanner;



public class Test {

	public static void main( String args[] ) {  
		  
		  Scanner scanner = new Scanner( System.in );  
		  System.out.print("Enter ur age ");  
		    
		  int value = scanner.nextInt();  
		  assert value >= 18 : " Not valid";  //give 17 or less to get error
		  
		  /*if (value < 18)
			    throw new AssertionError();*/
		  
		  System.out.println("value is " + value); 
		  
		  scanner.close();
	}   
	
}
