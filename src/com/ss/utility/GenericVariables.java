package com.ss.utility;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import java.util.EnumSet;

public class GenericVariables {

	public static Gson gson = new GsonBuilder().create();

	public enum ExceptionCode{SCS,RNF,ISE, MNA, UAE, UNAU, NAU, UNE ,RAE };
	
	public enum ROLE{Admin,User,Vendor}
	//public EnumSet<ROLE> ROLES = EnumSet.noneOf(ROLE.class);

	

}
