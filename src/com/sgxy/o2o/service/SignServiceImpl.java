package com.sgxy.o2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.mapper.SignMapper;

@Service("signService")
public class SignServiceImpl implements SignService{
	@Autowired
	public SignMapper signMapper;

	@Override
	public int addSign(String sid, String uid, String stime) {
		// TODO Auto-generated method stub
		return signMapper.addSign(sid, uid, stime);
	}

	@Override
	public List<String> getSign(String uid) {
		// TODO Auto-generated method stub
		return signMapper.getSign(uid);
	}
}
