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
public class DeleteBatchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			//设置编码的格式
			req.setCharacterEncoding("UTF-8");
			//接受参数
			String[] ids=req.getParameterValues("id");
			//根据ids批量删除
			MaintainService maintainService=new MaintainService();
			maintainService.deleteBatch(ids);
			//向页面跳转
			req.getRequestDispatcher("/List.action").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
