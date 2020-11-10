package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.*;

/**
 * Servlet implementation class ManagerHPServlet
 */
@WebServlet("/ManagerHPServlet")
public class ManagerHPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerHPServlet() {
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
		
		String type = request.getParameter("type");
		System.out.println("type==="+type);
		//��ʾ����˾����Ϣ
		if("driver".equals(type)) {
			this.getDriverInfo(request);
			request.getRequestDispatcher("jsp/ShowAllDriver.jsp").forward(request, response);
		}
		//ɾ��ĳ��˾������ô��ȡ����id��ͨ��hidden��
		if("deleteDriver".equals(type)) {
			String d_id=request.getParameter("id");
			System.out.println("id===="+d_id);
			DriverDao d=new DriverDao();
			d.deletDriver(d_id);
			System.out.println("delete");
			request.getRequestDispatcher("jsp/ShowAllDriver.jsp").forward(request, response);
		}
		//��ʾ���г�����Ϣ
		if("showcar".equals(type)) {
			System.out.println("car");
			CarDao carDao=new CarDao();
			List<Car> carList=carDao.getAllCar();
			request.setAttribute("carList",carList);
			request.getRequestDispatcher("jsp/ShowAllCar.jsp").forward(request, response);
		}
		if("showeva".equals(type)) {
			List<EvaDetails> detailList=this.evaDetail();
//			System.out.println("detailList===="+detailList);
			
			request.setAttribute("detailList",detailList);
			request.getRequestDispatcher("jsp/ShowAllEva.jsp").forward(request, response);
		}
		if("compnum".equals(type)) {
			ComplaintDao cDao=new ComplaintDao();
			Map<String,Integer> compMap=cDao.countComp();
//			System.out.println(compMap);
			request.setAttribute("compMap",compMap);
			request.getRequestDispatcher("jsp/Count.jsp").forward(request, response);
		}
	}
	//����dao��ȡ����˾����Ϣ��������ǰ��
	public void getDriverInfo(HttpServletRequest request) {
		DriverDao d=new DriverDao();
		List<Driver> driverList=d.getAllDriver();
		request.setAttribute("driverList",driverList);
	}
	//�ȵõ�һ�����۵ļ��ϣ���ȡ������Ķ����ŴӶ������еõ��û���˾����id��Ȼ�󷵻�һ�����ϡ�������+id
	public List<EvaDetails> evaDetail() {
		List<EvaDetails> detailList=new ArrayList<>();
		List<Evaluation> evaList=new ArrayList<>();
		EvaluationDao evaDao=new EvaluationDao();
		evaList=evaDao.getAllEvaluation();
//		System.out.println("evaList==="+evaList);
		
		for(Evaluation eva:evaList) {
			Long o_id=eva.getO_id();
			ClientDao cDao=new ClientDao();
			Order order=cDao.getTheIds(o_id);
			//����һ������
			EvaDetails ed=new EvaDetails(eva,order);
			detailList.add(ed);
		}
		return detailList;
	}
}
