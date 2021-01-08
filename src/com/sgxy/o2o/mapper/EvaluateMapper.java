package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sgxy.o2o.dto.EvaluateDto;

public interface EvaluateMapper {
	// 根据uid获取对应的评价列表
	@Select("select e.*, u.userName, u.avatar from evaluate e, user u where e.ruid=#{uid} "
			+ "and u.uid=e.uid "
			+ "order by e.etime desc "
			+ "limit 5")
	List<Map<String, String>> getEvaListByUID(@Param("uid") String uid);
	
	// 根据oid获取评价
	@Select("select * from evaluate where oid=#{oid}")
	EvaluateDto getEvaluateByOID(@Param("oid") String oid);
	
	// 添加评价
	@Insert("insert into evaluate values(#{eid},#{content},#{oid},#{uid},#{etime},#{erate},#{ruid})")
	int addEvaluate(@Param("eid") String eid, @Param("content") String content,
			@Param("oid") String oid, @Param("uid") String uid, @Param("etime") String etime,
			@Param("erate") String erate, @Param("ruid") String ruid);

}
