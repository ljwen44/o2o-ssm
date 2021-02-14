package com.sgxy.o2o.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CoinSaveMapper {
	@Insert("insert into coinsave(csid,uid,coin,flag,pid) "
			+ "values(#{csid},#{uid},#{coin},#{flag},#{pid})")
	int addCoinSave(@Param("csid") String csid, @Param("uid") String uid,
			@Param("coin") Integer coin, @Param("flag") Integer flag,
			@Param("pid") String pid);

	@Update("update coinsave set flag=1 where csid=#{csid}")
	int updCoinSaveFlag(@Param("csid") String csid);

	@Select("select coin from coinsave where pid=#{pid}")
	int getCoinSave(@Param("pid") String pid);
	
	@Update("update coinsave set time=#{time},flag=1 where pid=#{pid}")
	int updCoinTime(@Param("pid") String pid, @Param("time") String time);
	
	@Delete("delete from coinsave where uid=#{uid} and pid=#{pid}")
	int delCoinByUIDandPID(@Param("uid") String uid, @Param("pid") String pid);
}
