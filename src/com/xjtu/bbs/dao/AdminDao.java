package com.xjtu.bbs.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xjtu.bbs.domain.Admin;
import com.xjtu.bbs.util.JdbcUtil;

public class AdminDao {
   public Admin findAdminByTitle(String title) throws SQLException{
	   Admin admin = null;
	   QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
	   String sql = "select * from  admin where title = ?";
	   admin = (Admin)queryRunner.query(sql,title,new BeanHandler(Admin.class));
	   return admin;
   }
}
