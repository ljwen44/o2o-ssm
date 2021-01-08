package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sgxy.o2o.dto.FriendDto;

public interface FriendMapper {
	// 获取好友列表,根据添加时间排序,一次返回十条数据
	@Select("select f.*, u.avatar, u.introduce, u.userName from user u, friend f "
			+ "where (f.uid=#{uid} and f.ruid=u.uid) "
			+ "or (f.ruid=#{uid} and f.uid=u.uid)"
			+ "order by f.ftime desc "
			+ "limit #{page}, 10")
	List<Map<String, String>> getFriend(@Param("uid") String uid, @Param("page") Integer page);
	
	@Select("select count(*) from friend where uid=#{uid}")
	int getFriendTotal(@Param("uid") String uid);
	
	@Insert("insert into friend values(#{fid},#{uid},#{ruid},#{ftime})")
	int addFriend(@Param("fid") String fid, @Param("uid") String uid,
			@Param("ruid") String ruid, @Param("ftime") String ftime);
	
	@Delete("delete friend where fid=#{fid}")
	int delFriend(@Param("fid") String fid);
	
	@Select("select * from where fid=#{fid}")
	FriendDto getFriendIsExistByFID(@Param("fid") String fid);
	
}
