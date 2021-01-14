package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import com.sgxy.o2o.dto.PartTimeJobDto;

public interface PartJobService {
	// 条件查询兼职信息
	public List<Map<String, String>> getAllJob(String province, String city,
			String block, String salary, Integer page);
	
	// 发布兼职
	public int addJob(String pid, String uid, String userName, String userPhone,
			String province, String city, String block,
			String address, String salary,String content,
			String ptime, String pstatus, String precord, Integer day);
	
	// 根据uid获取发布的兼职信息
	public List<PartTimeJobDto> findInfoByUID(String uid);
	
	// 根据pid获取详细的兼职信息
	public PartTimeJobDto getTimeJobByPID(String pid);
	
	public Map<String, String> getPJob(String pid);
	
	public int getTotal();
	
	public String getPstatus(String pid);
	
	public int delPJob(String pid);
	public int updStatus(String pid, String pstatus);
}
