package com.xjtu.bbs.dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xjtu.bbs.domain.Topic;
import com.xjtu.bbs.util.JdbcUtil;


public class TopicDao {
	//���ݰ���ʱ���ѯ��������
	public Topic findTopicByTypeAndNewDate(int typeId) throws SQLException{
		Topic topic = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from topic where type_id = ? order by time desc";
		topic = (Topic) runner.query(sql,typeId,new BeanHandler(Topic.class));
		return topic;
	}
	//���ݰ��ȡ��������
	public int getTopicNumByType(int typeId) throws SQLException{
		int topicNum = 0;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select count(*) from topic where type_id = ?";
		Object[] array = (Object[]) runner.query(sql,typeId,new ArrayHandler());
		Long temp = (Long) array[0];
		topicNum = temp.intValue();
		return topicNum;
	}
	//���ݰ���ѯ����
	public List<Topic> findTopicByType(int typeId) throws SQLException{
		List<Topic> topicList = new ArrayList<Topic>();
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from topic where type_id = ? order by time desc";
		topicList = (List<Topic>) runner.query(sql,typeId,new BeanListHandler(Topic.class));
		return topicList;
	}
}




