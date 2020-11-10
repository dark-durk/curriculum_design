package bean;

public class EvaDetails {
	private Evaluation eva;
	private Order order;
	public EvaDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EvaDetails(Evaluation eva, Order order) {
		super();
		this.eva = eva;
		this.order = order;
	}
	public Evaluation getEva() {
		return eva;
	}
	public void setEva(Evaluation eva) {
		this.eva = eva;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
