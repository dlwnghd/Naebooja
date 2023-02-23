package com.lec.spring.service;

import com.lec.spring.domain.*;
import com.lec.spring.repository.PropertyRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PropertyService {

    private UserRepository userRepository;
    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        userRepository = sqlSession.getMapper(UserRepository.class);
        propertyRepository = sqlSession.getMapper(PropertyRepository.class);
        System.out.println("PropertyService() 생성");
    }

    public List<Property> list(Model model){

        // 현재 로그인 사용자 정보
        User user = U.getLoggedUser();

        System.out.println("☢️☢️☢️☢️☢️☢️☢️☢️");
        System.out.println(propertyRepository.findAll(user.getId()));

        // 특정 유저의 자산 리스트
        List<Property> list = propertyRepository.findAll(user.getId());
        model.addAttribute("list", list);

        // 한 유저의 전체 자산 정보
        return list;
    }

    public List<Transaction> propList(Model model){

        // 현재 로그인 사용자 정보
        User user = U.getLoggedUser();

        // 특정 자산의 번호⚠️
        // 특정 자산의 번호를 받아와야 한다 슈불탱

        // 특정 유저의 특정 자산의 거래 리스트
        List<Transaction> propList = propertyRepository.findPropTransAll(user.getId(), 1L);
        model.addAttribute("propList", propList);
        System.out.println("🟡🟡🟡🟡🟡🟡🟡🟡🟡");
        System.out.println(propertyRepository.findPropTransAll(user.getId(), 1L));

        // 한 유저의 전체 자산 정보
        return propList;
    }

    public int write(Property property){

        // 현재 로그인한 작성자 정보
        User user = U.getLoggedUser();

        // 위 정보는 session 의 정보이고, 일단 DB 에서 다시 읽어온다
        user = userRepository.findById(user.getId());
        property.setUser(user);  // 글 작성자 세팅

        int cnt = propertyRepository.save(property);

        return cnt;
    }

    public int deleteById(long id){
        int result = 0;

        Property property = propertyRepository.findById(id);
        if(property != null) {
            // 글삭제 (참조하는 첨부파일, 댓글 등도 같이 삭제 될 것이다 ON DELETE CASCADE)
            result = propertyRepository.delete(property);
        }

        return result;
    }
}
