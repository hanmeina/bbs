package com.xjtu.bbs.service;

import java.sql.SQLException;

import java.util.List;

import com.xjtu.bbs.dao.AddressDao;
import com.xjtu.bbs.dao.AdminDao;
import com.xjtu.bbs.dao.TypeDao;
import com.xjtu.bbs.dao.UserDao;
import com.xjtu.bbs.domain.Address;
import com.xjtu.bbs.domain.Admin;
import com.xjtu.bbs.domain.Type;
import com.xjtu.bbs.domain.User;
import com.xjtu.bbs.util.Md5Util;

public class BbsService {
	private TypeDao typeDao = new TypeDao();
    private AdminDao adminDao = new AdminDao();
    private UserDao   userDao = new UserDao();
    private AddressDao addressDao = new AddressDao();
  
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
	
}
