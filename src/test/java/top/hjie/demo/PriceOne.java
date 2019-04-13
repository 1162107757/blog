package top.hjie.demo;

import java.math.BigDecimal;

public class PriceOne {

	public static void main(String[] args) {
		
		/*BigDecimal one = new BigDecimal("67").add(new BigDecimal("134")
				.divide(new BigDecimal("216"),8,BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal("24")));
		
		BigDecimal two = new BigDecimal("12").add(new BigDecimal("24")
				.divide(new BigDecimal("216"),8,BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal("24")));
		
		BigDecimal three = new BigDecimal("30").add(new BigDecimal("30")
				.divide(new BigDecimal("216"),8,BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal("48")));
		
		BigDecimal four = new BigDecimal("28").add(new BigDecimal("28")
				.divide(new BigDecimal("216"),8,BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal("48")));*/
		
		
		
		Double one = DecimalUtils.sub(Double.parseDouble("67"), DecimalUtils.div(DecimalUtils.mul(DecimalUtils.div(Double.parseDouble("134"), 216, 6), 48),Double.parseDouble("2"), 6));
		Double two = DecimalUtils.sub(Double.parseDouble("12"), DecimalUtils.div(DecimalUtils.mul(DecimalUtils.div(Double.parseDouble("24"), 216, 6), 48),Double.parseDouble("2"), 6));
		Double three = DecimalUtils.sub(Double.parseDouble("30"), DecimalUtils.div(DecimalUtils.mul(DecimalUtils.div(Double.parseDouble("30"), 216, 6), 48),Double.parseDouble("1"), 6));
		Double four = DecimalUtils.sub(Double.parseDouble("28"), DecimalUtils.div(DecimalUtils.mul(DecimalUtils.div(Double.parseDouble("28"), 216, 6), 48),Double.parseDouble("1"), 6));
		
		Double total = (one * 2) + (two * 2) + three + four;
		System.out.println(new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		System.out.println(new BigDecimal(one).setScale(2, BigDecimal.ROUND_HALF_UP));
		System.out.println(new BigDecimal(two).setScale(2, BigDecimal.ROUND_HALF_UP));
		System.out.println(new BigDecimal(three).setScale(2, BigDecimal.ROUND_HALF_UP));
		System.out.println(new BigDecimal(four).setScale(2, BigDecimal.ROUND_HALF_UP));
		
	}
	
}
