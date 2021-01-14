package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ChatMapper {
	@Insert("insert into chat values(#{cid},#{uid},#{message},#{ctime},#{isRead},#{ruid})")
	int addChat(@Param("cid") String cid, @Param("uid") String uid,
			@Param("message") String message, @Param("ctime") String ctime,
			@Param("isRead") Integer isRead, @Param("ruid") String ruid);
	
	@Update("update chat set isRead=1 where ruid=#{uid} and uid=#{ruid}")
	void updIsRead(@Param("uid") String uid, @Param("ruid") String ruid);

	@Select("select c.message, u.uid userUUID, u.userName, u.avatar "
			+ "from chat c, user u "
			+ "where (c.uid=#{uid} and c.ruid=#{ruid} and u.uid=#{uid}) "
			+ "or (c.uid=#{ruid} and c.ruid=#{uid} and u.uid=#{ruid}) "
			+ "order by c.ctime")
	List<Map<String, String>> getChatList(@Param("uid") String uid, @Param("ruid") String ruid);

	// 需要拿到的数据：userUUID,userName,avatar,message,isRead, time
	@Select("SELECT u.userUUID, u.readNum, u.userName, u.avatar, c.message, c.ctime from "
			+ "(select c.uid userUUID, count(*) readNum, u.userName, u.avatar "
			+ "from chat c, user u "
			+ "where c.ruid=#{uid} and c.isRead=0 and c.uid=u.uid "
			+ "group by c.uid) as u, "
			+ "(select c.uid userUUID, c.message, c.ctime from chat c "
			+ "join (SELECT c1.uid, MAX( c1.ctime ) time FROM chat c1 GROUP BY c1.uid ) b "
			+ "on c.ctime = b.time "
			+ "where c.ruid=#{uid} and c.isRead=0) as c "
			+ "where c.userUUID = u.userUUID")
	List<Map<String, String>> getReadNum(@Param("uid") String uid);
}
