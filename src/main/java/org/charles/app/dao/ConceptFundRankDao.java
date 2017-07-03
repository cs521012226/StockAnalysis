package org.charles.app.dao;

import java.util.Date;
import java.util.List;

import org.charles.app.pojo.dto.ConceptFundRank;
import org.charles.framework.exp.BusinessException;


/**
 * 营业部上榜排名
 * @author Charles
 *
 */
public interface ConceptFundRankDao {
	
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException;
	
	public void save(ConceptFundRank p) throws BusinessException ;
	
	public void saveBatch(List<ConceptFundRank> p) throws BusinessException ;
	
}
