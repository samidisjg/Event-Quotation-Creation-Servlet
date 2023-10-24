package s_supplier;

import java.util.Random;

public class QuoteNumberGenerator {
	 private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    private static final int length = 4; 

	    public static String generateRandomQuoteNumber() {
	        StringBuilder sb = new StringBuilder();
	        Random random = new Random();

	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(characters.length());
	            char randomChar = characters.charAt(index);
	            sb.append(randomChar);
	        }

	        return sb.toString();
	    }

}
