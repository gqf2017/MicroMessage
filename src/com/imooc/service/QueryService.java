package com.imooc.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.bean.Message;
import com.imooc.dao.CommandDao;
import com.imooc.dao.MessageDao;
import com.imooc.entity.Page;
import com.imooc.util.Iconst;
/**
 * 列表相关的业务操作*/
public class QueryService {
	//分页查询所有的数据
	public List<Message> queryMessageList(String command,String description,Page page){
		MessageDao messageDao=new MessageDao();
		Message message=new Message();
		message.setCommand(command);
		message.setDescription(description);
		//根据查询条件查询条数
		int totalNumber=messageDao.count(message);
		//组织分页查询参数
		page.setTotalNumber(totalNumber);
		Map<String,Object> parameter=new HashMap<String, Object>();
		parameter.put("message", message);
		parameter.put("page", page);
		return messageDao.queryMessageList(parameter);
	}
	/*//查询所有的数据
	public List<Message> queryMessageList(String command,String description){
		MessageDao messageDao=new MessageDao();
		return messageDao.queryMessageList(command, description);
	}*/
	//通过指令查询自动回复内容
	public String queryByCommand(String command){
		CommandDao commandDao=new CommandDao();
		List<Command> commandList;
		if(Iconst.HELPA_COMMAND.equals(command)){
			commandList=commandDao.queryCommandList(null, null);
			StringBuilder result=new StringBuilder();
			for(int i=0;i<commandList.size();i++){
				if(i!=0){
					result.append("<br/>");
				}
				result.append("回复["+commandList.get(i).getName()+"]  可以查看: '"+commandList.get(i).getDescription()+"'");
			}
			return result.toString();
			
		}
		commandList=commandDao.queryCommandList(command, null);
		if(commandList.size()>0){
			List<CommandContent> contentList=commandList.get(0).getContentList();
			int i=new Random().nextInt(contentList.size());
			return contentList.get(i).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
	
}
