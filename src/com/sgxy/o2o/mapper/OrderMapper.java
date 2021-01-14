package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sgxy.o2o.dto.OrderDto;

public interface OrderMapper {
	// ����pid��ȡ����
	@Select("select * from orderTable where pid=#{pid}")
	public OrderDto getOrderByPID(@Param("pid") String pid);
	
	@Select("select o.*, p.content, p.userName, p.userPhone from orderTable o, parttimejob p where o.uid = #{uid} "
			+ "and o.flag=#{flag} "
			+ "and o.pid = p.pid")
	public List<Map<String, String>> getOrder(@Param("uid") String uid, @Param("flag") String flag);

	// ��Ӷ������ӵ�����
	@Insert("insert into orderTable(uid,pid,oid,flag) values(#{uid},#{pid},#{oid},#{flag})")
	int addOrder(@Param("uid") String uid, @Param("pid") String pid,
			@Param("oid") String oid, @Param("flag") String flag);

	// ȡ������
	// 1��ɾ�������� 2���޸ļ�ְ��Ϣ��״̬
	@Delete("delete from orderTable where oid=#{oid}")
	int delOrder(@Param("oid") String oid);
	
	// ��ɶ���
	@Update("update orderTable set flag=#{flag},time=#{time} where oid=#{oid}")
	int updOFlag(@Param("oid") String oid, @Param("flag") String flag,
			@Param("time") String time);
	
	// ��ȡ������״̬
	@Select("select flag from orderTable where oid=#{oid}")
	String getOFlag(@Param("oid") String oid);
	
}
