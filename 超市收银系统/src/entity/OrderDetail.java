package entity;

import java.util.ArrayList;

//import jdbc.DBOperation;
//import jdbc.MyConnection;

public class OrderDetail {

	private String gID;
	private String gName;
	private String gType;
	private java.math.BigDecimal gsdPrice;
	private java.math.BigDecimal gPrice;
	private String gUnit;
	private int gsdID;
	private double gsdNum;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public double getGsdNum() {
		return gsdNum;
	}

	public void setGsdNum(double gsdNum) {
		this.gsdNum = gsdNum;
	}

	public String getgID() {
		return gID;
	}

	public String getgName() {
		return gName;
	}

	public String getgType() {
		return gType;
	}

	public java.math.BigDecimal getgPrice() {
		return gPrice;
	}

	public String getgUnit() {
		return gUnit;
	}

	public java.math.BigDecimal getGsdPrice() {
		return gsdPrice;
	}

	public int getGsdID() {
		return gsdID;
	}

	public void setGsdPrice(java.math.BigDecimal gsdPrice) {
		this.gsdPrice = gsdPrice;
	}

	public void setGsdID(int gsdID) {
		this.gsdID = gsdID;
	}

	public void setgID(String gID) {
		this.gID = gID;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public void setgType(String gType) {
		this.gType = gType;
	}

	public void setgPrice(java.math.BigDecimal gPrice) {
		this.gPrice = gPrice;
	}

	public void setgUnit(String gUnit) {
		this.gUnit = gUnit;
	}
	
	/*public ArrayList<Goods> getGoodsList(){
		ArrayList<Goods> list=new ArrayList<>();
		Goods goods=myOpr.getGoods(gID1);
		
	}*/
}
