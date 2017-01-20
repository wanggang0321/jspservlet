package com.ppdtbb.seven;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseHeaderTest {
	
	public String download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		File file = new File("D:/work/1.doc");
		
		String fileName = "测试.doc";
		
		try {
			if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox")>0
					|| request.getHeader("User-Agent").toLowerCase().indexOf("chrome")>0) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1"); //火狐浏览器
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8"); //IE浏览器
			}
			InputStream inputStream = new FileInputStream(file);
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			byte data[] = new byte[1024];
			int len = 0;
			while((len=inputStream.read(data)) > 0) {
				outputStream.write(data, 0, len);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
