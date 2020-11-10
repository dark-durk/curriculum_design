package bean;

import java.sql.Date;

public class Maintenance {
	private long m_id;
	private String car_id;
	private String cause;
	private Date time;
	public Maintenance(String car_id, String cause, Date time) {
		super();
		this.car_id = car_id;
		this.cause = cause;
		this.time = time;
	}
	public Maintenance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getM_id() {
		return m_id;
	}
	public void setM_id(long m_id) {
		this.m_id = m_id;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
