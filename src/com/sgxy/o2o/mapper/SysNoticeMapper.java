package com.sgxy.o2o.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sgxy.o2o.dto.SysNoticeDto;

public interface SysNoticeMapper {
	// ����uid��ѯ
	@Select("select * from sysnotice where uid=#{uid} order by time desc")
	List<SysNoticeDto> getSysNotice(@Param("uid") String uid);
	
	// �������
	@Insert("insert into sysnotice values(#{ssid},#{uid},#{desc},#{time})")
	int addSysNotice(@Param("ssid") String ssid, @Param("uid") String uid,
			@Param("desc") String desc, @Param("time") String time);
}
