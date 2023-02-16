package com.lec.spring.controller;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.User;
import com.lec.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController  // data 를 response 한다  ('View' 를 리턴하는게 아니다!)
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    public CommentController() {
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/test1")
    public QryCommentList test1(Long id){   // url 경로가 아닌 QryCommentList를 return
        // Java 객체 -> JSON object
        QryCommentList list = new QryCommentList();

        list.setCount(1);
        list.setStatus("OK");
        Comment cmt = Comment.builder()
                .user(User.builder().username("한가위").id(34L).regDate(LocalDateTime.now()).name("대보름").build())
                .content("정말 재미있어요")
                .regDate(LocalDateTime.now())
                .build();
        List<Comment> cmtList = new ArrayList<>();
        cmtList.add(cmt);
        list.setList(cmtList);

        return list;

    }

    @GetMapping("/test2")
    public List<Integer> test2(){
        // Java 의 List, 배열 => JSON 의 배열
        List<Integer> list = Arrays.asList(10, 20, 30);
        return list;
    }

    @GetMapping("/test3")
    public Map<Integer, String> test3(){
        // double-brace 구문. {{ }}
        Map<Integer, String> myMap = new HashMap<>(){{
            put(100, "백이다");
            put(200, "이백이다");
        }};

        return myMap;
    }

    @GetMapping("/list")
    public QryCommentList list(Long id){
        return commentService.list(id);
    }

    @PostMapping("/write")
    public QryResult write(
            @RequestParam("write_id") Long writeId,
            @RequestParam("user_id") Long userId,
            String content){
        return commentService.write(writeId, userId, content);
    }

    @PostMapping("/delete")
    public QryResult delete(Long id){
        return commentService.delete(id);
    }
}


























