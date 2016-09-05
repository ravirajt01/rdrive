package com.ss.ws.rest;  

import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;  

import com.cft.ws.UserServices;
import com.javatpoint_old.Operation;
public class Test{  
    public static void main(String[] args){  
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
       // TestServices e = (TestServices) ((AbstractApplicationContext) context).getBean("opBean");  
        Operation e = (Operation) ((AbstractApplicationContext) context).getBean("opBean");  
       // System.out.println("calling msg...");  
      //  UserServices e = new UserServices() ;
      //  Operation e = new Operation();
        e.msg();  
        
       /* System.out.println("calling m...");  
        e.m(1);  
        System.out.println("calling k...");  
        e.k();  */
    }  
}  