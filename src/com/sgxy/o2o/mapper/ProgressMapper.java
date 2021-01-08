package com.sgxy.o2o.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sgxy.o2o.dto.ProgressDto;

public interface ProgressMapper {
	// 根据订单id获取进度安排
	@Select("select * from progress where oid=#{oid}")
	List<ProgressDto> getProgressByOID(@Param("oid") String oid);

	// 添加进度
	@Insert("insert into progress values(#{oid},#{ppid},#{content},#{pptime})")
	int addProgress(@Param("oid") String oid, @Param("ppid") String ppid,
			@Param("content") String content, @Param("pptime") String pptime);
	
}
