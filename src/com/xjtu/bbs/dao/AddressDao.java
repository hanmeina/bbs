package com.xjtu.bbs.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xjtu.bbs.domain.Address;
import com.xjtu.bbs.util.JdbcUtil;

public class AddressDao {
    /**
     * 根据ip查询归属地
     * @param ip
     * @return
     * @throws SQLException
     */
	public Address findAddressByIP(String ip) throws SQLException {
		// TODO Auto-generated method stub
		Address address = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from address where ip = ?";
		address = (Address)queryRunner.query(sql, ip,new BeanHandler(Address.class));
		return address;
	}

}
