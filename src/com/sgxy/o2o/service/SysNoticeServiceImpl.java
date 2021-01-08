package com.sgxy.o2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.SysNoticeDto;
import com.sgxy.o2o.mapper.SysNoticeMapper;

@Service("sysNoticeService")
public class SysNoticeServiceImpl implements SysNoticeService {
	@Autowired
	public SysNoticeMapper sysNoticeMapper;

	@Override
	public List<SysNoticeDto> getSysNotice(String uid) {
		// TODO Auto-generated method stub
		return sysNoticeMapper.getSysNotice(uid);
	}

	@Override
	public int addSysNotice(String ssid, String uid, String desc, String time) {
		// TODO Auto-generated method stub
		return sysNoticeMapper.addSysNotice(ssid, uid, desc, time);
	}
}
