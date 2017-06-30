package org.charles.app.dao;

import java.util.List;

import org.charles.app.pojo.Stock;
import org.charles.framework.exp.BusinessException;

/**
 * 股票
 * @author Charles
 *
 */
public interface StockDao {
	
	public List<Stock> findAll(Stock param) throws BusinessException;
	
	public Stock save(Stock param) throws BusinessException;
	
}
