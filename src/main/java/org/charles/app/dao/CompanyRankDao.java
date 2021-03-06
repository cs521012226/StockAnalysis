package org.charles.app.dao;

import java.util.Date;
import java.util.List;

import org.charles.app.pojo.dto.CompanyRank;
import org.charles.framework.exp.BusinessException;


/**
 * 营业部上榜排名
 * @author Charles
 *
 */
public interface CompanyRankDao {
	
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException;
	
	public void save(CompanyRank p) throws BusinessException ;
	
	public void saveBatch(List<CompanyRank> p) throws BusinessException ;
	
}
