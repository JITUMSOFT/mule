package com.framework.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HandleConfig
 */

public class HandleConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleConfig() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String config;
		config =request.getParameter("comment");
		if(config.contains("mule")){
			    File statText = new File("C://Software//mule-config.xml");
	            FileOutputStream is = new FileOutputStream(statText);
	            OutputStreamWriter osw = new OutputStreamWriter(is);    
	            Writer w = new BufferedWriter(osw);
	            w.write(config);
	            w.close();
		}
		
		String result=	new ParseMule().validateXMLSchema("C://Software//mule.xsd", "C://Software//mule-config.xml");	
		response.getWriter().write(result);
		
		
	}
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String config;
		config =request.getParameter("comment");
		if(config.contains("mule")){
			    File statText = new File("C://Software//mule-config.xml");
	            FileOutputStream is = new FileOutputStream(statText);
	            OutputStreamWriter osw = new OutputStreamWriter(is);    
	            Writer w = new BufferedWriter(osw);
	            w.write(config);
	            w.close();
		}
		
		
		config=config.replace("\r\n","<br>");
		
		
		
		response.getWriter().append(new ParseMule().validateXMLSchema("C://Software//mule.xsd", "C://Software//mule-config.xml"));
		
		String result=new ParseMule().validateXMLSchema("C://Software//mule.xsd", "C://Software//mule-config.xml");
		request.setAttribute("result", result);
		request.setAttribute("configs",config);
		request.getRequestDispatcher("resultPage.jsp").forward(request, response);
		System.out.println(config);
	}

}
