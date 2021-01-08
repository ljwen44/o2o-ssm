package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface AdminMapper {
	// -----------------管理员数据统计开始
	// 统计用户类型对比
	@Select("select "
			+ "case type "
			+ "when '教员' then '教员' "
			+ "when '学员' then '学员' "
			+ "end as name, "
			+ "count(1) as value "
			+ "from user where type!='管理员' group by type")
	// 统计
	List<Map<String, String>> getAnaUserType();
	
	// 获取用户注册增加率
	@Select("select type, count(1) value, regTime "
			+ "from user where type!='管理员' "
			+ "group by type, regTime "
			+ "order by regTime " 
			+ "limit 7")
	List<Map<String, String>> getUserRegTime();
	
	// 近期用户活跃人数统计（以签到为准）
	@Select("select s.stime name, count(s.stime) value "
			+ "from sign s "
			+ "GROUP BY stime asc "
			+ "limit 7")
	List<Map<String, String>> getUserSignAll();
	// 获取用户某段时间的签到情况
	@Select("select s.stime, u.type, count(2) value "
			+ "from user u, sign s "
			+ "where u.uid = s.uid "
			+ "and s.stime in(select stime from sign GROUP BY stime order by stime desc limit 7) "
			+ "group by u.type, s.stime")
	List<Map<String, String>> getUserSignInDate();
	// -----------------管理员数据统计结束
}
