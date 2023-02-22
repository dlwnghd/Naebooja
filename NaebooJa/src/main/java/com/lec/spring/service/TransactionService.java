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

//    CRUD - Create
    public int insert(Transaction transaction){
        User user = U.getLoggedUser();
//        DB 에서 다시 읽어옴
        user = userRepository.findById(user.getId());
        transaction.setUser_id(user);

        int cnt = transactionRepository.save(transaction);

        return cnt;
    }

//    CRUD - Read


//    CRUD - Update
    public int update(Transaction transaction){
        int result = 0;

        result = transactionRepository.update(transaction);

        return result;
    }

//    CRUD - Delete
    public int deleteById(long id){
        int result = 0;

        Transaction transaction = transactionRepository.findById(id);
        result = transactionRepository.delete(transaction);
        return result;
    }
}
