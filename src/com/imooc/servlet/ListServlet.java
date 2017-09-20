package com.imooc.servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.MessageListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.bean.Message;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
/**
 * 简单的页面初始化控制*/
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			req.setCharacterEncoding("UTF-8");
			String command=req.getParameter("command");
			String description=req.getParameter("description");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message","root","malingjuan");
			StringBuilder sql=new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1");
			List<String> paramList=new ArrayList<String>();
			if(command!=null&&!" ".equals(command.trim())){
				sql.append("and COMMAND=?");
				paramList.add(command);
			}
			if(description!=null&&!"".equals(description.trim())){
				sql.append("and DESCRIPTION like '%'?'%'");
				paramList.add(description);
			}
			PreparedStatement pre=(PreparedStatement) con.prepareStatement(sql.toString());
			for(int i=0;i<paramList.size();i++){
				pre.setString(i+1, paramList.get(i));
			}
			ResultSet rs=pre.executeQuery();
			List<Message> messageList=new ArrayList();
			while(rs.next()){
				Message message=new Message();
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
				messageList.add(message);
			}
			req.setAttribute("messageList",messageList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
