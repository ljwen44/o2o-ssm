package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

public interface AdminService {
	public List<Map<String, String>> getAnaUserType();
	public List<Map<String, String>> getUserRegTime();
	public List<Map<String, String>> getUserSignAll();
	public List<Map<String, String>> getUserSignInDate();
}
