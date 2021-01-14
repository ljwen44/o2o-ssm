package com.sgxy.o2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.ProgressDto;
import com.sgxy.o2o.mapper.ProgressMapper;

@Service("progressService")
public class ProgressServiceImpl implements ProgressService {
	@Autowired
	public ProgressMapper progressMapper;

	@Override
	public List<ProgressDto> getProgressByOID(String oid) {
		// TODO Auto-generated method stub
		return progressMapper.getProgressByOID(oid);
	}

	@Override
	public int addProgress(String oid, String ppid, String content, String pptime) {
		// TODO Auto-generated method stub
		return progressMapper.addProgress(oid, ppid, content, pptime);
	}

	@Override
	public void delProgress(String oid) {
		// TODO Auto-generated method stub
		progressMapper.delProgress(oid);
	}
}
