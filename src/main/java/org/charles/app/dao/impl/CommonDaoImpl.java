package org.charles.app.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.charles.app.dao.CommonDao;
import org.charles.framework.define.VOBase;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 简单的对JdbcDaoSupport与
 * NamedParameterJdbcDaoSupport进行封装
 * @date 2015年2月28日
 * @author YeChao
 */
public class CommonDaoImpl extends NamedParameterJdbcDaoSupport implements CommonDao{
	
	/**
	 * 通用日志对象
	 */
	protected Logger logger = Logger.getLogger(getClass());
	
	/**
	 * 在原sql上构建分页SQL 没有rownum
	 * @param sql		原脚本
	 * @param start		分页开始
	 * @param count		分页大小
	 * @return	构建后的脚本
	 */
	private String buildPageSql(String sql, int start, int count){
		return "select tmp_.* from (" + sql +") tmp_ limit " + start + ", " + count;
	}
	
	/**
	 * 在原sql脚本上构建查询总记录数脚本
	 * @param sql	原脚本
	 * @return
	 */
	private String buildCountSql(String sql){
		return "select count(1) as totalNum from (" + sql + ") tmp_";
	}
	
	/**
	 * （无参数）查找全部数据返回
	 * @author YeChao
	 * @param sql
	 * @param rowMapper
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public <T> List<T> findAll(String sql, RowMapper<T> rowMapper) throws DataAccessException {
		return getJdbcTemplate().query(sql, rowMapper);
	}
	/**
	 * 查找全部数据返回
	 * @author YeChao
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public <T> List<T> findAll(String sql, Map<String, ?> params, RowMapper<T> rowMapper) throws DataAccessException {
		return getNamedParameterJdbcTemplate().query(sql, params, rowMapper);
	}
	@Override
	public <T> List<T> findAll(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
		return getJdbcTemplate().query(sql, args, rowMapper);
	}
	/**
	 * 查询数据并且分页返回List数据  没有rownum
	 * @param sql
	 * @param start
	 * @param count
	 * @param rowMapper
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public <T> VOBase findByPage(String sql, int start, int count, RowMapper<T> rowMapper) throws DataAccessException {
		int totalRows = getCount(sql);
		List<T> items = findAll(buildPageSql(sql, start, count), rowMapper);
		return new VOBase(totalRows, items);
	}
	
	/**
	 * （带参数）查询数据并且分页返回List数据 没有rownum
	 * @param sql	脚本，例如：SELECT * FROM xxx WHERE tlrnbr = :key
	 * @param queryParams	Map类型查询参数，例如：queryParams.put("key", "测试");
	 * @param start	分页开始
	 * @param count	分页大小
	 * @return	分页存放类VOBase
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	@Override
	public <T> VOBase findByPage(String sql, 
			Map<String, ?> queryParams, int start, int count, RowMapper<T> rowMapper) throws DataAccessException {
		int totalRows = getCount(sql, queryParams);
		List<?> items = findAll(buildPageSql(sql, start, count), queryParams, rowMapper);
		return new VOBase(totalRows, items);
	}
	
	/**
	 * （带参数）查询数据并且分页返回List数据 没有rownum
	 * @param sql	脚本，例如：SELECT * FROM xxx WHERE tlrnbr = ?
	 * @param args	查询参数，例如：new Object[]{ "测试"}
	 * @param start	分页开始
	 * @param count	分页大小
	 * @return	分页存放类VOBase
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	@Override
	public <T> VOBase findByPage(String sql, Object[] args, int start, int count, RowMapper<T> rowMapper) throws DataAccessException {
		int totalRows = getCount(sql, args);
		List<?> items = findAll(buildPageSql(sql, start, count), args, rowMapper);
		return new VOBase(totalRows, items);
	}
	
	/**
	 * 查询该脚本数据总记录数
	 * @param sql	脚本
	 * @return		总记录数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	@Override
	public int getCount(String sql) throws DataAccessException{
		return getJdbcTemplate().queryForObject(buildCountSql(sql), Integer.class);
	}
	/**
	 * 查询该脚本数据总记录数
	 *<p>例：SELECT * FROM xxx WHERE xxx = :aaa</p>
	 * @param sql		脚本
	 * @param queryParams	Map类型查询参数
	 * @return		总记录数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	@Override
	public int getCount(String sql, Map<String, ?> queryParams) {
		return getNamedParameterJdbcTemplate().queryForObject(buildCountSql(sql), queryParams, Integer.class);
	}
	
	/**
	 * 查询该脚本数据总记录数
	 * <p>例：SELECT * FROM xxx WHERE xxx = ?</p>
	 * @param sql		脚本
	 * @param args		Object数组类型查询参数
	 * @return		总记录数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	@Override
	public int getCount(String sql, Object[] args) throws DataAccessException {
		return getJdbcTemplate().queryForObject(buildCountSql(sql), args, Integer.class);
	}
	/**
	 * 执行更新操作
	 * @param sql		脚本
	 * @param paramMap	Map类型查询参数
	 * @return			影响或生效的行数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	@Override
	public int update(String sql, Map<String, Object> paramMap) throws DataAccessException {
		return getNamedParameterJdbcTemplate().update(sql, paramMap);
	}
	
	/**
	 * 执行更新
	 * @param sql		脚本
	 * @param paramMap	Object数组类型查询参数
	 * @return			影响或生效的行数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	@Override
	public int update(String sql, Object[] args) throws DataAccessException {
		return getJdbcTemplate().update(sql, args);
	}
	
	/**
	 * 执行更新
	 * @param sql		脚本
	 * @return			影响或生效的行数
	 * @throws DataAccessException		脚本错误抛该异常
	 */
	@Override
	public int update(String sql) throws DataAccessException {
		return getJdbcTemplate().update(sql);
	}
	
}
