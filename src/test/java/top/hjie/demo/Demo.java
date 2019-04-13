package top.hjie.demo;


public class Demo {
	
	private static Person person = new Person();

	public static void main(String[] args) throws ClassNotFoundException {
		
		
		Class<?> clazz = Class.forName("top.hjie.demo.Person");
		
		
		
		
		System.out.println(666666);
		
		
	}
	

}

class Person {
	
	private String name;
	private String sex;

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

class Student extends Person{
	
	private String like;

	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	
}

