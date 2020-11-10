package service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.DriverDao;

/**
 * Servlet implementation class PicUploadServlet
 */
@WebServlet("/PicUploadServlet")
@MultipartConfig
public class PicUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicUploadServlet() {
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
		
		String d_id=request.getParameter("d_id");
		System.out.println("d_id=="+d_id);
		//��ȡ�ϴ��ļ���
		Part part=request.getPart("headshot");
		//��ȡ�ϴ��ļ���
		String fileName=part.getSubmittedFileName();
		//Ϊ��ֹ�ϴ��ļ����������ļ�����������,string.lastIndexOf('')�ַ����һ�γ��ֵ�λ��
		String newFileName=System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
		//���ϴ����ļ������ڷ�������imgĿ¼��
		String filePath=getServletContext().getRealPath("/img");
		System.out.println("ͷ�񱣴��·��"+filePath);
		File f=new File(filePath);
		if(!f.exists())
			f.mkdirs();
		part.write(filePath+"/"+newFileName);
		
		DriverDao driveDao=new DriverDao();
		
		if(driveDao.isExistHeadshot(d_id)) {
			driveDao.updateHeadshot(d_id,newFileName);
			request.getRequestDispatcher("jsp/Maintenance.jsp?d_id=d_id").forward(request, response);
		}else {
			driveDao.updateHeadshot(d_id,newFileName);
			response.sendRedirect("/curriculum_design/jsp/DriverHp.jsp?d_id=d_id");
		}
	}

}
