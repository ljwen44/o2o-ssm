package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface AdminMapper {
	// -----------------����Ա����ͳ�ƿ�ʼ
	// ͳ���û����ͶԱ�
	@Select("select "
			+ "case type "
			+ "when '��Ա' then '��Ա' "
			+ "when 'ѧԱ' then 'ѧԱ' "
			+ "end as name, "
			+ "count(1) as value "
			+ "from user where type!='����Ա' group by type")
	// ͳ��
	List<Map<String, String>> getAnaUserType();
	
	// ��ȡ�û�ע��������
	@Select("select type, count(1) value, regTime "
			+ "from user where type!='����Ա' "
			+ "group by type, regTime "
			+ "order by regTime " 
			+ "limit 7")
	List<Map<String, String>> getUserRegTime();
	
	// �����û���Ծ����ͳ�ƣ���ǩ��Ϊ׼��
	@Select("select s.stime name, count(s.stime) value "
			+ "from sign s "
			+ "GROUP BY stime asc "
			+ "limit 7")
	List<Map<String, String>> getUserSignAll();
	// ��ȡ�û�ĳ��ʱ���ǩ�����
	@Select("select s.stime, u.type, count(2) value "
			+ "from user u, sign s "
			+ "where u.uid = s.uid "
			+ "and s.stime in(select stime from sign GROUP BY stime order by stime desc limit 7) "
			+ "group by u.type, s.stime")
	List<Map<String, String>> getUserSignInDate();
	// -----------------����Ա����ͳ�ƽ���
}
