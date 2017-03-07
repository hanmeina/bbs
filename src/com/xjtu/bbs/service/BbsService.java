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
	 * ���������ѯ���лظ�
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
		 * ���ݰ���ѯ����
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
		 * �������ڲ�ѯ����
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
		 * ���ݰ����µ����
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
     * ����IP��ѯ������
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
	 * ͨ���û�������������û�
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
	 * ��ѯ���а��
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
			   //ȡ�ð���������
				int topicNum = topicDao.getTopicNumByType(type.getId());
				//ȡ�ð�����������
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
	 * �û�ע��
	 * @param user
	 * @throws Exception 
	 * @throws SQLException
	 */
	public void regist(User user) throws Exception{
		try {
			//�������������MD5�������
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
			//������
			flag = false;
			//���뼯��
			usernameList.add(user.getUsername());
		
		}else{
			if(usernameList.contains(user.getUsername())){
				//����
				flag = true;
			}else{
				//������
				flag = false;
				//���뼯��
				usernameList.add(user.getUsername());
			}
				
		}
		return flag;
	}
	  
	/**
	 * �жθ��û��Ƿ�����false��ʾû�е������true��ʾ�����
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
				//��ǰ�����
				if(typeIdList.contains(typeId)){
					flag = true;
				}else{
					//��ǰû�е����
					flag = false;
					typeIdList.add(typeId);
				}
			}
			return flag;
		}
}
