package com.sgxy.o2o.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sgxy.o2o.dto.UserDto;


public interface UserMapper {
	// ����uid�����û�
	@Select("SELECT * FROM user WHERE uid=#{uid}")
	UserDto findUserByUUID(@Param("uid") String uid);	
	// �����������������û�
	@Select("SELECT * FROM user WHERE email=#{email} and password=#{password}")
	UserDto selectUserByEmailAndPassword(@Param("email") String email,@Param("password") String password);
	// ���������ѯ����
	@Select("select * from user where email=#{email}")
	UserDto getUserByEmail(@Param("email") String email);
	// �����ֻ���ѯ����
	@Select("select * from user where phone=#{phone}")
	String getUserByPhone(@Param("phone") String phone);
	// ����û�
	@Insert("insert into user(uid, email, phone, password, type, userName, regTime, auth) "
			+ "values(#{uid},#{email},#{phone},#{password},#{type},#{userName},#{regTime},#{coin},#{auth})")
	int addUser(@Param("uid") String uid, @Param("email") String email, @Param("password") String password,
			@Param("type") String type, @Param("phone") String phone, @Param("userName") String userName,
			@Param("regTime") String regTime, @Param("auth") Integer coin, @Param("auth") String auth);
	// �޸�����
	@Update("update user set password=#{password} where uid=#{uid}")
	int updPwd(@Param("uid") String uid,@Param("password") String password);	
	// �޸�����
	@Update("update user "
			+ "set avatar=#{avatar},userName=#{userName},sex=#{sex},"
			+ "record=#{record},school=#{school},graduation=#{graduation},"
			+ "city=#{city},IDpass=#{IDpass},phone=#{phone},"
			+ "introduce=#{introduce} "
			+ "where uid=#{uid}")
	int editUser(@Param("avatar") String avatar,@Param("userName") String userName,@Param("sex") String sex,
			@Param("record") String record,@Param("school") String school,@Param("graduation") String graduation,
			@Param("city") String city,@Param("IDpass") String IDpass,@Param("phone") String phone,
			@Param("introduce") String introduce,@Param("uid") String uid);
	// ��ȡ�Ƽ��û�
	@Select("select * from user where type='��Ա' "
			+ "order by rate "
			+ "limit 5")
	List<UserDto> getRecUser();
	// ǩ���ӻ���
	@Update("update user set integral=integral+5,calcIntegral=calcIntegral+5 where uid=#{uid}")
	int addJFen(@Param("uid") String uid);
	// ���ֶһ�ѧ��
	@Update("update user set coin=coin+num,integral=integral-(num*100) where uid=#{uid}")
	int inteToCoin(@Param("uid") String uid, @Param("num") Integer num);
	// ����Ա�޸�����
	@Update("update user set userName=#{userName},phone=#{phone},avatar=#{avatar} where uid=#{uid}")
	int updAdmin(@Param("uid") String uid, @Param("userName") String userName, 
			@Param("phone") String phone, @Param("avatar") String avatar);
	// ��ȡ��Ҫ��֤���û�
	@Select("<script>"
			+ "select * from user where auth='δ��֤' and type='��Ա' and authStatus='������' "
			+ "<if test='userName!=null and userName != \"\" '>"
			+ "and userName like #{userName} "
			+ "</if>"
			+ "order by regTime "
			+ "limit #{page}, 8"
			+ "</script>")
	List<UserDto> getAuthUser(@Param("page") Integer page, @Param("userName") String userName);
	// ��ȡ��֤�û�������
	@Select("select count(*) from user where auth='δ��֤' and type='��Ա'")
	int getTotal();
	// ��֤δͨ��
	@Update("update user set authStatus='δ����' where uid=#{uid}")
	int updStatus(@Param("uid") String uid);
	// ��֤ͨ��
	@Update("update user set auth='����֤' where uid=#{uid}")
	int updAuth(@Param("uid") String uid);
	// �����û����ͺͷ�ҳ���Լ��û�����ȡ�û�
	@Select("<script>"
			+ "select * from user where type != '����Ա'"
			+ "<if test='type!=null and type != \"\" '>"
			+ "and type=#{type} "
			+ "</if>"
			+ "<if test='userName!=null and userName != \"\" '>"
			+ "and userName like #{userName} "
			+ "</if>"
			+ "order by regTime "
			+ "limit #{page}, 8"
			+ "</script>")
	List<UserDto> getUserByConditions(@Param("page") Integer page, 
			@Param("userName") String userName, @Param("type") String type);
    // ��ȡ�û�������������type
	@Select("<script>"
			+ "select count(*) from user where type!='����Ա' "
			+ "<if test='type!=null and type != \"\" '>"
			+ "and type=#{type} "
			+ "</if>"
			+ "</script>")
	int getTotalByType(@Param("type") String type);

	@Insert("insert into exitdata values(#{exid},#{uid},#{chat},#{sign})")
	int addLogout(@Param("exid") String exid,@Param("uid") String uid,
			@Param("chat") String chat, @Param("sign") String sign);
	
	@Select("select exid from exitdata where uid=#{uid}")
	String getLogoutData(@Param("uid") String uid);
	
	@Update("update exitdata set chat=#{chat},sign=#{sign} where uid=#{uid}")
	int updData(@Param("chat") String chat, @Param("uid") String uid, @Param("sign") String sign);

	@Select("select * from exitdata where uid=#{uid}")
	Map<String, String> getLogout(@Param("uid") String uid);
	
	@Update("update user set coin=coin-#{coin} where uid=#{uid}")
	int delUserCoin(@Param("uid") String uid, @Param("coin") Integer coin);
	
	@Update("update user set coin=coin+#{coin} where uid=#{uid}")
	int addUserCoin(@Param("uid") String uid, @Param("coin") Integer coin);
	
	// �û�������֤
	@Update("update user set authStatus='������' where uid=#{uid}")
	int updAuthStatus(@Param("uid") String uid);
	@Select("select authStatus from user where uid=#{uid}")
	String getAuthStatus(@Param("uid") String uid);
}
