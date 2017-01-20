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

public class ResponseTest4 {
	
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = new File("D:/test/1.doc");
		String filename = "";
		if(request.getHeader("User-Agent").toLowerCase().indexOf("chrome")>0
				|| request.getHeader("User-Agent").toLowerCase().indexOf("firefox")>0) {
			filename = new String(filename.getBytes(), "UTF-8"); //firefox
		} else {
			filename = URLEncoder.encode(filename, "ISO-8859-1");
		}
		InputStream in = new FileInputStream(file);
		BufferedInputStream bin = new BufferedInputStream(in);
		response.reset();
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		OutputStream out = response.getOutputStream();
		BufferedOutputStream bout = new BufferedOutputStream(out);
		byte datas[] = new byte[1024];
		int len = 0;
		while((len=bin.read(datas))>0) {
			bout.write(datas, 0, len);
		}
		bout.flush();
		bout.close();
		bin.close();
		in.close();
	}
	
}
