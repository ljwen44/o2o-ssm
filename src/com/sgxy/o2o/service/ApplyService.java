package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

public interface ApplyService {
	public int getTotal(String uid);
	public List<Map<String, String>> getApplyList(String uid, Integer page);
	public int updApply(String aid);
	public int delApply(String aid);
}
