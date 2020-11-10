package bean;

public class Complaint {
	private long cp_id;
	private String c_id;
	private String d_id;
	private String complaints;
	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Complaint(String c_id, String d_id, String complaints) {
		super();
		this.c_id = c_id;
		this.d_id = d_id;
		this.complaints = complaints;
	}
	public Complaint(long cp_id, String c_id, String d_id, String complaints) {
		super();
		this.cp_id = cp_id;
		this.c_id = c_id;
		this.d_id = d_id;
		this.complaints = complaints;
	}
	public long getCp_id() {
		return cp_id;
	}
	public void setCp_id(long cp_id) {
		this.cp_id = cp_id;
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
	public String getComplaints() {
		return complaints;
	}
	public void setComplaints(String complaints) {
		this.complaints = complaints;
	}
	
}
