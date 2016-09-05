package com.javatpoint;

import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ss.exceptions.CustomExceptionMapper;



public  class InitialLoader  extends HttpServlet  {

	private static final Logger logger = Logger.getLogger(CustomExceptionMapper.class.getName());

	public static ApplicationContext ctx = null;
	static {

		logger.info("before initialised .. applicationContext");
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger.info("after .. applicationContext");
	}
	
	private void Init(ServletConfig confg) {
		
		//logger.info("this is in init method");
	}
	
}
