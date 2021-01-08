package com.sgxy.o2o.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SignMapper {
	// ǩ��
	@Insert("insert into sign values(#{sid},#{uid},#{stime})")
	int addSign(@Param("sid") String sid, @Param("uid") String uid, @Param("stime") String stime);

	// ����uid��ȡǩ������
	@Select("select stime from sign where uid=#{uid}")
	List<String> getSign(@Param("uid") String uid);
}
