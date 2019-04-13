package top.hjie.demo;

import java.math.BigDecimal;

public class PriceTwo {

	public static void main(String[] args) {
		// 优惠金额
		BigDecimal discounts = new BigDecimal("216").subtract(new BigDecimal("168"));
		
		BigDecimal goodPrice = new BigDecimal("28");
		
		BigDecimal totalPrice = goodPrice.multiply(new BigDecimal("1"));
		
		BigDecimal divide = totalPrice.divide(new BigDecimal("216"),6,BigDecimal.ROUND_HALF_UP);
		BigDecimal multiply = divide.multiply(discounts);
		BigDecimal multiply2 = multiply.multiply(new BigDecimal("1"));
		System.out.println(multiply2);
		
		
		
		System.out.println(new BigDecimal("59.555520").multiply(new BigDecimal("2")).add(new BigDecimal("10.666656").multiply(new BigDecimal("2"))).add(new BigDecimal("6.666672")).add(new BigDecimal("6.222240")));
		
	}
	
}
