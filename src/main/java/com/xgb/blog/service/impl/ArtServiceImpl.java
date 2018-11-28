package com.xgb.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xgb.blog.dao.ArtMapper;
import com.xgb.org.domain.Art;
import com.xgb.org.service.blog.BlogArtService;


/**
 * SpringBoot 事务配置
 * <p>Title: 事务配置</p>
 * <p>Description: </p>
 *	第一种方式使用注解：
 *		第一步在启动类添加注解开启事务支持（@EnableTransactionManagement），
 *  	第二步在实现接口添加事务注解（@Transactional()[类级别配置表示对其下所有方法都进行配置事务]，@Transactional(readOnly=true)[方法级别配置表示本方法进行配置事务]）
 *  
 *  第二种方式使用AOP全局事务配置切面（参考切面类com.xgb.org.config.TransationAdviceConfig）
 *  	参考网页：https://segmentfault.com/q/1010000015095590/a-1020000015097236
 *  	
 * @author XuGongBin
 * @version 1.0.0
 * @date 2018/7/16 16:20
 */
//@Transactional()
@Service("blogArtService")
public class ArtServiceImpl implements BlogArtService {

	@Autowired
	ArtMapper artMapper;


	@Transactional(readOnly=true)
	@Override
	public Art getBeanByIdService(String id) {
		return artMapper.getBeanById(id);
	}

	@Override
//	@Transactional(readOnly=true)
	public List<Art> getListService(int index, int pageSize,String search,String typese,int sort) {
		System.err.println("getListService");
		//PageHelper.startPage(index, pageSize,typese);
		List<Art> list = artMapper.getList(search,typese,sort,index,pageSize);
		return list;
	}

	@Override
//	@Transactional(readOnly=true)
	public int getCountService(String search,String typese) {
		return artMapper.getCount(search,typese);
	}

	@Override
	public int updateViewsService(String id, int views, int viewsNew) throws Exception {
		return artMapper.updateViews(id, views, viewsNew);
	}

	@Override
	public List<Art> getListByLabelService(int index, int pageSize, String typese, int sort, String lid)
			throws Exception {
		return artMapper.getListByLabel(index, pageSize, typese, sort, lid);
	}

	@Override
	public int getCountByLabelService(String typese, String lid) throws Exception {
		return artMapper.getCountByLabel(typese, lid);
	}

	@Override
	public Art getThePreviousBeanService(String time) throws Exception {
		return artMapper.getThePreviousBean(time);
	}

	@Override
	public Art getTheNextBeanService(String time) throws Exception {
		return artMapper.getTheNextBean(time);
	}

	@Override
	public List<Art> getRandomByTypesService(int size, String typese) throws Exception {
		return artMapper.getRandomByTypes(size, typese);
	}


}
