package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.*;

/**
 * Servlet implementation class YueCheServlet
 */
@WebServlet("/ClientHPServlet")
public class ClientHPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientHPServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String type = request.getParameter("type");
//		String c_id = request.getParameter("theId");
		//跳转到约车页面
		if ("yueche".equals(type)) {
			request.getRequestDispatcher("jsp/YueChe.jsp").forward(request, response);
		}
		//处理约车页面的生成订单信息插入到数据库中
		if("createOrder".equals(type)) {
			this.CreateOrder(request);
		}
		
		//跳转到订单页面
		if("order".equals(type)) {
			this.getOrderList(request);
			request.getRequestDispatcher("jsp/Order.jsp").forward(request, response);
		}
			
		//跳转到个人信息页面
		if ("clientInfo".equals(type)) {
			this.getClientInfo(request);
			request.getRequestDispatcher("jsp/ClientInfo.jsp").forward(request, response);
		}
		//修改个人信息，修改成功后重定向到个人信息页面
		if("changeClientInfo".equals(type)) {
			int code=this.changeClientInfo(request);
			if(code>0) {
				this.getClientInfo(request);
				request.getRequestDispatcher("jsp/ClientInfo.jsp").forward(request, response);
			}else {
				System.out.println("failue");
			}
		}
	}
	
	//获取个人信息
	private void getClientInfo(HttpServletRequest request) {
		String c_id = request.getParameter("theId");
		ClientDao clientDao = new ClientDao();
		Client client = clientDao.getClient(c_id);
		request.setAttribute("client", client);
	}
	
	//修改个人信息
	private int changeClientInfo(HttpServletRequest request) {
		int code=0;
		String theId=request.getParameter("theId");
		String password=request.getParameter("password");
		String phonenum=request.getParameter("phonenum");
		int age=Integer.parseInt(request.getParameter("age"));
		String sex=request.getParameter("sex");
		Client client=new Client(theId,password,phonenum,age,sex);
		ClientDao clientDao = new ClientDao();
		code=clientDao.changeClient(client);
		return code;
	}
	//生成订单信息，调用dao
	private int CreateOrder(HttpServletRequest request) {
		int code=0;
		String theId=request.getParameter("theId");
		String d_id=request.getParameter("d_id");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		Date time=java.sql.Date.valueOf(request.getParameter("time"));
		
		Order order=new Order(theId,d_id,start,end,time);
		ClientDao clientDao = new ClientDao();
		code=clientDao.insertOrder(order);
		return code;
	}
	//获取用户所有订单信息，利用request传到前端,返回值为空
	public void getOrderList(HttpServletRequest request) {
		String theId=request.getParameter("theId");
		ClientDao clientDao = new ClientDao();
		List<Order> list=clientDao.getOrders(theId);
		request.setAttribute("list",list);
		request.setAttribute("c_id",theId);
	}
}
