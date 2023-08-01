package com.example.account.service;

import com.example.account.dto.ArticleDto;
import com.example.account.mappers.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Controller => (서비스: 검색식) -> Mapper 검색
@Service
public class ArticleService {

    @Autowired
    private ConfigMapper configMapper;

    public List<ArticleDto> getList(String code, String searchType, String words) {
        // 검색어
//        System.out.println("서비스 code: " + code);
//        System.out.println("서비스 searchType: " + searchType);
//        System.out.println("서비스 words: " + words);

        String searchQuery = "";

        if(searchType.equals("subject") || searchType.equals("writer") ) {
            //WHERE 컬럼명 = 검색어
            // WHERE subject 또는 writer = '검색어';
            // WHERE 검색타입 = '검색어';
            searchQuery = "WHERE" + searchType + " = '" + words + "'";


        }else if (searchType.equals("content")) {
            // WHERE 컬럼명 Like %검색어%
            //  searchType = "WHERE content LIKE %검색어%";
            searchQuery = "WHERE content LIKE '%" + words + "%'";


        // 검색어가 없으면 실행될 코드(전체 검색)
        }else {
           searchQuery = "";
        }

        Map<String, Object> map = new HashMap<>();
        // "code" <= mapper에 지정한 이름 , code = Service에 선언한 이름
        map.put("code", code);
        map.put("searchQuery", searchQuery);

        // 데이터 타입이 List<ArticleDto>이기 때문에 결과(return)이 줄로 나와야한다. configMapper.getList(code);
        return configMapper.getList(map);
    }

}
