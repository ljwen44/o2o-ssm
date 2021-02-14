package com.sgxy.o2o.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sgxy.o2o.dto.BannerDto;

public interface BannerMapper {
	@Select("select count(*) from banner")
	int getAll();
	
	// ��ȡ�ֲ�ͼ
	@Select("select * from banner order by btime desc limit #{page}, 8")
	List<BannerDto> getBanners(@Param("page") Integer page);

	// �����ҳ�ֲ�ͼ
	@Insert("insert into banner values(#{bid},#{img},#{btime},#{title},#{content})")
	int addBanner(@Param("bid") String bid, @Param("img") String img,
			@Param("btime") String btime, @Param("title") String title, @Param("content") String content);
	
	// ɾ���ֲ�ͼ
	@Delete("delete from banner where bid=#{bid}")
	int delBanner(@Param("bid") String bid);
	
	// �޸��ֲ�ͼ
	@Update("update banner set title=#{title},btime=#{btime},content=#{desc},img=#{img} where bid=#{bid}")
	int updBanner(@Param("bid") String bid, @Param("img") String img, 
			@Param("btime") String btime,@Param("title") String title,@Param("desc") String desc);

	// ��ȡ��ҳ�ֲ�ͼ
	@Select("select img from banner order by btime desc limit 4")
	List<String> getIndexBanner();
}
