package top.hjie.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JudegCard {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入身份证号：");
		String cardCode = sc.nextLine();
		
		Map<Integer, Integer> days = new HashMap<>();
		// 添加每个月的天数	// 二月份判断是否是闰年再添加
		days.put(1, 31);
		days.put(3, 31);
		days.put(4, 30);
		days.put(5, 31);
		days.put(6, 30);
		days.put(7, 31);
		days.put(8, 30);
		days.put(9, 30);
		days.put(10, 31);
		days.put(11, 30);
		days.put(12, 31);
		// 得到年份
		Integer year = Integer.parseInt(cardCode.subSequence(6, 10).toString());
		// 得到月份
		Integer month = Integer.parseInt(cardCode.subSequence(10, 12).toString());
		// 得到天数
		Integer day = Integer.parseInt(cardCode.subSequence(12, 14).toString());
		// 判断是否是闰年
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {	// 闰年
			// 闰年2月29天
			days.put(2, 29);
		} else {	// 不是闰年
			// 非闰年2月28天
			days.put(2, 28);
		}
		
		// 开始判断号码是否正确
		// 得到当前月份的天数
		Integer thisMonthDay = days.get(month);
		if(thisMonthDay != null) {
			// 当前月份天数不能小于0也不能大于当前月份实际天数，否则有误
			if(day >= 0 && day <= thisMonthDay) {
				System.out.println("身份证号码正确");
			}else {
				System.out.println("身份证有误");
			}
		}else {
			System.out.println("身份证有误");
		}
	}

}
