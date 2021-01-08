package com.sgxy.o2o.service;

import java.util.List;

import com.sgxy.o2o.dto.ProgressDto;

public interface ProgressService {
	// 根据订单id获取进度安排
	public List<ProgressDto> getProgressByOID(String oid);
	
	// 添加进度
	public int addProgress(String oid, String ppid, String content, String pptime);
}
