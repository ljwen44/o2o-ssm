package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import com.sgxy.o2o.dto.PartTimeJobDto;

public interface PartJobService {
	// ������ѯ��ְ��Ϣ
	public List<Map<String, String>> getAllJob(String province, String city,
			String block, String salary, Integer page);
	
	// ������ְ
	public int addJob(String pid, String uid, String userName, String userPhone,
			String province, String city, String block,
			String address, String salary,String content,
			String ptime, String pstatus, String precord, Integer day);
	
	// ����uid��ȡ�����ļ�ְ��Ϣ
	public List<PartTimeJobDto> findInfoByUID(String uid);
	
	// ����pid��ȡ��ϸ�ļ�ְ��Ϣ
	public PartTimeJobDto getTimeJobByPID(String pid);
	
	public Map<String, String> getPJob(String pid);
	
	public int getTotal();
	
	public String getPstatus(String pid);
	
	public int delPJob(String pid);
	public int updStatus(String pid, String pstatus);
}
