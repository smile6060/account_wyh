package com.example.account.mappers;

import com.example.account.dto.ConfigDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConfigMapper {
    @Insert("INSERT INTO config VALUES(#{code}, #{title}, #{color})")
    void setConfig(ConfigDto config);

    // create라는 것은 없기 때문에 select 사용
    // 테이블 이름이 #{}로 받을거 같지만 ${}로 받음 ${안에는DTO값}
    @Select("CREATE TABLE kortb_${code}(\n" +
            "    id int not null auto_increment,\n" +
            "    subject varchar(100),\n" +
            "    writer varchar(20),\n" +
            "    content text,\n" +
            "    grp int,\n" +
            "    depth int,\n" +
            "    primary key(id)\n" +
            ");")
    void createTable(String code);

    @Select("SELECT * FROM config ORDER BY code ASC")
    List<ConfigDto> getConfig();

    @Delete("DELETE FROM config WHERE code = #{code}")
    void deleteConfig(String code);

    @Select("DROP TABLE kortb_${code}")
    void dropTable(String code);
}
