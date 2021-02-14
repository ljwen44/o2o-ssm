package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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
	
	// ���ݵ�ǰ���ڻ�ȡ����������û���������
	@Select("select t.time, ifnull(u.value, 0) as value "
			+ "from (select date_format(date_add(#{date},interval -t.help_topic_id day),'%Y-%m-%d') as time "
			+ "from mysql.help_topic t where t.help_topic_id<=6) as t "
			+ "left join "
			+ "(select date_format(u.regTime,'%Y-%m-%d') as time, count(u.regTime) as value "
			+ "from user u where u.type != '����Ա' group by date_format(u.regTime,'%Y-%m-%d')) as u "
			+ "on t.time=u.time order by t.time desc")
	List<Map<String, String>> getUserNumByDate(@Param("date") String date);
	
	// ��ȡ��ǰ��������������û���������
	@Select("select t.time, ifnull(s.value, 0) as value "
			+ "from (select date_format(date_add(#{date},interval -t.help_topic_id day),'%Y-%m-%d') as time "
			+ "from mysql.help_topic t where t.help_topic_id<=6) as t "
			+ "left join "
			+ "(select date_format(s.stime,'%Y-%m-%d') as time, count(s.stime) as value "
			+ "from sign s group by date_format(s.stime,'%Y-%m-%d')) as s "
			+ "on t.time=s.time order by t.time desc")
	List<Map<String, String>> getUserSignByDate(@Param("date") String date);
	
	// ���ݵ�ǰ���ں��û����ͻ�ȡ�����������������
	@Select("select t.time, ifnull(s.value, 0) as value "
			+ "from (select date_format(date_add(#{date},interval -t.help_topic_id day),'%Y-%m-%d') as time "
			+ "from mysql.help_topic t where t.help_topic_id<=6) as t "
			+ "left join "
			+ "(select date_format(s.stime,'%Y-%m-%d') as time, count(s.stime) as value "
			+ "from sign s, user u where s.uid=u.uid and u.type=#{type} group by date_format(s.stime,'%Y-%m-%d')) as s "
			+ "on t.time=s.time order by t.time desc")
	List<Map<String, String>> getSignByDateAndType(@Param("date") String date, @Param("type") String type);

	// �������ڻ�ȡ�ɽ���
	@Select("select t.time, ifnull(o.value, 0) as value "
			+ "from (select date_format(date_add(#{date},interval -t.help_topic_id day),'%Y-%m-%d') as time "
			+ "from mysql.help_topic t where t.help_topic_id<#{num}) as t "
			+ "left join "
			+ "(select date_format(o.time,'%Y-%m-%d') as time, count(o.time) as value "
			+ "from ordertable o where o.flag='�����' group by date_format(o.time,'%Y-%m-%d')) as o "
			+ "on t.time=o.time order by t.time desc")
	List<Map<String, String>> getOrderByDateAndNum(@Param("date") String date, @Param("num") Integer num);
	
	// �������ڻ�ȡ�ɽ����
	@Select("select t.time, ifnull(c.value, 0) as value "
			+ "from (select date_format(date_add(#{date},interval -t.help_topic_id day),'%Y-%m-%d') as time "
			+ "from mysql.help_topic t where t.help_topic_id<#{num}) as t "
			+ "left join "
			+ "(select date_format(c.time,'%Y-%m-%d') as time, sum(c.coin) as value "
			+ "from coinsave c where c.flag=1 group by date_format(c.time,'%Y-%m-%d')) as c "
			+ "on t.time=c.time order by t.time desc")
	List<Map<String, String>> getCoinByDateAndNum(@Param("date") String date, @Param("num") Integer num);
	
	// �������ڲ鿴ѧ��������֧��
	@Select("select t.time, ifnull(a.value, 0) as value "
			+ "from (select date_format(date_add(#{date},interval -t.help_topic_id day),'%Y-%m-%d') as time "
			+ "from mysql.help_topic t where t.help_topic_id<#{num}) as t "
			+ "left join (select date_format(a.time,'%Y-%m-%d') as time, sum(a.paynum) as value "
			+ "from alipay a where a.type=1 group by date_format(a.time,'%Y-%m-%d')) as a "
			+ "on t.time=a.time order by t.time desc")
	List<Map<String, String>> getCoinIn(@Param("date") String date, @Param("num") Integer num);
	@Select("select t.time, ifnull(a.value, 0) as value "
			+ "from (select date_format(date_add(#{date},interval -t.help_topic_id day),'%Y-%m-%d') as time "
			+ "from mysql.help_topic t where t.help_topic_id<#{num}) as t "
			+ "left join (select date_format(a.time,'%Y-%m-%d') as time, sum(a.paynum) as value "
			+ "from alipay a where a.type=0 group by date_format(a.time,'%Y-%m-%d')) as a "
			+ "on t.time=a.time order by t.time desc")
	List<Map<String, String>> getCoinOut(@Param("date") String date, @Param("num") Integer num);
	
	// -----------------����Ա����ͳ�ƽ���
	
	// ----------����Ա������ְ��Ϣ��ʼ-----------------
	
	// ��ȡ������ְ��Ϣ
	@Select("<script>"
			+ "select p.*, u.uid userUUID, u.userName name, u.avatar "
			+ "from parttimejob p, user u where type=#{type} "
			+ "<if test='pstatus!=null and pstatus != \"\" '>"
			+ "and pstatus=#{pstatus} "
			+ "</if>"
			+ "order by p.ptime desc "
			+ "limit #{page},10"
			+ "</script>")
	List<Map<String, String>> getPJobByAdmin(@Param("type") String type,
			@Param("pstatus") String pstatus, @Param("page") Integer page);
	// ����������ȡ��ְ��Ϣ����
	@Select("<script>"
			+ "select count(*) "
			+ "from parttimejob p, user u where type=#{type} "
			+ "<if test='pstatus!=null and pstatus != \"\" '>"
			+ "and pstatus=#{pstatus} "
			+ "</if>"
			+ "</script>")
	int getTotalByAdmin(@Param("type") String type, @Param("pstatus") String pstatus);
	
	
	// ----------����Ա������ְ��Ϣ����-----------------
}
