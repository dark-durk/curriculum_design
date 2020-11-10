package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import bean.Driver;
import dao.CarDao;
import dao.ClientDao;
import dao.DriverDao;
import dao.MyJdbcUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String identity=req.getParameter("identity");
		PrintWriter out=resp.getWriter();
		//判断是否成功
		int code=0;
		//判断身份
		if("用户".equals(identity)) {
			//获取被封装的对象
			Client client=this.reqClientObj(req);
			ClientDao clientDao=new ClientDao();
			code=clientDao.insert(client);
		}else {
			DriverDao driverDao=new DriverDao();
			Driver driver=this.reqDriverObj(req);
			code=driverDao.insert(driver);
			//插入车牌号
			CarDao carDao=new CarDao();
			carDao.insertCar(driver.getCar_id(),driver.getD_id());
		}
		if(code>0) {
			resp.sendRedirect("html/Login.html");
		}else
			out.write(" failue");
	}
	//把请求的数据封装成对象
	private Client reqClientObj(HttpServletRequest req) {
		Client client=null;
		String theId=req.getParameter("theId");
		String password=req.getParameter("password");
		client=new Client(theId,password);
		return client;
	}
	private Driver reqDriverObj(HttpServletRequest req) {
		Driver driver=null;
		String d_id=req.getParameter("theId");
		String password=req.getParameter("password");
		String phonenum=req.getParameter("phonenum");
		String name=req.getParameter("name");
		int age=Integer.parseInt(req.getParameter("age"));
		String sex=req.getParameter("sex");
		String car_id=req.getParameter("car_id");
		double car_age=Double.parseDouble(req.getParameter("car_age"));
		driver=new Driver(d_id,password,phonenum,name,age,sex,car_id,car_age);
		return driver;
	}
}
