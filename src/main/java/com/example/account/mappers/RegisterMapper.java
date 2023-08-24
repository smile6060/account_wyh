package com.example.account.mappers;

import com.example.account.dto.RegisterDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Insert("INSERT INTO member VALUES(NULL, #{userid}, #{username}, #{password}, #{level})")
    void getRegister(RegisterDto registerDto);

    @Select("SELECT * FROM member WHERE userid = #{userid} AND password = #{password}")
    RegisterDto checkLogin(RegisterDto registerDto);

    @Select("SELECT * FROM member ORDER BY id DESC")
    List<RegisterDto> getMemberAll();

    @Select(" SELECT * FROM member WHERE id = #{id}")
    RegisterDto getMemberId(int id);

    @Update("UPDATE member SET userid = #{userid}, username = #{username}, password = #{password}, level = #{level} WHERE id = #{id}")
    void setUpdate(RegisterDto registerDto);


}
