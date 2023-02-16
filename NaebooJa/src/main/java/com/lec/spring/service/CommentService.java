package com.lec.spring.service;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.User;
import com.lec.spring.repository.CommentRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentService(SqlSession sqlSession){
        commentRepository = sqlSession.getMapper(CommentRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);

        System.out.println("CommentService() 생성");
    }

    // 특정 글(id) 의 댓글 목록
    public QryCommentList list(Long id) {
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findByWrite(id);

        list.setCount(comments.size());
        list.setList(comments);
        list.setStatus("OK");

        return list;
    }

    // 특정 글(writeId) 에 특정 사용자(userId) 가 댓글 작성
    public QryResult write(Long writeId, Long userId, String content) {
        User user = userRepository.findById(userId);

        Comment comment = Comment.builder()
                .user(user)
                .content(content)
                .write_id(writeId)
                .build();

        commentRepository.save(comment);

        QryResult result = QryResult.builder()
                .count(1)
                .status("OK")
                .build();

        return result;
    }

    public QryResult delete(Long id) {

        int count = commentRepository.deleteById(id);
        String status = "FAIL";

        if(count > 0) status = "OK";

        QryResult result = QryResult.builder()
                .count(count)
                .status(status)
                .build();

        return result;
    }
}
