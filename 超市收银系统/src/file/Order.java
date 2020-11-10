package file;

import java.io.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JOptionPane;

import jdbc.DBConnection;

public class Order implements Serializable {

	private String gsID;
	private String uAccount;
	private Timestamp gsDate;
	private int gsNum;
	private BigDecimal gsPrice;
	
	public Order() {
	}

	public Order(String gsID, String uAccount, Timestamp gsDate, int gsNum, BigDecimal gsPrice) {
		super();
		this.gsID = gsID;
		this.uAccount = uAccount;
		this.gsDate = gsDate;
		this.gsNum = gsNum;
		this.gsPrice = gsPrice;
	}

	public static void main(String[] args) {
		String fileName = "D:\\java实验\\IO\\order.txt";
		//writeFile(fileName);
		readFile(fileName);
	}

	public static void writeFile(String fileName) {
		File file = new File(fileName);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		Connection conn = DBConnection.getConn();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			String sql = "select * from goodsSales";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.gsID = rs.getString(1);
				order.uAccount = rs.getString(2);
				order.gsDate = rs.getTimestamp(3);
				order.gsNum = rs.getInt(4);
				order.gsPrice = rs.getBigDecimal(5);
				Order order1 = new Order(order.gsID, order.uAccount, order.gsDate, order.gsNum, order.gsPrice);
				oos.writeObject(order1);
			}
			JOptionPane.showMessageDialog(null, "打印成功");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBConnection.closeConn(rs, psmt, conn);
		}
	}

	public static void readFile(String fileName) {
		File file = new File(fileName);
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			Order order = (Order) ois.readObject();
		//	System.out.println(order.gsID + "  " + order.uAccount);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null)
					ois.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
