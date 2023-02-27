package com.lec.spring.service;

import com.lec.spring.domain.*;
import com.lec.spring.repository.PropertyRepository;
import com.lec.spring.repository.StatisticRepository;
import com.lec.spring.repository.TransactionRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Service
public class StatisticService {

    private UserRepository userRepository;
    private PropertyRepository propertyRepository;
    private TransactionRepository transactionRepository;
    private StatisticRepository statisticRepository;

    @Autowired
    public StatisticService(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        userRepository = sqlSession.getMapper(UserRepository.class);
        propertyRepository = sqlSession.getMapper(PropertyRepository.class);
        transactionRepository = sqlSession.getMapper(TransactionRepository.class);
        statisticRepository = sqlSession.getMapper(StatisticRepository.class);
        System.out.println("StatisticService() 생성");
    }

    // 한 유저의 전체 지출 정보
    public List<Transaction> list(Model model){

        // 현재 로그인 사용자 정보
        User user = U.getLoggedUser();

        // 특정 유저의 지출 리스트
        List<Transaction> list = statisticRepository.findUserOutcomeAll(user.getId());
        model.addAttribute("list", list);

        System.out.println("🤩🤩🤩🤩🤩🤩🤩");
        System.out.println(list);

        // 한 유저의 전체 지출 정보
        return list;
    }

    // 특정 유저의 특정 월 지출 정보
    public QryStatisticList statisticDetail(Long id, Date date){
        QryStatisticList statisticList = new QryStatisticList();

        List<Transaction> trans = statisticRepository.findUserOutcomeAll_Monthly(id, date);

        statisticList.setCount(trans.size());
        statisticList.setList(trans);
        statisticList.setStatus("OK");
        statisticList.setDate(date);

        System.out.println("🥶"+statisticList.getCount());
        System.out.println("🥶"+statisticList.getList());
        System.out.println("🥶"+statisticList.getStatus());

        // 특정 자산 정보
        return statisticList;
    }
}