package bean;

import java.sql.Date;

public class Order {
	private long o_id;//¶©µ¥ºÅ
	private String c_id;
	private String d_id;
	private String start;
	private String end;
	private Date time;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String c_id, String d_id) {
		super();
		this.c_id = c_id;
		this.d_id = d_id;
	}
	public Order(String c_id, String d_id, String start, String end, Date time) {
		super();
		this.c_id = c_id;
		this.d_id = d_id;
		this.start = start;
		this.end = end;
		this.time = time;
	}
	public Order(long o_id, String c_id, String d_id, String start, String end, Date time) {
		super();
		this.o_id = o_id;
		this.c_id = c_id;
		this.d_id = d_id;
		this.start = start;
		this.end = end;
		this.time = time;
	}
	public long getO_id() {
		return o_id;
	}
	public void setO_id(long o_id) {
		this.o_id = o_id;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
