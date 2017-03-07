package com.xjtu.bbs.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.xjtu.bbs.dao.AddressDao;
import com.xjtu.bbs.dao.AdminDao;
import com.xjtu.bbs.dao.FlowDao;
import com.xjtu.bbs.dao.ReplyDao;
import com.xjtu.bbs.dao.TopicDao;
import com.xjtu.bbs.dao.TypeDao;
import com.xjtu.bbs.dao.UserDao;
import com.xjtu.bbs.domain.Address;
import com.xjtu.bbs.domain.Admin;
import com.xjtu.bbs.domain.Flow;
import com.xjtu.bbs.domain.Reply;
import com.xjtu.bbs.domain.Topic;
import com.xjtu.bbs.domain.Type;
import com.xjtu.bbs.domain.User;
import com.xjtu.bbs.util.Md5Util;

public class BbsService {
	private TypeDao typeDao = new TypeDao();
    private AdminDao adminDao = new AdminDao();
    private UserDao   userDao = new UserDao();
    private AddressDao addressDao = new AddressDao();
    private FlowDao flowDao = new FlowDao();
	private TopicDao topicDao = new TopicDao();
	private ReplyDao replyDao = new ReplyDao();
	
	/**
	 * 根据主题查询所有回复
	 * @param topicId
	 * @return
	 * @throws Exception
	 */
		public List<Reply> findReplyByTopic(int topicId) throws Exception{
			try {
				return replyDao.findReplyByTopic(topicId);
			} catch (SQLException e) {
				throw new Exception();
			}
		}
		
		/**
		 * 根据版块查询主题
		 * @param typeId
		 * @return
		 * @throws Exception
		 */
		public List<Topic> findTopicByType(int typeId) throws Exception{
			try {
				List<Topic> topicList =  topicDao.findTopicByType(typeId);
				for(Topic topic : topicList){
					int replyNum = replyDao.getReplyNumByTopic(topic.getId());
					topic.setReplyNum(replyNum);
				}
				return topicList;
			} catch (SQLException e) {
				throw new Exception();
			}
		}
	
		/**
		 * 根据日期查询流量
		 * @param date
		 * @return
		 * @throws Exception
		 */
		public Flow findFlowByDate(Date date) throws Exception{
			try {
				return flowDao.findFlowByDate(date);
			} catch (SQLException e) {
				throw new Exception();
			}
		}
		
		/**
		 * 根据版块更新点击数
		 * @param typeId
		 * @throws Exception
		 */
		public void updateClickByType(int typeId) throws Exception{
			try {
				typeDao.updateClickByType(typeId);
			} catch (SQLException e) {
				throw new Exception();
			}
		}
    /**
     * 根据IP查询归属地
     * @param ip
     * @return
     * @throws Exception
     */
  	public Address findAddressByIP(String ip) throws Exception{
  		try {
  			return addressDao.findAddressByIP(ip);
  		} catch (SQLException e) {
  			throw new Exception();
  		}
  	}
    /**
	 * 通过用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
     * @throws Exception 
	 * @throws SQLException
	 */
	public User findUserByUsernameAndPassword(User user) throws Exception {
		String md5Password = Md5Util.encodeByMd5(user.getPassword());
		try {
			return userDao.findUserByUsernameAndPassword(user.getUsername(), md5Password);
		} catch (SQLException e) {
			throw new Exception();
		}
		
		
	}
    
    /**
	 * 查询所有版块
	 * 
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public List<Type> findAllType() throws Exception {
		try {
			List<Type> typeList =  typeDao.findAllType();
			for(Type type : typeList){
			   Admin admin = adminDao.findAdminByTitle(type.getTitle());	
			   //取得版块的主题数
				int topicNum = topicDao.getTopicNumByType(type.getId());
				//取得版块的最新主题
				Topic topic = topicDao.findTopicByTypeAndNewDate(type.getId());
				type.setTopic(topic);
				type.setTopicNum(topicNum);
			    type.setAdmin(admin);
			}
			return typeList;
		} catch (SQLException e) {
			throw new Exception();
		}
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @throws Exception 
	 * @throws SQLException
	 */
	public void regist(User user) throws Exception{
		try {
			//将明文密码进行MD5单向加密
			String md5Password = Md5Util.encodeByMd5(user.getPassword());
			user.setPassword(md5Password);
			userDao.regist(user);
		} catch (SQLException e) {
			throw new Exception();
		}
	}

	public boolean checkOnline(User user, List<String> usernameList) {
		boolean flag = false;
		if(usernameList!=null && usernameList.size()==0){
			//不在线
			flag = false;
			//加入集合
			usernameList.add(user.getUsername());
		
		}else{
			if(usernameList.contains(user.getUsername())){
				//在线
				flag = true;
			}else{
				//不在线
				flag = false;
				//加入集合
				usernameList.add(user.getUsername());
			}
				
		}
		return flag;
	}
	  
	/**
	 * 判段该用户是否点击过false表示没有点击过，true表示点击过
	 * @param typeId
	 * @param typeIdList
	 * @return
	 */
		public boolean isClicked(String typeId, List<String> typeIdList) {
			boolean flag = false;
			if(typeIdList!=null && typeIdList.size()==0){
				flag = false;
				typeIdList.add(typeId);
			}else{
				//以前点击过
				if(typeIdList.contains(typeId)){
					flag = true;
				}else{
					//以前没有点击过
					flag = false;
					typeIdList.add(typeId);
				}
			}
			return flag;
		}
}
