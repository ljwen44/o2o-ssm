package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.EvaluateDto;
import com.sgxy.o2o.mapper.EvaluateMapper;

@Service("evaluateService")
public class EvaluateServiceImpl implements EvaluateService {
	@Autowired
	public EvaluateMapper evaluateMapper;
	
	@Override
	public List<Map<String, String>> getEvaListByUID(String uid) {
		// TODO Auto-generated method stub
		return evaluateMapper.getEvaListByUID(uid);
	}

	@Override
	public EvaluateDto getEvaluateByOID(String oid) {
		// TODO Auto-generated method stub
		return evaluateMapper.getEvaluateByOID(oid);
	}

	@Override
	public int addEvaluate(String eid, String content, String oid, String uid, String etime, String erate,
			String ruid) {
		// TODO Auto-generated method stub
		return evaluateMapper.addEvaluate(eid, content, oid, uid, etime, erate, ruid);
	}

}
