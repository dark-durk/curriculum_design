package service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import bean.Car;
import bean.Driver;
import bean.Maintenance;
import bean.Order;
import dao.CarDao;
import dao.DriverDao;
import dao.MaintenanceDao;

/**
 * Servlet implementation class DriverServlet
 */
@WebServlet("/DriverHPServlet")
@MultipartConfig
public class DriverHPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverHPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String type = request.getParameter("type");
		String d_id=request.getParameter("d_id");
		
		if("maint".equals(type)) {
			//处理维修信息
			if(this.addMaint(request)>0) {
				request.getRequestDispatcher("jsp/Maintenance.jsp").forward(request, response);
			}
			else
				System.out.println("维修信息提交出错");
		}
		System.out.println("type="+type);
		if("carinfo".equals(type)) {
			if(this.updateCarInfo(request)>0) {
				request.getRequestDispatcher("jsp/ModifyCarInfo.jsp").forward(request, response);
			}
			else
				System.out.println("修改车辆信息出错");
		}
	}
	//调用dao，生成维修信息
	private int addMaint(HttpServletRequest request) {
		int code=0;
		String car_id=request.getParameter("car_id");
		String cause=request.getParameter("cause");
		Date time=java.sql.Date.valueOf(request.getParameter("time"));
		
		Maintenance maint=new Maintenance(car_id,cause,time);
		MaintenanceDao mDao = new MaintenanceDao();
		code=mDao.insertMaint(maint);
		return code;
	}
	public int updateCarInfo(HttpServletRequest request) {
		int code=0;
		String d_id=request.getParameter("d_id");
		Date purchaseDate=java.sql.Date.valueOf(request.getParameter("purchaseDate"));
		String brand=request.getParameter("brand");
		String color=request.getParameter("color");
		Car car=new Car(d_id,purchaseDate,brand,color);
		CarDao carDao=new CarDao();
		code=carDao.updateCar(car);
		return code;
	}
//	//调用，插入头像
//	private int addHeadshot(HttpServletRequest request) {
//		int code=0;
//		//获取文件路径
//		String d_id=request.getParameter("d_id");
//		
//		DriverDao driveDao=new DriverDao();
//		if(driveDao.isExistHeadshot(d_id)) {
//			code=0;
//		}else {
//			request.getRequestDispatcher("").forward(request, response);
//			String fileName=request.getParameter("");
//			driveDao.insertHeadshot(fileName);
//		}
//		return code;
//	}
}
