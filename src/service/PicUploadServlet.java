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
		//获取上传文件域
		Part part=request.getPart("headshot");
		//获取上传文件名
		String fileName=part.getSubmittedFileName();
		//为防止上传文件重名，对文件进行重命名,string.lastIndexOf('')字符最后一次出现的位置
		String newFileName=System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
		//将上传的文件保存在服务器的img目录下
		String filePath=getServletContext().getRealPath("/img");
		System.out.println("头像保存的路径"+filePath);
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
