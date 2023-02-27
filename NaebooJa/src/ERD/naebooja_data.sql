-- 기존테이블 삭제
DELETE FROM user_authorities;
ALTER TABLE user_authorities AUTO_INCREMENT = 1;
DELETE FROM authority;
ALTER TABLE authority AUTO_INCREMENT = 1;
DELETE FROM comment;
ALTER TABLE comment AUTO_INCREMENT = 1;
DELETE FROM file;
ALTER TABLE file AUTO_INCREMENT = 1;
DELETE FROM `write` ;
ALTER TABLE `write` AUTO_INCREMENT = 1;
DELETE FROM `user` ;
ALTER TABLE `user` AUTO_INCREMENT = 1;
DELETE FROM property ;
ALTER TABLE property AUTO_INCREMENT = 1;
DELETE FROM transaction;
ALTER TABLE transaction AUTO_INCREMENT = 1;

-- 샘플 authority
INSERT INTO authority (name) VALUES
	('ROLE_MEMBER'), ('ROLE_ADMIN')
;

-- 샘플 사용자
INSERT INTO `user` (username, password, name) VALUES
-- ('USER1', '1234', '회원1'),
-- ('USER2', '1234', '회원2'),
-- ('ADMIN1', '1234', '관리자1')
('USER1', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', '회원1'),
('USER2', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa', '회원2'),
('ADMIN1', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '관리자1')
;

-- 샘플 사용자-권한
INSERT INTO user_authorities VALUES
(1, 1),
(3, 1),
(3, 2)
;
-- 샘플 글
INSERT INTO `write` (user_id, subject, content) VALUES
(1, '제목입니다1', '내용입니다1'),
(1, '제목입니다2', '내용입니다2'),
(3, '제목입니다3', '내용입니다3'),
(3, '제목입니다4', '내용입니다4')
;
-- 샘플 댓글
INSERT INTO comment(user_id, write_id, content) VALUES
(1, 1, '1. user1이 1번글에 댓글 작성.'),
(1, 1, '2. user1이 1번글에 댓글 작성.'),
(1, 2, '3. user1이 2번글에 댓글 작성.'),
(1, 2, '4. user1이 2번글에 댓글 작성.'),
(1, 3, '5. user1이 3번글에 댓글 작성.'),
(1, 3, '6. user1이 3번글에 댓글 작성.'),
(1, 4, '7. user1이 4번글에 댓글 작성.'),
(1, 4, '8. user1이 4번글에 댓글 작성.'),
(3, 1, '9. admin1이 1번글에 댓글 작성.'),
(3, 1, '10. admin1이 1번글에 댓글 작성.'),
(3, 2, '11. admin1이 2번글에 댓글 작성.'),
(3, 2, '12. admin1이 2번글에 댓글 작성.'),
(3, 3, '13. admin1이 3번글에 댓글 작성.'),
(3, 3, '14. admin1이 3번글에 댓글 작성.'),
(3, 4, '15. admin1이 4번글에 댓글 작성.'),
(3, 4, '16. admin1이 4번글에 댓글 작성.')
;
-- 샘플 댓글
INSERT INTO file(write_id, source, file) VALUES
(1, 'face01.png', 'face01.png'),
(1, 'face02.png', 'face02.png'),
(2, 'face03.png', 'face03.png'),
(2, 'face04.png', 'face04.png'),
(3, 'face05.png', 'face05.png'),
(3, 'face06.png', 'face06.png'),
(4, 'face07.png', 'face07.png'),
(4, 'face08.png', 'face08.png')
;

-- 샘플 property
INSERT INTO property(user_id, `group`, `name`, rest_money) VALUES
-- 1번 유저
(1, '현금', '테스트자산1', 100000),
(1, '현금', '테스트자산2', 10000),
(1, '현금', '테스트자산3', 1000),
-- 2번 유저
(2, '현금', '테스트자산1', 100000),
(2, '현금', '테스트자산2', 10000),
(2, '현금', '테스트자산3', 1000)
;

-- 잔액 없이 생성 테스트
INSERT INTO property(user_id, `group`, `name`) VALUES
-- 1번 유저
(1, '카드', '테스트자산4'),
-- 1번 유저
(2, '카드', '테스트자산4')
;

-- 샘플 transaction


-- *수입
-- (날짜, 내용 제외)
INSERT INTO transaction(property_id, user_id, transaction_type, money, category) VALUES
(1, 1, '수입',2800000,'월급'),
(1,1, '수입', 5000,'용돈'),
(2,2,'수입',  2800000,'월급'),
(2,2,'수입', 2800000,'월급')
;
-- (날짜, 내용 포함)
INSERT INTO transaction(property_id, user_id, transaction_type, regdate, money, category, content) VALUES
(1,1,'수입','2021-01-01 00:00:00', 3000000,'월급','2021년 1월 1일'),
(1,1,'수입','2021-02-01 00:00:00', 5000,'용돈', '2021년 2월 1일'),
(1,1,'수입','2021-03-01 00:00:00', 5000,'용돈', '2021년 3월 1일'),
(1,1,'수입','2021-04-01 00:00:00', 5000,'용돈', '2021년 4월 1일'),
(1,1,'수입','2021-05-01 00:00:00', 5000,'용돈', '2021년 5월 1일'),
(1,1,'수입','2021-06-01 00:00:00', 5000,'용돈', '2021년 6월 1일'),
(1,1,'수입','2021-07-01 00:00:00', 5000,'용돈', '2021년 7월 1일'),
(1,1,'수입','2021-08-01 00:00:00', 5000,'용돈', '2021년 8월 1일'),
(1,1,'수입','2021-09-01 00:00:00', 5000,'용돈', '2021년 9월 1일'),
(1,1,'수입','2021-10-01 00:00:00', 5000,'용돈', '2021년 10월 1일'),
(1,1,'수입','2021-11-01 00:00:00', 5000,'용돈', '2021년 11월 1일'),
(1,1,'수입','2021-12-01 00:00:00', 5000,'용돈', '2021년 12월 1일')

(1,1,'수입','2022-01-22 00:00:00', 3000000,'월급','2022년 1월 1일'),
(1,1,'수입','2022-02-22 00:00:00', 5000,'용돈', '2022년 2월 22일'),
(1,1,'수입','2022-03-22 00:00:00', 5000,'용돈', '2022년 3월 22일'),
(1,1,'수입','2022-04-22 00:00:00', 5000,'용돈', '2022년 4월 22일'),
(1,1,'수입','2022-05-22 00:00:00', 5000,'용돈', '2022년 5월 22일'),
(1,1,'수입','2022-06-22 00:00:00', 5000,'용돈', '2022년 6월 22일'),
(1,1,'수입','2022-07-22 00:00:00', 5000,'용돈', '2022년 7월 22일'),
(1,1,'수입','2022-08-22 00:00:00', 5000,'용돈', '2022년 8월 22일'),
(1,1,'수입','2022-09-22 00:00:00', 5000,'용돈', '2022년 9월 22일'),
(1,1,'수입','2022-10-22 00:00:00', 5000,'용돈', '2022년 10월 22일'),
(1,1,'수입','2022-11-22 00:00:00', 5000,'용돈', '2022년 11월 22일'),
(1,1,'수입','2022-12-22 00:00:00', 5000,'용돈', '2022년 12월 22일'),

(2,2,'수입','2020-01-01 00:00:00', 3000000,'월급','2021년 2월 1일'),
(2,2,'수입','2020-02-01 00:00:00', 5000,'용돈', '2021년 2월 1일')
;



-- *지출
-- (날짜, 내용 제외)
INSERT INTO transaction(property_id,user_id, transaction_type, money, category) VALUES
(1,1,'지출', 9000,'식비'),
(1,1,'지출', 1500,'교통비'),
(2,2,'지출', 50000,'쇼핑'),
(2,2,'지출', 4000,'기타')
;

-- (날짜, 내용 포함)
INSERT INTO transaction(property_id, user_id,transaction_type, regdate, money, category, content) VALUES
(1,1,'지출','2023-01-01 00:00:00', 9000,'식비','테스트내용1'),
(1,1,'지출','202-02-01 00:00:00', 1500,'교통비','테스트내용2'),
(2,2,'지출','2020-01-01 00:00:00', 50000,'쇼핑','테스트내용3'),
(2,2,'지출','2020-01-01 00:00:00', 4000,'기타','테스트내용4')
;


-- 수수료
INSERT INTO transaction(property_id, user_id,transaction_type, money, category, content) VALUES
(1,1,'지출', 500,'기타','수수료'),
(1,1,'지출', 500,'기타','수수료'),
(2,2,'지출', 500,'기타','수수료'),
(2,2,'지출', 500,'기타','수수료')
;


-- *이체
-- (날짜, 내용 제외)
INSERT INTO transaction(property_id, user_id,transaction_type, money, category, in_property_id) VALUES
(1,1,'이체',10000,'이체',2),
(1,1,'이체',20000,'이체',2),
(2,2,'이체',30000,'이체',1),
(2,2,'이체',40000,'이체',1)
;


-- (날짜, 내용 포함)
INSERT INTO transaction(property_id, user_id,transaction_type, regdate, money, category, content, in_property_id) VALUES
(1,1,'이체','2022-01-01 00:00:00',10000,'이체','이체내용1',2),
(1,1,'이체','2022-01-01 00:00:00',20000,'이체','이체내용2',2),
(2,2,'이체','2021-01-01 00:00:00',30000,'이체','이체내용3',1),
(2,2,'이체','2021-01-01 00:00:00',40000,'이체','이체내용4',1)
;
