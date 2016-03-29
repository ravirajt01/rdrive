package com.ss.utility;

import org.json.JSONException;
import org.json.JSONObject;

import com.ss.utility.GenericVariables.ExceptionCode;

public class Reply {

	static String reply = "reply";
	static String messageCode = "messsageCode";

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

		System.out.println(jsonObject.toString());
		
		return jsonObject.toString();
	}


}
