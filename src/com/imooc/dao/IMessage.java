package com.imooc.dao;

import java.util.List;
import java.util.Map;

import com.imooc.bean.Message;

/**
 * 与Message配置文件相对应的接口*/
public interface IMessage {
	//public List<Message> queryMessageList(Message message);
	public int count(Message message);
	public List<Message> queryMessageList(Map<String,Object> parameter);
}
