package org.charles.app.dao;

import java.util.List;

import org.charles.app.pojo.Company;
import org.charles.framework.exp.BusinessException;

/**
 * 营业部、机构
 * @author Charles
 *
 */
public interface CompanyDao {

	public List<Company> findAll(Company param) throws BusinessException;
	
	public Company save(Company param) throws BusinessException;
	
}
