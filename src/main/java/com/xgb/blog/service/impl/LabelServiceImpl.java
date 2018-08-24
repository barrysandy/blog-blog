package com.xgb.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgb.blog.dao.LabelMapper;
import com.xgb.org.domain.Label;
import com.xgb.org.service.blog.BlogLabelService;


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
@Service("blogLabelService")
public class LabelServiceImpl implements BlogLabelService {

	@Autowired
	LabelMapper labelMapper;

	@Override
	public Label getBeanByIdService(String id) throws Exception {
		return labelMapper.getBeanById(id);
	}

	@Override
	public List<Label> getListService(String artId) throws Exception {
		return labelMapper.getList(artId);
	}

	@Override
	public List<Label> getCloudLabels() {
		return labelMapper.getCloudLabels();
	}


}
