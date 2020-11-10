package bean;

public class Driver {
	private String d_id;
	private String password;
	private String phonenum;
	private String name;
	private int age;
	private String sex;
	private String car_id;
	private double car_age;
	private String headshot;//Í·Ïñ

	public String getHeadshot() {
		return headshot;
	}
	public void setHeadshot(String headshot) {
		this.headshot = headshot;
	}
	public Driver() {
		super();
	}
	public Driver(String d_id, String password, String phonenum, String name, int age, String sex, String car_id,
			double car_age) {
		super();
		this.d_id = d_id;
		this.password = password;
		this.phonenum = phonenum;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.car_id = car_id;
		this.car_age = car_age;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public double getCar_age() {
		return car_age;
	}
	public void setCar_age(double car_age) {
		this.car_age = car_age;
	}
	
	public static void main(String[] args) {
//		Driver d=new Driver();
//				d.sex='ÄÐ';
//		System.out.println(d.sex);
	}
}
