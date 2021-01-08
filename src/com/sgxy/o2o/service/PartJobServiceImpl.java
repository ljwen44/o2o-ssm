package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.PartTimeJobDto;
import com.sgxy.o2o.mapper.PartJobMapper;

@Service("partjobService")
public class PartJobServiceImpl implements PartJobService {
	@Autowired
	public PartJobMapper partJobMapper;

	@Override
	public int addJob(String pid, String uid, String userName, String userPhone, String province, String city,
			String block, String address, String minSalary, String maxSalary, String startTime, String endTime,
			String content, String ptime, String pstatus, String precord) {
		// TODO Auto-generated method stub
		return partJobMapper.addJob(pid, uid, userName, userPhone, province, city, block, address, minSalary, maxSalary, startTime, endTime, content, ptime, pstatus, precord);
	}

	@Override
	public List<PartTimeJobDto> findInfoByUID(String uid) {
		// TODO Auto-generated method stub
		return partJobMapper.findInfoByUID(uid);
	}

	@Override
	public PartTimeJobDto getTimeJobByPID(String pid) {
		// TODO Auto-generated method stub
		return partJobMapper.getTimeJobByPID(pid);
	}

	@Override
	public Map<String, String> getPJob(String pid) {
		// TODO Auto-generated method stub
		return partJobMapper.getPJob(pid);
	}

	@Override
	public List<Map<String, String>> getAllJob(String province, String city, String block, String minSalary,
			String maxSalary, Integer page) {
		// TODO Auto-generated method stub
		return partJobMapper.getAllJob(province, city, block, minSalary, maxSalary, page);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return partJobMapper.getTotal();
	}

	

}
