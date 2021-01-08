package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	@Update("update apply set flag='已完成' where aid=#{aid}")
	int updApply(@Param("aid") String aid);
	
	// 拒绝申请
	@Delete("delete friend where aid=#{aid}")
	int delApply(@Param("aid") String aid);
}
