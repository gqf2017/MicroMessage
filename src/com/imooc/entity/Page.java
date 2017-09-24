package com.imooc.entity;

public class Page {
	//总条数
	private int totalNumber;
	//当前页数
	private int currentPage;
	//总页数
	private int totalPage;
	//每页数据条数
	private int pageNumber=5;
	//数据库中limit的参数，从第几条开始取
	private int dbIndex;
	//数据库中limit的参数，每页一共多少条
	private int dbNumber;
	//根据当前对象中的属性值计算并设置相关属性
	public void count(){
		int totalPageTemp=this.totalNumber/this.pageNumber;
		int plus=(this.totalNumber%this.pageNumber)==0 ?0:1;
		totalPageTemp+=plus;
		if(totalPageTemp<=0){
			totalPageTemp=1;
		}
		this.totalPage=totalPageTemp;
		//设置当前页数，总页数小于当前页应该将当前页设置为总页数
		if(this.totalPage<this.currentPage){
			this.currentPage=this.totalPage;
		}
		//当前页数如果小于1就将当前页设置为1
		if(this.currentPage<1){
			this.currentPage=1;
		}
		//设置Limit的参数
		this.dbIndex=(this.currentPage-1)*this.pageNumber;
		this.dbNumber=this.pageNumber;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getDbIndex() {
		return dbIndex;
	}
	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}
	public int getDbNumber() {
		return dbNumber;
	}
	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}
	
}
