package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;


import com.sgxy.o2o.dto.ApplyDto;


public interface ApplyService {
	public int getTotal(String uid);
	public List<Map<String, String>> getApplyList(String uid, Integer page);
	public int updApply(String aid, Integer flag);
	public int delApply(String aid);
	public int addApply(String aid, String uid,String ruid, 
			Integer flag, String desc, String atime);
	public ApplyDto getApplyFlag(String uid,String ruid);
	int delByDelFriend(String uid, String ruid);
}
