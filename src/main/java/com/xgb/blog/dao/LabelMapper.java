package com.xgb.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.xgb.org.domain.Label;

@Repository
public interface LabelMapper {

	@Select("select id,name,sort from t_label where id = #{id}")
	Label getBeanById(@Param("id") String id);
	
	@Select("SELECT id,name,sort FROM t_label WHERE id in(SELECT label_id FROM t_art_label WHERE art_id = #{artId} )")
	List<Label> getList(@Param("artId") String artId);
	
	@Select("SELECT id,name,sort FROM t_label LIMIT 0,12 ")
	List<Label> getCloudLabels();
}
