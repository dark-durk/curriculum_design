package bean;

import java.sql.Date;

public class Car {
	private String car_id;
	private String d_id;
	private Date purchaseDate;
	private String brand;
	private String color;
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(String car_id) {
		this.car_id=car_id;
	}
	
	public Car(String d_id,Date purchaseDate, String brand, String color) {
		super();
		this.d_id = d_id;
		this.purchaseDate = purchaseDate;
		this.brand = brand;
		this.color = color;
	}
	public Car(String car_id, String d_id, Date purchaseDate, String brand, String color) {
		super();
		this.car_id = car_id;
		this.d_id = d_id;
		this.purchaseDate = purchaseDate;
		this.brand = brand;
		this.color = color;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
