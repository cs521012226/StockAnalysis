package org.charles.app.dao;

import java.util.List;
import java.util.Map;

import org.charles.framework.define.VOBase;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 公共数据对象DAO
 * @author YeChao
 */
public interface CommonDao {

	
	public <T> List<T> findAll(String sql, RowMapper<T> rowMapper) throws DataAccessException ;
	/**
	 * 查找全部数据返回
	 * @author YeChao
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 * @throws DataAccessException
	 */
	public <T> List<T> findAll(String sql, Map<String, ?> params, RowMapper<T> rowMapper) throws DataAccessException ;
	
	public <T> List<T> findAll(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException ;
	/**
	 * 查询数据并且分页返回List数据  没有rownum
	 * @param sql
	 * @param start
	 * @param count
	 * @param rowMapper
	 * @return
	 * @throws DataAccessException
	 */
	public <T> VOBase findByPage(String sql, int start, int count, RowMapper<T> rowMapper) throws DataAccessException ;
	
	/**
	 * （带参数）查询数据并且分页返回List数据 没有rownum
	 * @param sql	脚本，例如：SELECT * FROM xxx WHERE tlrnbr = :key
	 * @param queryParams	Map类型查询参数，例如：queryParams.put("key", "测试");
	 * @param start	分页开始
	 * @param count	分页大小
	 * @return	分页存放类VOBase
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	public <T> VOBase findByPage(String sql, 
			Map<String, ?> queryParams, int start, int count, RowMapper<T> rowMapper) throws DataAccessException ;
	
	/**
	 * （带参数）查询数据并且分页返回List数据 没有rownum
	 * @param sql	脚本，例如：SELECT * FROM xxx WHERE tlrnbr = ?
	 * @param args	查询参数，例如：new Object[]{ "测试"}
	 * @param start	分页开始
	 * @param count	分页大小
	 * @return	分页存放类VOBase
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	public <T> VOBase findByPage(String sql, Object[] args, int start, int count, RowMapper<T> rowMapper) throws DataAccessException ;
	
	/**
	 * 查询该脚本数据总记录数
	 * @param sql	脚本
	 * @return		总记录数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	public int getCount(String sql) throws DataAccessException;
	/**
	 * 查询该脚本数据总记录数
	 *<p>例：SELECT * FROM xxx WHERE xxx = :aaa</p>
	 * @param sql		脚本
	 * @param queryParams	Map类型查询参数
	 * @return		总记录数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	public int getCount(String sql, Map<String, ?> queryParams) ;
	
	/**
	 * 查询该脚本数据总记录数
	 * <p>例：SELECT * FROM xxx WHERE xxx = ?</p>
	 * @param sql		脚本
	 * @param args		Object数组类型查询参数
	 * @return		总记录数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	public int getCount(String sql, Object[] args) throws DataAccessException ;
	/**
	 * 执行更新操作
	 * @param sql		脚本
	 * @param paramMap	Map类型查询参数
	 * @return			影响或生效的行数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	public int update(String sql, Map<String, Object> paramMap) throws DataAccessException ;
	
	/**
	 * 执行更新
	 * @param sql		脚本
	 * @param paramMap	Object数组类型查询参数
	 * @return			影响或生效的行数
	 * @throws DataAccessException	脚本错误抛该异常
	 */
	public int update(String sql, Object[] args) throws DataAccessException ;
	
	/**
	 * 执行更新
	 * @param sql		脚本
	 * @return			影响或生效的行数
	 * @throws DataAccessException		脚本错误抛该异常
	 */
	public int update(String sql) throws DataAccessException ;
}
