package com.sgxy.o2o.service;

import java.util.List;

import com.sgxy.o2o.dto.ProgressDto;

public interface ProgressService {
	// ���ݶ���id��ȡ���Ȱ���
	public List<ProgressDto> getProgressByOID(String oid);
	
	// ��ӽ���
	public int addProgress(String oid, String ppid, String content, String pptime);
}
