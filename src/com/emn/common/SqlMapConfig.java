package com.emn.common;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfig {
	
	private static SqlMapClient sqlMapper;

	static {
		 try {
			 Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			 sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
			 reader.close(); 
		 } catch (IOException e) {
			 // Fail fast.
			 throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		 }
	}
	
	public static SqlMapClient getSqlMapInstance() throws Exception{
		return sqlMapper;
	} 
	
	@SuppressWarnings("unchecked")
	public static List<SqlMapUsableObj> selectListEmn (String selectQuery) throws SQLException {
		return sqlMapper.queryForList(selectQuery); 
	}
	
	@SuppressWarnings("unchecked")
	public static List<SqlMapUsableObj> selectListEmn(String selectQuery, SqlMapUsableObj sqlMapUsable) throws SQLException {
		return sqlMapper.queryForList(selectQuery, sqlMapUsable);
	}
	
	@SuppressWarnings("unchecked")
	public static List<SqlMapUsableObj> selectListEmn(String selectQuery, Map<Object, Object> map) throws SQLException {
		return sqlMapper.queryForList(selectQuery, map);
	}
	
	public static SqlMapUsableObj selectOneEmn (String selectQuery) throws SQLException {
		return (SqlMapUsableObj) sqlMapper.queryForObject(selectQuery); 
	}
	
	public static SqlMapUsableObj selectOneEmn(String selectQuery, SqlMapUsableObj sqlMapUsable) throws SQLException {
		return (SqlMapUsableObj) sqlMapper.queryForObject(selectQuery, sqlMapUsable);
	}
	
	public static SqlMapUsableObj selectOneEmn(String selectQuery, Map<Object, Object> map) throws SQLException {
		return (SqlMapUsableObj) sqlMapper.queryForObject(selectQuery, map);
	}
		
	public static Integer insertEmn (String insertQuery, SqlMapUsableObj sqlMapUsable) throws SQLException {
		return (Integer) sqlMapper.insert(insertQuery, sqlMapUsable);
	}
	 
	public static int updateEmn (String updateQuery, SqlMapUsableObj sqlMapUsable) throws SQLException {
		return sqlMapper.update(updateQuery, sqlMapUsable);
	}
	  
	public static void deleteEmn (String deleteQuery, SqlMapUsableObj sqlMapUsable) throws SQLException {
		sqlMapper.delete(deleteQuery, sqlMapUsable);
	}
	

}
