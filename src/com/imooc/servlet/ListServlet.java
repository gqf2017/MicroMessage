package com.imooc.servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.jms.MessageListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.bean.Message;
import com.imooc.entity.Page;
import com.imooc.service.QueryService;
import com.mysql.jdbc.Connection;

/**
 * 简单的页面初始化控制*/
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet{
	//分页查询
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置编码的格式
		req.setCharacterEncoding("UTF-8");
		//接受参数
		String command=req.getParameter("command");
		String description=req.getParameter("description");
		String currentPage=req.getParameter("currentPage");
		//查询消息列表并传给页面
		Page page=new Page();
		Pattern pattern=Pattern.compile("[0-9]{1,9}");
		if(currentPage==null ||!pattern.matcher(currentPage).matches()){
			page.setCurrentPage(1);
		}else{
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		QueryService messageService=new QueryService();
		req.setAttribute("messageList",messageService.queryMessageList(command, description,page));
		//向页面传值
		req.setAttribute("page", page);
		req.setAttribute("command", command);
		req.setAttribute("description",description );
		//向页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}
	/*查询但是不分页
	 * @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置编码的格式
		req.setCharacterEncoding("UTF-8");
		//接受参数
		String command=req.getParameter("command");
		String description=req.getParameter("description");
		//向页面传值
		req.setAttribute("command", command);
		req.setAttribute("description",description );
		//查询消息列表并传给页面
		QueryService messageService=new QueryService();
		req.setAttribute("messageList",messageService.queryMessageList(command, description));
		//向页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
