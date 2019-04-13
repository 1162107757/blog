package top.hjie.demo;

import java.util.ArrayList;

public class SetTest{
	
	public static void main(String[] args) {
		
		
		ArrayList<Integer> one = new ArrayList<>();
		one.add(11);
		one.add(22);
		one.add(33);
		one.add(44);
		
		ArrayList<Integer> two = new ArrayList<>();
		two.add(11);
		two.add(22);
		two.add(33);
		two.add(55);
		
		
		
		String[] arr = new String[two.size()];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = two.get(i).toString();
		}
		
		for (String string : arr) {
			System.out.println(string);
		}
	}
	
}


