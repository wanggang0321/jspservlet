package com.ppdtbb.seven;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseTest3 {
	
	public void download(HttpServletRequest request, HttpServletResponse response) {
		File file = new File("D:/");
		String filename = "";
		if(request.getHeader("User-Agent").toLowerCase().indexOf("chrome")>0
				||request.getHeader("User-Agent").toLowerCase().indexOf("firefox")>0) {
			
		}
	}
	
}
