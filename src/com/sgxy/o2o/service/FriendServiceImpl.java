package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.FriendDto;
import com.sgxy.o2o.mapper.FriendMapper;

@Service("friendService")
public class FriendServiceImpl implements FriendService {
	@Autowired
	public FriendMapper friendMapper;

	@Override
	public List<Map<String, String>> getFriend(String uid, Integer page) {
		// TODO Auto-generated method stub
		return friendMapper.getFriend(uid, page);
	}

	@Override
	public int getFriendTotal(String uid) {
		// TODO Auto-generated method stub
		return friendMapper.getFriendTotal(uid);
	}

	@Override
	public int addFriend(String fid, String uid, String ruid, String ftime) {
		// TODO Auto-generated method stub
		return friendMapper.addFriend(fid, uid, ruid, ftime);
	}

	@Override
	public int delFriend(String fid) {
		// TODO Auto-generated method stub
		return friendMapper.delFriend(fid);
	}

	@Override
	public FriendDto getFriendIsExistByFID(String fid) {
		// TODO Auto-generated method stub
		return friendMapper.getFriendIsExistByFID(fid);
	}
}
