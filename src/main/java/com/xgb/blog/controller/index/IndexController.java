package com.xgb.blog.controller.index;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xgb.org.domain.Art;
import com.xgb.org.domain.Label;
import com.xgb.org.service.blog.BlogArtService;
import com.xgb.org.service.blog.BlogLabelService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired private BlogArtService blogArtService;
	@Autowired private BlogLabelService blogLabelService;
	
	@Value("${art.typese1}")
	private String setTypese;
	
	@GetMapping("")
	public String index(HttpServletRequest request) {
		try {
			List<Art> list = blogArtService.getListService(0, 10, "",setTypese,-1,1);
			List<Label> listLable = blogLabelService.getCloudLabels();
			List<Art> listClick = blogArtService.getListService(0, 10, "",setTypese,1,1);
			request.setAttribute("list", list);
			request.setAttribute("listLable", listLable);
			request.setAttribute("listClick", listClick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index/index";
	}

}
