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

public class DownloadTest {

	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = new File("D:/work/test.doc");
		String fileName = "测试.doc";
		/*	IE的话，通过URLEncoder对filename进行UTF8编码。
		而其他的浏览器（firefox、chrome、safari、opera），则要通过字节转换成ISO8859-1了。*/
		Boolean flag= request.getHeader("User-Agent").toLowerCase().indexOf("like gecko")>0;
		if (request.getHeader("User-Agent").toLowerCase().indexOf("MSIE") > 0 || flag){
			fileName = URLEncoder.encode(fileName, "UTF-8");//IE浏览器
		}else {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		}
		InputStream inputStream = new FileInputStream(file);
		BufferedInputStream bufferedIn = new BufferedInputStream(inputStream);
		response.reset();
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream outputStream = response.getOutputStream();
		BufferedOutputStream bufferedOut = new BufferedOutputStream(outputStream);
		byte data[] = new byte[1024];
		int len = 0;
		while((len=bufferedIn.read(data))>0) {
			bufferedOut.write(data, 0, len);
		}
		outputStream.flush();
		outputStream.close();
		bufferedIn.close();
	}
	
}
