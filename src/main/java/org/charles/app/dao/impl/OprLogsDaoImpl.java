package org.charles.app.dao.impl;

import org.charles.app.dao.OprLogsDao;
import org.charles.app.pojo.OprLogs;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 操纵日志
 * @author Charles
 *
 */
public class OprLogsDaoImpl extends NamedParameterJdbcDaoSupport implements OprLogsDao{
	
	@Override
	public void delete() throws BusinessException {
//		getJdbcTemplate().update("delete from big_trade");
	}

	@Override
	public void save(OprLogs p) throws BusinessException {
		
		SqlHelper.Insert u = SqlHelper.getInsert("opr_logs");
		u.value("log_type", p.getLogType()); ///*类型*/
		u.value("code", p.getCode()); ///*类型*/
		u.value("content", p.getContent()); ///*内容*/
		u.value("create_date", p.getCreateDate()); ///*日期*/

		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
	}
}
