package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sgxy.o2o.dto.OrderDto;

public interface OrderMapper {
	// 根据pid获取数据
	@Select("select * from orderTable where pid=#{pid}")
	public OrderDto getOrderByPID(@Param("pid") String pid);
	
	@Select("select o.*, p.content, p.userName, p.userPhone from orderTable o, parttimejob p where o.uid = #{uid} "
			+ "and o.flag=#{flag} "
			+ "and o.pid = p.pid")
	public List<Map<String, String>> getOrder(@Param("uid") String uid, @Param("flag") String flag);

	// 添加订单：接单操作
	@Insert("insert into orderTable(uid,pid,oid,flag) values(#{uid},#{pid},#{oid},#{flag})")
	int addOrder(@Param("uid") String uid, @Param("pid") String pid,
			@Param("oid") String oid, @Param("flag") String flag);

	// 取消订单
	// 1、删除订单表。 2、修改兼职信息的状态
	@Delete("delete from orderTable where oid=#{oid}")
	int delOrder(@Param("oid") String oid);
	
	// 完成订单
	@Update("update orderTable set flag=#{flag},time=#{time} where oid=#{oid}")
	int updOFlag(@Param("oid") String oid, @Param("flag") String flag,
			@Param("time") String time);
	
	// 获取订单的状态
	@Select("select flag from orderTable where oid=#{oid}")
	String getOFlag(@Param("oid") String oid);
	
}
