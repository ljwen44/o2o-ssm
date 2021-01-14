package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.ApplyDto;
import com.sgxy.o2o.mapper.ApplyMapper;

@Service("applyService")
public class ApplyServiceImpl implements ApplyService {
	@Autowired
	public ApplyMapper applyMapper;

	@Override
	public List<Map<String, String>> getApplyList(String uid, Integer page) {
		// TODO Auto-generated method stub
		return applyMapper.getApplyList(uid, page);
	}

	@Override
	public int getTotal(String uid) {
		// TODO Auto-generated method stub
		return applyMapper.getTotal(uid);
	}

	@Override
	public int updApply(String aid, Integer flag) {
		return applyMapper.updApply(aid, flag);
	}

	@Override
	public int delApply(String aid) {
		// TODO Auto-generated method stub
		return applyMapper.delApply(aid);
	}

	@Override
	public int addApply(String aid, String uid, String ruid, Integer flag, String desc, String atime) {
		// TODO Auto-generated method stub
		return applyMapper.addApply(aid, uid, ruid, flag, desc, atime);
	}

	@Override
	public ApplyDto getApplyFlag(String uid, String ruid) {
		// TODO Auto-generated method stub
		return applyMapper.getApplyFlag(uid, ruid);
	}

	@Override
	public int delByDelFriend(String uid, String ruid) {
		// TODO Auto-generated method stub
		return applyMapper.delByDelFriend(uid, ruid);
	}
}
