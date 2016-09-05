package com.ss.utility;

import java.util.UUID;
import java.util.logging.Logger;

import org.jboss.resteasy.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.ws.rest.Authenticator;


public class Utils {
	
	private static final Logger logger = Logger.getLogger(Utils.class.getName());


	public static String objectToJsonStirng(Object object) {


		String reply = null ;
		try{
			reply = GenericVariables.gson.toJson(object);
		}catch(Exception ex){
			
			logger.warning("Parsing error for reply - level1");
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				reply = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}	

		return reply;

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
