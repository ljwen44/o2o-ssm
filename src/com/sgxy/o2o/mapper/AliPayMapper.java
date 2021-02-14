package com.sgxy.o2o.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface AliPayMapper {
	@Insert("insert into alipay values(#{apid},#{uid},#{time},#{paynum},#{type})")
	int addAlipay(@Param("apid") String apid,@Param("uid") String uid,
			@Param("time") String time, @Param("paynum") String paynum,
			@Param("type") Integer type);
}
