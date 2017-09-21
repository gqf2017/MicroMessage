package com.imooc.bean;

public class CommandContent {
	/**
	 * 主键*/
	private String id;
	/**
	 * 内容*/
	private String content;
	/**
	 * 指令id*/
	private String commandId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommandId() {
		return commandId;
	}
	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}
	
}
