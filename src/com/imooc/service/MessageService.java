package com.imooc.service;

import java.sql.SQLException;
import java.util.List;

import com.imooc.bean.Message;
import com.imooc.dao.MessageDao;
/**
 * 列表相关的业务操作*/
public class MessageService {
	public List<Message> queryMessageList(String command,String description){
		MessageDao messageDao=new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
}
