package com.ss.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GenericVariables {

	public static Gson gson = new GsonBuilder().create();
	
	public enum ExceptionCode{SCS,RNF,ISE, MNA, UAE, UNAU, NAU, UNE };
}
