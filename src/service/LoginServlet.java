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

import dao.ClientDao;
import dao.DriverDao;
import dao.MyJdbcUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		//获取请求参数
		String theId=req.getParameter("theId");
		String password=req.getParameter("password");
		String identity=req.getParameter("identity");
		//获取数据库对象
		ClientDao clientDao=new ClientDao();
		DriverDao driverDao=new DriverDao();
		
		if(identity.equals("用户")) {
			if("00000".equals(theId)&&"00000".equals(password)) {
				resp.sendRedirect("jsp/ManagerHP.jsp");
			}else if(clientDao.isExist(theId,password)) {
				req.getSession().setAttribute("theId",theId);
				resp.sendRedirect("jsp/ClientHP.jsp");
			}else
				out.print("<script language='javascript'>alert('账号密码错误!!');window.location.href='/curriculum_design/html/Login.html';</script>");  
		}else {
			if(driverDao.isExist(theId,password)) {
				req.getSession().setAttribute("d_id",theId);
				if(driverDao.isExistHeadshot(theId)) {
					//存在头像，定向到首页
					resp.sendRedirect("/curriculum_design/jsp/DriverHp.jsp");
				}else {
					//不存在头像，转发到添加头像页面
					resp.sendRedirect("/curriculum_design/jsp/PicUpload.jsp");
				}
			}else
				out.print("<script language='javascript'>alert('账号密码错误!!');window.location.href='/curriculum_design/html/Login.html';</script>");  
		}
		
	}
	
}
