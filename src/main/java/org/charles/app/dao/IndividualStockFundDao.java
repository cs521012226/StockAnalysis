package org.charles.app.dao;

import java.util.List;

import org.charles.app.pojo.dto.IndividualStockFund;
import org.charles.framework.exp.BusinessException;


/**
 * 营业部上榜排名
 * @author Charles
 *
 */
public interface IndividualStockFundDao {
	
	public void delete() throws BusinessException;
	
	public void save(IndividualStockFund p) throws BusinessException ;
	
	public void saveBatch(List<IndividualStockFund> p) throws BusinessException ;
	
}
