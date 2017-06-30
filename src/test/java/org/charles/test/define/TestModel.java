package org.charles.test.define;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/config/spring/spring-*.xml")
@TransactionConfiguration(transactionManager="txManager", defaultRollback=false)
public class TestModel  extends AbstractTransactionalJUnit4SpringContextTests{
	protected Logger logger = Logger.getLogger(getClass());
}
