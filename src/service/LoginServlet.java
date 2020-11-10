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
		//��ȡ�������
		String theId=req.getParameter("theId");
		String password=req.getParameter("password");
		String identity=req.getParameter("identity");
		//��ȡ���ݿ����
		ClientDao clientDao=new ClientDao();
		DriverDao driverDao=new DriverDao();
		
		if(identity.equals("�û�")) {
			if("00000".equals(theId)&&"00000".equals(password)) {
				resp.sendRedirect("jsp/ManagerHP.jsp");
			}else if(clientDao.isExist(theId,password)) {
				req.getSession().setAttribute("theId",theId);
				resp.sendRedirect("jsp/ClientHP.jsp");
			}else
				out.print("<script language='javascript'>alert('�˺��������!!');window.location.href='/curriculum_design/html/Login.html';</script>");  
		}else {
			if(driverDao.isExist(theId,password)) {
				req.getSession().setAttribute("d_id",theId);
				if(driverDao.isExistHeadshot(theId)) {
					//����ͷ�񣬶�����ҳ
					resp.sendRedirect("/curriculum_design/jsp/DriverHp.jsp");
				}else {
					//������ͷ��ת�������ͷ��ҳ��
					resp.sendRedirect("/curriculum_design/jsp/PicUpload.jsp");
				}
			}else
				out.print("<script language='javascript'>alert('�˺��������!!');window.location.href='/curriculum_design/html/Login.html';</script>");  
		}
		
	}
	
}
