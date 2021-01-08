package com.sgxy.o2o.service;

import java.util.List;

import com.sgxy.o2o.dto.SysNoticeDto;

public interface SysNoticeService {
	// 根据uid查询
	public List<SysNoticeDto> getSysNotice(String uid);
	
	// 添加数据
	public int addSysNotice(String ssid, String uid, String desc, String time);
}
