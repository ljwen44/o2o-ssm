package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;


import com.sgxy.o2o.dto.EvaluateDto;

public interface EvaluateService {
	// 根据uid获取评价列表
	public List<Map<String, String>> getEvaListByUID(String uid);
	
	// 根据oid获取评价
	public EvaluateDto getEvaluateByOID(String oid);
	
	// 添加评价
	public int addEvaluate(String eid, String content,
			String oid, String uid,String etime,
			String erate, String ruid);
}
