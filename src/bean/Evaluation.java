package bean;

import java.sql.Date;

public class Evaluation {
	private long e_id;
	private long o_id;
	private Date time;
	private String evaluations;
	private String reply;
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Evaluation(long o_id, Date time, String evaluations) {
		super();
		this.o_id = o_id;
		this.time = time;
		this.evaluations = evaluations;
	}
	
	public Evaluation(long o_id, Date time, String evaluations, String reply) {
		super();
		this.e_id = e_id;
		this.o_id = o_id;
		this.time = time;
		this.evaluations = evaluations;
		this.reply = reply;
	}
	public long getE_id() {
		return e_id;
	}
	public void setE_id(long e_id) {
		this.e_id = e_id;
	}
	public long getO_id() {
		return o_id;
	}
	public void setO_id(long o_id) {
		this.o_id = o_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getEvaluations() {
		return evaluations;
	}
	public void setEvaluations(String evaluations) {
		this.evaluations = evaluations;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
