package com.xgb.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.xgb.org.domain.Art;

@Repository
public interface ArtMapper {

	//@Select("select id,title,image,descM,content,typese,isResouce,url,createTime,updateTime,statuses from t_art where id = #{id}")
	Art getBeanById(@Param("id") String id);
	
	List<Art> getList(@Param("search") String search,@Param("typese") String typese,@Param("sort") int sort,
			@Param("index") int index, @Param("pageSize") int pageSize,@Param("statuses") int statuses);
	
	int getCount(@Param("search") String search,@Param("typese") String typese);
	
	@Update("UPDATE t_art SET views = #{viewsNew} WHERE id = #{id} AND views = #{views} ")
	int updateViews(@Param("id") String id,@Param("views") int views,@Param("viewsNew") int viewsNew);
	
	List<Art> getListByLabel(@Param("index") int index, @Param("pageSize") int pageSize,
			@Param("typese") String typese, @Param("sort") int sort,@Param("lid") String lid);
	
	int getCountByLabel(@Param("typese") String typese, @Param("lid") String lid);
	
	@Select("SELECT id,title FROM t_art WHERE createTime < #{time} LIMIT 0,1")
	Art getThePreviousBean(@Param("time") String time);
	
	@Select("SELECT id,title FROM t_art WHERE createTime > #{time} LIMIT 0,1")
	Art getTheNextBean(@Param("time") String time);
	
	@Select("SELECT id,title FROM t_art WHERE typese = #{typese} ORDER BY rand() LIMIT 0,#{size}")
	List<Art> getRandomByTypes(@Param("size") int size, @Param("typese") String typese);
}
