package com.imooc.dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;
import com.mysql.jdbc.Connection;
/**
 * 和message表相关的数据库操作*/
public class MessageDao {
	//根据查询条件查询消息列表
	public List<Message> queryMessageList(String command,String description){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession=null;
		List<Message> messageList=new ArrayList<Message>();
		try {
			sqlSession=dbAccess.getSqlSession();
			Message message=new Message();
			message.setCommand(command);
			message.setDescription(description);
			//1.通过SqlSession执行SQL语句
			messageList=sqlSession.selectList("Message.queryMessageList",message);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return messageList;
	}
	//根据id啥删除一条数据
	public void deleteOne(int id){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession=null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//1.通过SqlSession执行SQL语句
			sqlSession.delete("Message.deleteOne",id);
			//需要提交事务
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	//批量删除
	public void deleteBatch(List<Integer> ids){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession=null;
		try {
			sqlSession=dbAccess.getSqlSession();
			//通过SqlSession执行SQL语句
			sqlSession.delete("Message.deleteBatch",ids);
			//需要提交事务
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
				
	/**
	 * 根据查询条件查询消息列表
	 * @throws ClassNotFoundException 
	 * @throws SQLException */
	/*public List<Message> queryMessageList(String command,String description){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message","root","malingjuan");
			StringBuilder sql=new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1");
			List<String> paramList=new ArrayList<String>();
			if(command!=null&&!" ".equals(command.trim())){
				sql.append(" and COMMAND=?");
				paramList.add(command);
			}
			//此处有问题
			if(description!=null&&!"".equals(description.trim())){
				sql.append(" and DESCRIPTION like ?'%'");
				paramList.add(description);
			}
			
			PreparedStatement pre=con.prepareStatement(sql.toString());
			for(int i=0;i<paramList.size();i++){
				pre.setString(i+1, paramList.get(i));
			}
			//System.out.println(sql.toString());
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
			return messageList;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	*/
}
