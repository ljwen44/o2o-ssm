package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sgxy.o2o.dto.PartTimeJobDto;

public interface PartJobMapper {
	// 多条件查询
		@Select("<script>"
		+ "select p.*, u.uid userUUID, u.userName name, u.avatar from parttimejob p, user u where p.uid=u.uid "
		+ "and p.pstatus='未接单' "
		+ "<if test='province!=null and province != \"\" '>"
		+ "and p.province=#{province} "
		+ "</if>"
		+ "<if test='city!=null and city != \"\" '>"
		+ "and p.city=#{city} "
		+ "</if>"
		+ "<if test='block!=null and block != \"\" '>"
		+ "and p.block=#{block} "
		+ "</if>"
		+ "<if test='salary!=null and salary != \"\" '>"
		+ "and p.salary &gt;= #{salary} "
		+ "</if>"
		+" ORDER BY p.ptime DESC"
		+" LIMIT #{page}, 2"
		+ "</script>")
	List<Map<String, String>> getAllJob(@Param("province") String province,
			@Param("city") String city, @Param("block") String block, 
			@Param("salary") String salary, @Param("page") Integer page);
	
	// 添加兼职信息
	@Insert("insert into parttimejob values(#{pid}, #{uid}, #{userName},"
			+ "#{userPhone}, #{province}, #{city},"
			+ "#{block}, #{address}, #{salary},"
			+ "#{content}, #{ptime}, #{pstatus},#{precord}, #{day})")
	int addJob(@Param("pid") String pid, @Param("uid") String uid, @Param("userName") String userName, 
			@Param("userPhone") String userPhone,
			@Param("province") String province, @Param("city") String city, @Param("block") String block,
			@Param("address") String address, @Param("salary") String salary,
			@Param("content") String content, @Param("ptime") String ptime, 
			@Param("pstatus") String pstatus, @Param("precord") String precord,
			@Param("day") Integer day);

	// 获取用户发布的兼职信息
	@Select("select * from parttimejob where uid=#{uid} order by ptime desc limit 6")
	List<PartTimeJobDto> findInfoByUID(@Param("uid") String uid);

	// 根据pid获取详细的兼职信息
	@Select("select * from parttimejob where pid=#{pid}")
	PartTimeJobDto getTimeJobByPID(@Param("pid") String pid);
	
	@Select("select p.*, u.userName name, u.avatar, u.uid uid "
			+ "from parttimejob p, user u "
			+ "where p.pid=#{pid} "
			+ "and p.uid=u.uid")
	Map<String, String> getPJob(@Param("pid") String pid);
	
	@Select("select count(*) from parttimejob where pstatus='未接单'")
	int getTotal();

	// 获取兼职信息状态
	@Select("select pstatus from parttimejob where pid=#{pid}")
	String getPstatus(@Param("pid") String pid);

	// 删除订单
	@Delete("delete from parttimejob where pid=#{pid}")
	int delPJob(@Param("pid") String pid);
	
	// 修改订单状态
	@Update("update parttimejob set pstatus=#{pstatus} where pid=#{pid}")
	public int updStatus(@Param("pid") String pid, @Param("pstatus") String pstatus);
	
	
}
