package com.lec.spring.repository;

// Repository layer
// DataSource (DB) 등에 대한 직접적인 접근

import com.lec.spring.domain.Write;

import java.util.List;


public interface WriteRepository {

    // 새글 작성 <- Write
    int save(Write write);

    // admin 공지글 작성
    int saveAdmin(Write write);

    // 공지사항 글 조회
    List<Write> findAdminWrite();

    // 특정 id 글 내용 읽기
    Write findById(long id);

    // 특정 id 글 조회수 +1 증가
    int incViewCnt(long id);

    // 전체 글 목록 : 최신순
    List<Write> findAll();

    // 특정 id 글 수정 (제목, 내용)
    int update(Write write);

    // 특정 id 글 삭제하기
    int delete(Write write);

    // 페이징
    // from 부터 rows 개 만큼 select
    List<Write> selectFromRow(int from, int rows);

    // 전체 글의 개수
    int countAll();
}













