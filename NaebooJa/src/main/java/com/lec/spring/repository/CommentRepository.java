package com.lec.spring.repository;

import com.lec.spring.domain.Comment;

import java.util.List;

public interface CommentRepository {

    // 특정 글(write_id) 의 댓글 목록
    List<Comment> findByWrite(Long write_id);

    // 댓글 작성 <-- Comment
    int save(Comment comment);

    // 특정 댓글 (id) 삭제
    int deleteById(Long id);
}
