package com.ss.ws.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		getRequestInfo((HttpServletRequest)servletRequest);
		filterChain.doFilter(servletRequest, servletResponse);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	public static String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder bodyParam = new StringBuilder("Body Param : ");

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
			System.out.println(bodyParam.append(stringBuilder));
		} catch (IOException ex) {
			throw ex;
		}

		body = stringBuilder.toString();
		return body;
	}

	private static void getRequestInfo(HttpServletRequest servletRequest) {
		System.out.println("Client Addr : " + servletRequest.getRemoteAddr()+":"+servletRequest.getRemotePort());
		System.out.println("Request URI : "+servletRequest.getRequestURI());
		System.out.println("Method Type : "+servletRequest.getMethod());

		Enumeration<String> en=servletRequest.getParameterNames();
		StringBuilder inputParams = new StringBuilder("Query Param : ") ;
		StringBuilder inputParam = new StringBuilder() ;
		while(en.hasMoreElements())
		{
			String param=(String)en.nextElement();
			inputParam.append(param).append(" : ").append((servletRequest).getParameter(param)).append(", ")  ;
		}
		System.out.println(inputParams.append(inputParam));
	/*	try {
			getBody( servletRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
}
