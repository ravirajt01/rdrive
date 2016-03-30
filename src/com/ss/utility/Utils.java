package com.ss.utility;

import java.util.UUID;

import org.jboss.resteasy.util.Base64;


public class Utils {

public static String objectToJsonStirng(Object object) {
		
		return GenericVariables.gson.toJson(object);
		
	}


	public static boolean isEmpty(String s) {
		
		if (s==null || s.isEmpty())
			return true;
		else
			return false;
		
	}


	public static String generateRandomCode() {
		String randomCode = Base64.encodeBytes(UUID.randomUUID()
				.toString().getBytes());
		return(randomCode.trim());
	}
	
	

}
