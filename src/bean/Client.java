package bean;

public class Client {
	private String c_id;
	private String password;
	private String phonenum;
	private int age;
	private String sex;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String c_id, String password) {
		super();
		this.c_id = c_id;
		this.password = password;
	}
	
	public Client(String c_id, String password, String phonenum, int age, String sex) {
		super();
		this.c_id = c_id;
		this.password = password;
		this.phonenum = phonenum;
		this.age = age;
		this.sex = sex;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
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
	
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
