package org.tatasu.ajax;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tatasu.model.LogsDao;
import org.tatasu.model.LogsDaoImpl;

import org.json.JSONArray;
import org.json.JSONObject;

public class LogsViewServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1L;

	public LogsViewServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(getAllLogsJson());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected String getLogsByIdJson(int id) {
		LogsDao dao = new LogsDaoImpl();
		return new JSONObject(dao.getOnceById(id)).toString();		
	}
	
	protected String getAllLogsJson() {
		LogsDao dao = new LogsDaoImpl();
		return new JSONArray(dao.getList()).toString();		
	}
}
