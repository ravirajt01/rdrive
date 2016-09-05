package com.ss.ws.rest;  
import java.util.logging.Logger;

import javax.ejb.AfterBegin;
import javax.ejb.BeforeCompletion;
import javax.ws.rs.GET;  
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;  
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.Response;

import com.cft.ws.VehicleServices;
import com.ss.utility.Reply;
import com.ss.utility.GenericVariables.ExceptionCode;
@Path("/")  
public class TestServices {  
  // This method is called if HTML and XML is not requested  
	
	private static final Logger logger = Logger.getLogger(TestServices.class.getName());

	@GET  
	  @Produces(MediaType.APPLICATION_JSON)  
	  @Path("/hello")  
	  public String helloM() { 
		 
		  logger.info("getTask method invoked");
		
		  String reply = "{ \"records\":[ {\"Name\":\"Alfreds Futterkiste\",\"City\":\"Berlin\",\"Country\":\"Germany\"} ] }";
		  
			return Reply.formatReply(reply,ExceptionCode.SCS);

		  
		//return Response.status(500).entity(reply).build();

		
	    
	  }
	
  @GET  
  @Produces(MediaType.APPLICATION_JSON)  
  @Path("/task/{taskId}/{taskName}")  
  public Response getTask(@PathParam("taskId") Integer taskId , 
		  @PathParam("taskName") String taskName , 
		  @QueryParam(value = "taskNum") Integer taskNum) { 
	  msg();
	  logger.info("getTask method invoked");
	String  result=  taskId + taskNum + "" +taskName;  
	return Response.status(500).entity(result).build();

	
    
  }
  
  public void msg(){logger.info("msg method invoked");}  
  public int m(){logger.info("m method invoked");return 2;}  
  public int k(){logger.info("k method invoked");return 3;}  

  // This method is called if XML is requested  
  /* 
  @GET  
  @Produces(MediaType.TEXT_XML)  
  public String sayXMLHello() {  
    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";  
  }  
  
  // This method is called if HTML is requested  
 @GET  
  @Produces(MediaType.TEXT_HTML)  
  public String sayHtmlHello() {  
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"  
        + "<body><h1>" + "Hello Jersey HTML" + "</h1></body>" + "</html> ";  
  } */ 
  
  
}   