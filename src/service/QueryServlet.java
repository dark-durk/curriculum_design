package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Driver;
import dao.*;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
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
		
		String d_id=request.getParameter("id");
		System.out.println("id===="+d_id);
		//获取某司机的所有信息
		DriverDao d=new DriverDao();
		Driver driver=d.getDriver(d_id);
		if(driver.getCar_id()==null) {
			out.print("<script>alert('不存在该名司机');</script>");
		}else {
			//获取某司机的所有评价
			QueryDao q=new QueryDao();
			List<String> evaList=q.getAllEvaOfDriver(d_id);
			request.setAttribute("evaList",evaList);
			request.setAttribute("driver",driver);
			System.out.println("eva=="+evaList);
			request.getRequestDispatcher("jsp/HomePage.jsp").forward(request, response);
		}
		System.out.println("driver==="+driver);
	}

}
