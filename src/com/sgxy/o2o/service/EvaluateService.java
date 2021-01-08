package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;


import com.sgxy.o2o.dto.EvaluateDto;

public interface EvaluateService {
	// ����uid��ȡ�����б�
	public List<Map<String, String>> getEvaListByUID(String uid);
	
	// ����oid��ȡ����
	public EvaluateDto getEvaluateByOID(String oid);
	
	// �������
	public int addEvaluate(String eid, String content,
			String oid, String uid,String etime,
			String erate, String ruid);
}
