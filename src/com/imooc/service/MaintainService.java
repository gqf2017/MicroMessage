package com.imooc.service;

import java.util.ArrayList;
import java.util.List;

import com.imooc.dao.MessageDao;
//处理相关的业务操作
public class MaintainService {
	//根据id删除一条数据
	public void deleteOne(String id){
		if(id!=null && !"".equals(id)){
			MessageDao messageDao=new MessageDao();
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
	//批量删除数据
	public void deleteBatch(String[] ids){
		MessageDao messageDao=new MessageDao();
		List<Integer> idList=new ArrayList<Integer>();
		if(ids!=null&&ids.length!=0)
		{
			for(String id:ids){
				idList.add(Integer.valueOf(id));
			}
			messageDao.deleteBatch(idList);
		}else{
			System.out.println("没有可以删除的数据.....");
		}
		
	}
}
