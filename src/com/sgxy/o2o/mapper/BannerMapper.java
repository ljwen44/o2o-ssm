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
	@Select("select * from banner order by btime limit #{page}, 8")
	List<BannerDto> getBanners(@Param("page") Integer page);

	// �����ҳ�ֲ�ͼ
	@Insert("insert into banner values(#{bid},#{img},#{btime},#{title},#{desc})")
	int addBanner(@Param("bid") String bid, @Param("img") String img,
			@Param("btime") String btime, @Param("title") String title, @Param("desc") String desc);
	
	// ɾ���ֲ�ͼ
	@Delete("delete banner where bid=#{bid}")
	int delBanner(@Param("bid") String bid);
	
	// �޸��ֲ�ͼ
	@Update("update banner set title=#{title},btime=#{btime},desc=#{desc},img=#{img} where bid=#{bid}")
	int updBanner(@Param("bid") String bid, @Param("img") String img, 
			@Param("btime") String btime,@Param("title") String title,@Param("desc") String desc);
}
