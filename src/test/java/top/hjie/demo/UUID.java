package top.hjie.demo;

import java.util.regex.Pattern;

public class UUID {

	public static void main(String[] args) {
	/*	System.out.println(java.util.UUID.randomUUID());
		
		boolean matches = Pattern.compile("[A-Za-z0-9]+").matcher("hA1").matches();
		System.out.println(matches);
		
		
		int length = "   ".replaceAll("\\s*$", "").length();
		
		System.out.println(length);
		
		
		System.out.println("465465".indexOf("."));
		
		System.out.println(new BigDecimal(0).compareTo(new BigDecimal(10)));
		
		
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(j == 5){
					// break;
					// System.out.println(j);
					
					System.out.println(j);
					return;
				}
			}
		}*/
		
		String randomId = RandomId.randomId();
		System.out.println(randomId);
		
		
		
		
		System.out.println(Pattern.compile("[0-9]{4}[-][0-9]{7}|[0-9]{3}[-][0-9]{8}").matcher("083-64466986").matches());
		
	}
	
}
