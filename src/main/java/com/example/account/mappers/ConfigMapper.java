package com.example.account.mappers;

import com.example.account.dto.ArticleDto;
import com.example.account.dto.ConfigDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    @Select("SELECT * FROM config WHERE code = #{code}")
    ConfigDto getConfigOne(String code);

    // 다중 게시판은 매개변수를 필요로 한다.(String code)
//    @Select("SELECT * FROM kortb_${code} ORDER BY grp DESC, depth ASC") <= 검색기능 추가 전 코드
    @Select("SELECT * FROM kortb_${code} #{searchQuery} ORDER BY grp DESC, depth ASC")
    List<ArticleDto> getList(Map<String, Object> map);

    @Insert("INSERT INTO kortb_${code} VALUES(NULL, #{subject}, #{writer}, #{content}, #{grp}, 1)")
    void setWrite(ArticleDto articleDto);

//    ifnull(값이 있으면 grp + 1, 없으면 default 값 1)
    @Select("SELECT ifnull(max(grp) + 1, 1) as maxCnt FROM kortb_${code}")
    int getMaxCnt(String code);

    @Delete("DELETE FROM kortb_${code} WHERE id = #{id}")
    void delArticle(ArticleDto articleDto);

    @Select("SELECT * FROM kortb_${code} WHERE id = #{id}")
    ArticleDto getEdit(ArticleDto articleDto);

    @Update("UPDATE kortb_${code} SET subject = #{subject}, writer = #{writer}, content = #{content} WHERE id = #{id}")
    void setEdit(ArticleDto articleDto);

    @Insert("INSERT INTO kortb_${code} VALUES(NULL, #{subject}, #{writer}, #{content}, #{grp}, #{depth})")
    void setReply(ArticleDto articleDto);
}
