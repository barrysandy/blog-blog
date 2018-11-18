package com.xgb.blog.controller.myfun;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xgb.org.common.PageUtils;
import com.xgb.org.common.StringUtils;
import com.xgb.org.domain.Art;
import com.xgb.org.domain.Label;
import com.xgb.org.service.blog.BlogArtService;
import com.xgb.org.service.blog.BlogLabelService;

@Controller
@RequestMapping("/myfun")
public class MyFunController {
	
	@Autowired private BlogArtService blogArtService;
	@Autowired private BlogLabelService blogLabelService;
	
	@Value("${app.pageSize}")
	private Integer pageSize;
	
	@Value("${art.typese2}")
	private String setTypese;
	
	
	@GetMapping("/index")
	public String index(HttpServletRequest request,String search,String typese,Integer cpage) {
		//Init
		if(search == null) { search = ""; }
		if(cpage == null) { cpage = 1; }
		if(typese == null || "".equals(typese)) { typese = setTypese; }
		try {
			int totalCurrent = blogArtService.getCountService(search, typese);
			int totalPage = PageUtils.totalPage(totalCurrent, pageSize);
			int index = (cpage -1) * pageSize;
			List<Art> list = blogArtService.getListService(index, pageSize, search,typese,-1);
			if(search != null && !"".equals(search)) { list = getSeachList(list,search,"red"); }
			List<Label> listLable = blogLabelService.getCloudLabels();
			List<Art> listClick = list;
			List<Art> list1 = new ArrayList<>();
			List<Art> list2 = new ArrayList<>();
			List<Art> list3 = new ArrayList<>();
			if(list != null){
				if(list.size() > 0){
					for (int i = 0; i < list.size(); i++) {
						if(i == 0 || i%3 == 0){
							list1.add(list.get(i));
						}
						if(i%3 == 1){
							list2.add(list.get(i));
						}
						if(i%3 == 2){
							list3.add(list.get(i));
						}
					}
				}
			}
			request.setAttribute("list", list);
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			request.setAttribute("listLable", listLable);
			request.setAttribute("listClick", listClick);
			request.setAttribute("search", search);
			request.setAttribute("typese", typese);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("cpage", cpage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "myfun/list";
	}
	
	
	@GetMapping("/info/{id}")
	public String index(HttpServletRequest request,@PathVariable("id") String id) {
		try {
			Art bean = blogArtService.getBeanByIdService(id);
			if(bean != null) {
				//组装本bean的图集
				String text = bean.getContent();
				List<String> images = StringUtils.getListImg(text);
				System.err.println(images);
				List<Label> listLable = blogLabelService.getCloudLabels();//Cloud Labels
				List<Label> lables = bean.getLabels();//Current Art Labels
				List<Art> list = blogArtService.getListService(0, 10, "","",-1);//Arts
				blogArtService.updateViewsService(bean.getId(), bean.getViews(),bean.getViews() + 1);
				
				Art previous = blogArtService.getThePreviousBeanService(bean.getCreateTime());
				Art next = blogArtService.getTheNextBeanService(bean.getCreateTime());
				
				//随机推荐
				List<Art> listRandom = blogArtService.getRandomByTypesService(10, bean.getTypese());
				request.setAttribute("listRandom", listRandom);
				
				request.setAttribute("images", images);
				request.setAttribute("list", list);
				request.setAttribute("lables", lables);
				request.setAttribute("listLable", listLable);
				request.setAttribute("previous", previous);
				request.setAttribute("next", next);
			}
			
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "myfun/info";
	}
	
	public static List<Art> getSeachList(List<Art> list,String seach,String color){
		if(list != null) {
			if(list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String title = list.get(i).getTitle();
					if(title != null && !"".equals(title)) {
						title = StringUtils.setStrColor(title,seach,color);
					}
					
					String descM = list.get(i).getDescM();
					if(descM != null && !"".equals(descM)) {
						descM = StringUtils.setStrColor(descM,seach,color);
					}
					
					list.get(i).setTitle(title);
					list.get(i).setDescM(descM);
				}
			}
		}
		return list;
	}
}
