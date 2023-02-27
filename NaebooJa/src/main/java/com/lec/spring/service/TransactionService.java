package com.lec.spring.service;

import com.lec.spring.domain.Transaction;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PropertyRepository;
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
public class TransactionService {

    private UserRepository userRepository;
    private PropertyRepository propertyRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        userRepository = sqlSession.getMapper(UserRepository.class);
        propertyRepository = sqlSession.getMapper(PropertyRepository.class);
        transactionRepository = sqlSession.getMapper(TransactionRepository.class);
        System.out.println("TransactionService() 생성");
    }

//    1. CRUD - Create
    public int insert(Transaction transaction){
        User user = U.getLoggedUser();
//        DB 에서 다시 읽어옴
        user = userRepository.findById(user.getId());
        transaction.setUser_id(user);

        int cnt = transactionRepository.save(transaction);
        return cnt;
    }

//    2. CRUD - Read
//    (2-1) 특정 user 의 거래내역 전부 불러오기 (언제 필요한지는 모르겠음)
    public List<Transaction> list(Model model){
        User user = U.getLoggedUser();
        Long id = user.getId();

        List<Transaction> list = transactionRepository.findAlls(id);
        model.addAttribute("list", list);
        return list;
    }

//    (2-2) 특정 user 의 특정 타입의 거래 내역 불러오기
    public List<Transaction> listByType(String type){
        User user = U.getLoggedUser();
        Long id = user.getId();
        return transactionRepository.findByType(id, type);
    }

//    (2-3) 특정 user 의 특정 날짜(일) 의 거래내역 전체 불러오기
    public List<Transaction> listByDay(Date date){
        User user = U.getLoggedUser();
        Long id = user.getId();
        return transactionRepository.findAllByDaily(id, date);
    }

//    (2-4) 특정 user 의 특정 달(월) 의 거래내역 전체 불러오기
    public List<Transaction> listByMonth(Date date){
        User user = U.getLoggedUser();
        Long id = user.getId();
        return transactionRepository.findAllByMonthly(id, date);
    }

//    (2-5) 특정 user 의 특정 날짜(일) 의 특정 타입의 거래 내역 불러오기
    public List<Transaction> listByTypeinDay(String type, Date date){
        User user = U.getLoggedUser();
        Long id = user.getId();
        return transactionRepository.findByDayType(id, type, date);
    }

//    (2-6) 특정 user 의 특정 달(월)의 특정 타입의 거래 내역 불러오기
    public List<Transaction> listByTypeinMonth(String type, Date date){
    User user = U.getLoggedUser();
    Long id = user.getId();
    return transactionRepository.findByMonthType(id, type, date);
}

//    3. CRUD - Update
    public int update(Transaction transaction){
        int result = 0;
        result = transactionRepository.update(transaction);
        return result;
    }

//    4. CRUD - Delete
    public int delete(Transaction transaction){
        User user = U.getLoggedUser();
    //        DB 에서 다시 읽어옴
        user = userRepository.findById(user.getId());
        transaction.setUser_id(user);

        int cnt = transactionRepository.delete(transaction);
        return cnt;
    }
}
