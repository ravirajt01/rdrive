package com.ss.utility;

import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import com.cft.ws.VehicleServices;
import com.ss.utility.GenericVariables.ExceptionCode;

public class Reply {
	
	private static final Logger logger = Logger.getLogger(Reply.class.getName());


	static final String reply = "reply";
	static final String messageCode = "messsageCode";

	public static String formatReply(Object object, ExceptionCode code) {

		JSONObject jsonObject = new JSONObject();
		String replyString = Utils.objectToJsonStirng(object);
		
		try {
			jsonObject.put(messageCode, code);
			if(!Utils.isEmpty(replyString))
				jsonObject.put(reply, replyString);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		logger.info(jsonObject.toString());
		return jsonObject.toString();
	}


}
