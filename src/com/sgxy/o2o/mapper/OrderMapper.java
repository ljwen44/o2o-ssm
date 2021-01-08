package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sgxy.o2o.dto.OrderDto;

public interface OrderMapper {
	// 根据pid获取数据
	@Select("select * from orderTable where pid=#{pid}")
	public OrderDto getOrderByPID(@Param("pid") String pid);
	
	@Select("select o.*, p.content, p.userName, p.userPhone from orderTable o, parttimejob p where o.uid = #{uid} "
			+ "and o.flag=#{flag} "
			+ "and o.pid = p.pid")
	public List<Map<String, String>> getOrder(@Param("uid") String uid, @Param("flag") String flag);
}
