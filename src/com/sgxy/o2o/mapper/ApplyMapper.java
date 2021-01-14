package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sgxy.o2o.dto.ApplyDto;

public interface ApplyMapper {
	// ��ȡ����
	@Select("select count(*) from apply a where a.ruid=#{uid}")
	int getTotal(@Param("uid") String uid);
	
	// ����uid��ȡ���������б�
	@Select("select a.*, u.userName, u.avatar from apply a, user u where a.ruid=#{uid} "
			+ "and a.uid=u.uid "
			+ "and flag='������' "
			+ "order by atime "
			+ "limit #{page}, 10")
	List<Map<String, String>> getApplyList(@Param("uid") String uid, @Param("page") Integer page);

	// ͨ������
	@Update("update apply set flag=#{flag} where aid=#{aid}")
	int updApply(@Param("aid") String aid, @Param("flag") Integer flag);
	
	// �ܾ�����
	@Delete("delete from apply where aid=#{aid}")
	int delApply(@Param("aid") String aid);
	
	// ��Ӻ��Ѳ���
	@Insert("insert into apply values(#{aid}, #{uid}, #{ruid}, #{flag}, #{desc}, #{atime})")
	int addApply(@Param("aid") String aid, @Param("uid") String uid,
			@Param("ruid") String ruid, @Param("flag") Integer flag, 
			@Param("desc") String desc, @Param("atime") String atime);
	
	// ��ȡ��Ӻ��ѵ�״̬
	@Select("select * from apply where uid=#{uid} and ruid=#{ruid}")
	ApplyDto getApplyFlag(@Param("uid") String uid, @Param("ruid") String ruid);

	// ɾ������֮��ɾ����������
	@Delete("delete from apply "
			+ "where (uid=#{uid} and ruid=#{ruid}) "
			+ "or (uid=#{ruid} and ruid=#{uid})")
	int delByDelFriend(@Param("uid") String uid, @Param("ruid") String ruid);

}
