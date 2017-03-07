package com.xjtu.bbs.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xjtu.bbs.domain.User;
import com.xjtu.bbs.util.JdbcUtil;

public class UserDao {
	/**
	 * �û�ע��
	 * @param user
	 * @throws SQLException
	 */
	public void regist(User user) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "insert into user(username,password,gender,email) values(?,?,?,?)";
		queryRunner.update(sql,
				new Object[] { user.getUsername(), user.getPassword(), user.getGender(), user.getEmail() });
	}
	/**
	 * ͨ���û�������������û�
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public User findUserByUsernameAndPassword(String username,String password) throws SQLException{
		User user =null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from user where username=? and password=?";
		user = (User)queryRunner.query(sql, new Object[]{username,password},new BeanHandler(User.class));
		return user;
	}
}
