package com.sgxy.o2o.service;

import java.util.List;

import com.sgxy.o2o.dto.SysNoticeDto;

public interface SysNoticeService {
	// ����uid��ѯ
	public List<SysNoticeDto> getSysNotice(String uid);
	
	// �������
	public int addSysNotice(String ssid, String uid, String desc, String time);
}
