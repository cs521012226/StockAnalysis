package org.charles.test.define;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

public class JUnit4ClassRunner extends SpringJUnit4ClassRunner{
	
	static {
		try {
			Log4jConfigurer.initLogging("classpath:config/log4j/log4j.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}
	
}
