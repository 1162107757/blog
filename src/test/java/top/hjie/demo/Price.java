package top.hjie.demo;

import java.math.BigDecimal;

public class Price {

	public static void main(String[] args) {
		System.out.println(67F + (134F / 216F) * 48F / 2F);
		System.out.println(12F + (24F / 216F) * 48F / 2F);
		System.out.println(30F + (30F / 216F) * 48F / 1F);
		System.out.println(28F + (28F / 216F) * 48F / 1F);
		
		BigDecimal one = new BigDecimal("67").add(new BigDecimal("134")
				.divide(new BigDecimal("216"),6,BigDecimal.ROUND_DOWN)
				.multiply(new BigDecimal("48")).divide(new BigDecimal("2")));
		
		BigDecimal two = new BigDecimal("12").add(new BigDecimal("24")
				.divide(new BigDecimal("216"),6,BigDecimal.ROUND_DOWN)
				.multiply(new BigDecimal("48")).divide(new BigDecimal("2")));
		
		BigDecimal three = new BigDecimal("30").add(new BigDecimal("30")
				.divide(new BigDecimal("216"),6,BigDecimal.ROUND_DOWN)
				.multiply(new BigDecimal("48")).divide(new BigDecimal("1")));
		
		BigDecimal four = new BigDecimal("28").add(new BigDecimal("28")
				.divide(new BigDecimal("216"),6,BigDecimal.ROUND_DOWN)
				.multiply(new BigDecimal("48")).divide(new BigDecimal("1")));
		
	
		System.out.println(one);
		System.out.println(two);
		System.out.println(three);
		System.out.println(four);
		System.out.println(one.add(two).add(three).add(four));
		
	}
	
}
