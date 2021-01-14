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
	public List<Map<String, String>> getAllJob(String province, String city, String block, String salary,
		 Integer page) {
		// TODO Auto-generated method stub
		return partJobMapper.getAllJob(province, city, block, salary, page);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return partJobMapper.getTotal();
	}

	@Override
	public String getPstatus(String pid) {
		// TODO Auto-generated method stub
		return partJobMapper.getPstatus(pid);
	}

	@Override
	public int addJob(String pid, String uid, String userName, String userPhone, String province, String city,
			String block, String address, String salary, String content, String ptime, String pstatus, String precord,
			Integer day) {
		// TODO Auto-generated method stub
		return partJobMapper.addJob(pid, uid, userName, userPhone, province, city, block, address, salary, content, ptime, pstatus, precord, day);
	}

	@Override
	public int delPJob(String pid) {
		// TODO Auto-generated method stub
		return partJobMapper.delPJob(pid);
	}

	@Override
	public int updStatus(String pid, String pstatus) {
		// TODO Auto-generated method stub
		return partJobMapper.updStatus(pid, pstatus);
	}

	

}
