package com.xjtu.bbs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xjtu.bbs.domain.Type;
import com.xjtu.bbs.util.JdbcUtil;

public class TypeDao {
	/**
	 * ²éÑ¯ËùÓÐ°æ¿é
	 * @return
	 * @throws SQLException
	 */
  public List<Type> findAllType() throws SQLException{
	  List<Type> typeList = null;
	  QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
	  String sql = "select * from type";
	   typeList = (List<Type>) queryRunner.query(sql, new BeanListHandler(Type.class));
       return typeList;
  }
}
