package com.ppdtbb.jspservlet.servlet471;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitInsuranceInfo extends HttpServlet {
	
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = new File("D:/1.txt");
		String fileName = "一.txt";
		if(request.getHeader("User-Agent").toLowerCase().indexOf("chrome")>0
				||request.getHeader("User-Agent").toLowerCase().indexOf("firefox")>0) {
			fileName = new String(fileName.getBytes(), "ISO-8859-1"); //firefox
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8"); //IE
		}
		InputStream in = new FileInputStream(file);
		BufferedInputStream bin = new BufferedInputStream(in);
		response.reset();
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream out = new BufferedOutputStream(response.getOutputStream());
		byte data[] = new byte[1024];
		int len = 0;
		while((len=bin.read(data))>0) {
			out.write(data, 0, len);
		}
		out.flush();
		out.close();
		bin.close();
	}
	
}
