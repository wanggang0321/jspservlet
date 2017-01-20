package com.ppdtbb.seven;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseTest1 {
	
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = "";
		if(request.getHeader("User-Agent").toLowerCase().indexOf("chrome") > 0
				|| request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1"); //火狐
		} else {
			filename = URLEncoder.encode(filename, "UTF-8"); //IE
		}
		File file = new File(filename);
		InputStream in = new FileInputStream(file);
		BufferedInputStream bin = new BufferedInputStream(in);
		response.reset();
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		byte data[] = new byte[1024];
		int len = 0;
		while((len=bin.read(data))>0) {
			outputStream.write(data, 0, len);
		}
		outputStream.flush();
		outputStream.close();
		bin.close();
	}
	
}
