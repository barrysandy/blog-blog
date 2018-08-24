package com.xgb.blog.controller.search;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xgb.blog.controller.mystudy.MyStudyController;
import com.xgb.org.common.PageUtils;
import com.xgb.org.domain.Art;
import com.xgb.org.domain.Label;
import com.xgb.org.service.blog.BlogArtService;
import com.xgb.org.service.blog.BlogLabelService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired private BlogArtService blogArtService;
	@Autowired private BlogLabelService blogLabelService;
	
	@Value("${app.pageSize}")
	private Integer pageSize;
	
	@GetMapping("all")
	public String index(HttpServletRequest request) {
		request.setAttribute("search", "");
		return "search/index2";
	}
	
	@GetMapping("")
	public String search(HttpServletRequest request,String search) {
		//Init
		if(search == null) { search = ""; }
		try {
			List<Art> list = blogArtService.getListService(0, pageSize, search,"",-1);
			if(search != null && !"".equals(search)) { list = MyStudyController.getSeachList(list,search,"red"); }
			
			request.setAttribute("list", list);
			request.setAttribute("search", search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "search/index2";
	}
	
	@GetMapping("byLabel")
	public String searchByLabel(HttpServletRequest request,String lid,String typese,Integer cpage) {
		if(lid == null) { lid = ""; }
		if(cpage == null) { cpage = 1; }
		if(typese == null || "".equals(typese)) { typese = ""; }
		try {
			int totalCurrent = blogArtService.getCountByLabelService(typese, lid);
			int totalPage = PageUtils.totalPage(totalCurrent, pageSize);
			int index = (cpage -1) * pageSize;
			
			List<Art> list = blogArtService.getListByLabelService(index, pageSize, typese, -1, lid);
			List<Label> listLable = blogLabelService.getCloudLabels();//Cloud Labels
			
			request.setAttribute("list", list);
			request.setAttribute("listLable", listLable);
			request.setAttribute("typese", typese);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("cpage", cpage);
			request.setAttribute("lid", lid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "search/labelList";
	}

}
