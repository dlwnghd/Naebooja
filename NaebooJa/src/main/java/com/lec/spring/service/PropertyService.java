package com.lec.spring.service;

import com.lec.spring.domain.Property;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PropertyRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

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

        // 현재 로그인한 작성자 정보
        User user = U.getLoggedUser();

        System.out.println("☢️☢️☢️☢️☢️☢️☢️☢️");
        System.out.println(propertyRepository.findAll(user.getId()));
        System.out.println("☢️☢️☢️☢️☢️☢️☢️☢️");

        List<Property> list = propertyRepository.findAll(user.getId());
        model.addAttribute("list", list);

        // 한 유저의 전체 자산 정보
        return propertyRepository.findAll(user.getId());

    }
}
