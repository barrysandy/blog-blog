package com.xgb.blog.controller.myfun;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myfun")
public class MyFunController {
	
	
	@GetMapping("/index")
	public String index(HttpServletRequest request) {
		try {
			request.setAttribute("1", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "myfun/list";
	}
}
