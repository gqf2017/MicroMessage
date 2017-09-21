package com.imooc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.service.MaintainService;
import com.imooc.service.QueryService;
//单条删除控制层
@SuppressWarnings("serial")
public class DeleteOneServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			//设置编码的格式
			req.setCharacterEncoding("UTF-8");
			//接受参数
			String id=req.getParameter("id");
			//根据id删除一条信息
			MaintainService maintainService=new MaintainService();
			maintainService.deleteOne(id);
			//向页面跳转
			req.getRequestDispatcher("/List.action").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
