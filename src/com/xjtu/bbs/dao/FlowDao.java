package com.xjtu.bbs.dao;

import java.sql.SQLException;

import java.util.Date;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xjtu.bbs.domain.Flow;
import com.xjtu.bbs.util.JdbcUtil;


public class FlowDao {
	//根据日期查询流量
	public Flow findFlowByDate(Date date) throws SQLException{
		Flow flow = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from flow where historydate = ?";
		flow = (Flow) runner.query(sql,new java.sql.Date(date.getTime()),new BeanHandler(Flow.class));
		return flow;
	}
}




