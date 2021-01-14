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
	// 获取总数
	@Select("select count(*) from apply a where a.ruid=#{uid}")
	int getTotal(@Param("uid") String uid);
	
	// 根据uid获取好友申请列表
	@Select("select a.*, u.userName, u.avatar from apply a, user u where a.ruid=#{uid} "
			+ "and a.uid=u.uid "
			+ "and flag='进行中' "
			+ "order by atime "
			+ "limit #{page}, 10")
	List<Map<String, String>> getApplyList(@Param("uid") String uid, @Param("page") Integer page);

	// 通过申请
	@Update("update apply set flag=#{flag} where aid=#{aid}")
	int updApply(@Param("aid") String aid, @Param("flag") Integer flag);
	
	// 拒绝申请
	@Delete("delete from apply where aid=#{aid}")
	int delApply(@Param("aid") String aid);
	
	// 添加好友操作
	@Insert("insert into apply values(#{aid}, #{uid}, #{ruid}, #{flag}, #{desc}, #{atime})")
	int addApply(@Param("aid") String aid, @Param("uid") String uid,
			@Param("ruid") String ruid, @Param("flag") Integer flag, 
			@Param("desc") String desc, @Param("atime") String atime);
	
	// 获取添加好友的状态
	@Select("select * from apply where uid=#{uid} and ruid=#{ruid}")
	ApplyDto getApplyFlag(@Param("uid") String uid, @Param("ruid") String ruid);

	// 删除好友之后删除申请数据
	@Delete("delete from apply "
			+ "where (uid=#{uid} and ruid=#{ruid}) "
			+ "or (uid=#{ruid} and ruid=#{uid})")
	int delByDelFriend(@Param("uid") String uid, @Param("ruid") String ruid);

}
