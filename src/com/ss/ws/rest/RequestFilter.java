package com.ss.ws.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cft.ws.UserServices;

public class RequestFilter implements Filter {
	
	private static final Logger logger = Logger.getLogger(RequestFilter.class.getName());


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
			logger.info(bodyParam.append(stringBuilder).toString());
		} catch (IOException ex) {
			throw ex;
		}

		body = stringBuilder.toString();
		return body;
	}

	private static void getRequestInfo(HttpServletRequest servletRequest) {

		StringBuilder resource = new StringBuilder("Method : ") ;
		Enumeration<String> en=servletRequest.getParameterNames();
		resource.append(servletRequest.getRemoteAddr()).append(" -> ").append(servletRequest.getMethod()).append(" ").append(servletRequest.getRequestURI())  ;

		resource.append(" -> param : ");
		while(en.hasMoreElements()){
			String param=(String)en.nextElement();
			resource.append(param).append(" : ").append((servletRequest).getParameter(param)).append(", ")  ;
		}

		logger.info(resource.toString());

	}
	
}
