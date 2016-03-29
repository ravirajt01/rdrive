package com.javatpoint;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public  class InitialLoader  extends HttpServlet  {

	public static ApplicationContext ctx = null;
	static {

		System.out.println("before initialised .. applicationContext");
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("after .. applicationContext");
	}
	
	
	private void Init(ServletConfig confg) {
		
		System.out.println("this is in init method");
	}
	
}
