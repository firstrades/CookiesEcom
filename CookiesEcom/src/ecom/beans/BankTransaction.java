package ecom.beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankTransaction {
	
	private BankTransaction() {}
	
	public static BankTransaction getNewInstance() {		
		return new BankTransaction();
	}
	
	/********************************************************/
	
	public String hashCalculation(String type, String hashString) {		
		
		byte[] hashseq = hashString.getBytes();
		StringBuffer hexString = new StringBuffer();
		
		try {
			
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();	//System.out.println(messageDigest.toString() + " End");
	
			for (int i = 0 ; i < messageDigest.length; i++) {
				
				//System.out.println(messageDigest[i]);
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if(hex.length() == 1) hexString.append("0");
				hexString.append(hex);
			}
			
		} catch(NoSuchAlgorithmException nsae) { }
		
		return hexString.toString();

	}
	
	
	public String getNextTransactionId() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss/Ms");	
		
		Date date = new Date();
		
		String str = dateFormat.format(date).toString();
		
		String str1 = "J" + str;
		
		return str1.replace("/", "");
	}
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
