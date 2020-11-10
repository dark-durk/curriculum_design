package service;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import bean.*;
import dao.*;

/**
 * Servlet implementation class TextServlet
 */
@WebServlet("/TextServlet")
public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TextServlet() {
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
		System.out.println("txttype======="+type);
		if ("evaluation".equals(type)) {
			this.addEva(request);
		}
		if ("complaint".equals(type)) {
			this.addComp(request);
		}
		if ("reply".equals(type)) {
			System.out.println("reply before");
			if(this.addReply(request)>0) {
				System.out.println("�ظ��ɹ�");
			}else
				System.out.println("reply��ô����");
		}
	}

	// ����dao�������۲��뵽���ݿ���
	public int addEva(HttpServletRequest request) {
		int code = 0;
		long o_id = Long.parseLong(request.getParameter("o_id"));
		Date time = (java.sql.Date.valueOf(request.getParameter("time")));
		String evaluations = (request.getParameter("evaluations"));

		Evaluation eva = new Evaluation(o_id, time, evaluations);
		EvaluationDao evaDao = new EvaluationDao();
		code=evaDao.insertEva(eva);
		return code;
	}

	// ����dao����Ͷ��ԭ����뵽���ݿ���
	public int addComp(HttpServletRequest request) {
		int code = 0;
		String c_id = request.getParameter("c_id");
		String d_id = request.getParameter("d_id");
		String complaints = (request.getParameter("complaints"));
		Complaint comp = new Complaint(c_id, d_id, complaints);
		ComplaintDao compDao = new ComplaintDao();
		code=compDao.insertComp(comp);
		return code;
	}

	// ����dao���ѻظ����۲��뵽���ݿ���
	public int addReply(HttpServletRequest request) {
		int code = 0;
		long e_id = Long.valueOf(request.getParameter("e_id"));
		String reply = request.getParameter("reply");
		EvaluationDao evaDao = new EvaluationDao();
		code=evaDao.insertReply(e_id, reply);
		return code;
	}
}
