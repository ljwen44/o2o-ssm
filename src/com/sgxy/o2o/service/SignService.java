package com.sgxy.o2o.service;

import java.util.List;

public interface SignService {
	// ǩ��
	public int addSign(String sid, String uid, String stime);
	 
	// ��ȡǩ������
	public List<String> getSign(String uid);
}
