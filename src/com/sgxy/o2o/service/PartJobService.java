package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import com.sgxy.o2o.dto.PartTimeJobDto;

public interface PartJobService {
	// ������ѯ��ְ��Ϣ
	public List<Map<String, String>> getAllJob(String province, String city,
			String block, String minSalary, String maxSalary, Integer page);
	
	// ������ְ
	public int addJob(String pid, String uid, String userName, String userPhone,
			String province, String city, String block,
			String address, String minSalary, String maxSalary,
			String startTime, String endTime, String content,
			String ptime, String pstatus, String precord);
	
	// ����uid��ȡ�����ļ�ְ��Ϣ
	public List<PartTimeJobDto> findInfoByUID(String uid);
	
	// ����pid��ȡ��ϸ�ļ�ְ��Ϣ
	public PartTimeJobDto getTimeJobByPID(String pid);
	
	public Map<String, String> getPJob(String pid);
	
	public int getTotal();
}
