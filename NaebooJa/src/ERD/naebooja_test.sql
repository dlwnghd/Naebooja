-- 테이블 전체 보기
SHOW TABLES;

-- 특정 테이블 보기
SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'naeboojadb'
AND TABLE_NAME LIKE '%'
;

-- 각 테이블 조회
SELECT * FROM authority;
SELECT * FROM `user` ORDER BY idx DESC;
SELECT * FROM user_authorities;
SELECT * FROM `write` ORDER BY user_idx DESC;
SELECT * FROM comment ORDER BY user_idx DESC;
SELECT * FROM file ORDER BY write_idx DESC;
SELECT * FROM property;
SELECT * FROM transaction;

-- 특정 id 의 사용자 조회
SELECT
	id "id"
	, password "password"
	, email "email"
FROM `user`
WHERE idx = 1
;

-- 특정 name 의 authority 조회
SELECT
	id "id"
	, name "name"
FROM authority
WHERE name = 'ROLE_ADMIN'
;

-- 특정 사용자의 authority 조회
SELECT a.id "id", a.name "auth_name"
FROM authority a, user_authorities u
WHERE a.id = u.authority_id AND u.user_id = 3	-- 3번은 관리자임
;

-- 페이징 테스트용 다량의 데이터
INSERT INTO `write`(user_idx, subject, content)
SELECT user_idx, subject, content FROM `write`;

SELECT count(*) FROM `write`;

SELECT * FROM `write` ORDER BY idx desc limit 5;

SELECT * FROM `write` ORDER BY idx desc limit 5, 5;

DELETE FROM `write` WHERE idx > 4;

# -------------------------------------------------------
# 댓글
# 특정글 의 (댓글 + 사용자) 정보
SELECT c.idx "idx", c.content "content", c.regdate "regdate",
	u.idx "user_idx", u.idx "user_idx", u.password "user_password"
FROM comment c, `user` u
WHERE c.user_idx = u.idx AND c.write_idx = 4
ORDER BY c.idx DESC
;



# -------------------------------------------------------
# -------------------------------------------------------
# --------------------- 거래정보 ---------------------------
# -------------------------------------------------------
# -------------------------------------------------------

# 특정 사용자(1번 사용자)의 전체 거래 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "수입자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE u.idx = 1
ORDER BY t.idx DESC
;

# 특정 사용자(1번 사용자)의 전체 수입 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "수입자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.transaction_type ='수입' 
	AND u.idx = 1
ORDER BY t.idx DESC
;

# 특정 사용자(1번 사용자)의 전체 지출 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "지출자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.transaction_type ='지출' 
	AND u.idx = 1
ORDER BY t.idx DESC
;

# 특정 사용자(1번 사용자)의 전체 이체 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.transaction_type ='이체' 
	AND u.idx = 1
ORDER BY t.idx DESC
;


# 특정 사용자(1번 사용자)의 오늘 거래 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", t.money "거래액",
	t.category "분류", t.content "내용"
FROM transaction t, `user` u
WHERE DATE(t.regdate) = DATE(now())  
	AND u.idx = 1
ORDER BY t.idx DESC

# 특정 사용자(1번 사용자)의 특정일 (2022-01-01) 거래 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", 
	dayname(t.regdate) "거래요일", time(t.regdate) "거래시간",  
	t.money "거래액", t.category "분류"
FROM transaction t, `user` u
WHERE DATE(t.regdate) = DATE('2023-02-15')  
	AND u.idx = 1
ORDER BY t.idx DESC

# 특정 사용자(1번 사용자)의 특정일(2022-01-01) 수입 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", dayname(t.regdate) "거래요일",time(t.regdate) "거래시간",  t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.transaction_type ='수입' AND DATE(t.regdate) = DATE('2022-01-01')  AND u.idx = 1
ORDER BY t.idx DESC

# 특정 사용자(1번 사용자)의 특정일(2022-01-01) 지출 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", dayname(t.regdate) "거래요일",time(t.regdate) "거래시간",  t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.transaction_type ='지출' AND DATE(t.regdate) = DATE('2022-01-01')  AND u.idx = 1
ORDER BY t.idx DESC

# 특정 사용자(1번 사용자)의 특정일(2022-01-01) 이체 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", dayname(t.regdate) "거래요일",time(t.regdate) "거래시간",  t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.transaction_type ='이체' AND DATE(t.regdate) = DATE('2022-01-01')  AND u.idx = 1
ORDER BY t.idx DESC

#######################주간##########################

# 오늘 기준 특정 사용자(1번 사용자)의 주간 거래 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.regdate BETWEEN DATE_SUB(NOW(), INTERVAL 1 WEEK) AND NOW() AND u.idx = 1
ORDER BY t.idx DESC


# 특정일 기준 특정 사용자(1번 사용자)의 주간 거래 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.regdate BETWEEN DATE_SUB(DATE('2022-01-01'), INTERVAL 1 WEEK) AND NOW() AND u.idx = 1
ORDER BY t.idx DESC



#######################월간##########################

# 오늘 기준 특정 사용자의 월간 거래 정보
SELECT t.idx "거래번호", u.idx "유저번호", t.property_idx "출금자산번호",
	t.in_property_idx "입금자산번호",
	t.transaction_type "거래타입", t.regdate "거래일", t.money "거래액",
	t.category "분류"
FROM transaction t, `user` u
WHERE t.regdate BETWEEN DATE_SUB(NOW(), INTERVAL 1 MONTH) 
	AND NOW() 
	AND u.idx = 1
ORDER BY t.idx DESC

# 통계페이지


# 특정 사용자(1번 사용자)의 특정 일(2023-02-15) 지출 합
SELECT sum(t.money) "특정 사용자의 특정 일(2023-02-15) 지출 합"
FROM transaction t, `user` u
WHERE t.transaction_type ='지출' -- 수입,지출,이체
	AND DATE(t.regdate) = DATE('2023-02-15')  
	AND u.idx = 1
ORDER BY t.idx DESC

# 특정 사용자의 특정 일(2023-02-15)의 차액(수입-지출)
SELECT 
    (SELECT 
    	SUM(t.money) 
    	FROM transaction t, `user` u 
    	WHERE t.transaction_type ='수입' 
    		AND DATE(t.regdate) = DATE('2023-02-15') 
    		AND u.idx = 1)
    - 
    (SELECT 
    	SUM(t.money) 
    	FROM transaction t, `user` u
    	WHERE t.transaction_type ='지출' 
    		AND DATE(t.regdate) = DATE('2023-02-15') 
    		AND u.idx = 1) 
    AS "합산"
;


# 통계 페이지


# 특정 사용자의 특정일(2023-02-15) 카테고리별 지출 비율
# 일,월,년을 빼면 일별,월별,연별로 볼 수 있음
SELECT 
    category, 
    ROUND(SUM(money) / 
        (SELECT SUM(money) 
         FROM TRANSACTION, `user` u
         WHERE transaction_type = '지출' 
         AND u.idx = 1 
         AND YEAR(regdate) = 2023 -- 년
         AND MONTH(regdate) = 02 -- 월
         AND DAY(regdate) = 15 -- 일
        ) * 100, 2) AS percentage 
FROM TRANSACTION, `user` u
WHERE transaction_type = '지출' 
AND u.idx = 1 
AND YEAR(regdate) = 2023 -- 년
AND MONTH(regdate) = 02 -- 월
AND DAY(regdate) = 15 -- 일
GROUP BY category;


# 특정 사용자의 전체 자산의 전체 수입 구하기
# 전체 수입 + 이체 받은값

# 1번 자산의 전체 수입
SELECT sum(t.money) "1번 자산의 전체 수입"
	FROM transaction t, `user` u
	WHERE t.transaction_type ='수입' 
		AND u.idx = 1
	ORDER BY t.idx DESC
;

# 1번 자산에서 이체로 나간 값
SELECT sum(t.money) "1번 자산에서 이체로 나간 값"
	FROM transaction t, `user` u
	WHERE t.transaction_type ='이체' 
		AND u.idx = 1 AND t.property_idx =1
	ORDER BY t.idx DESC
;

# 1번 자산에서 이체로 받은 값 
SELECT sum(t.money) "1번 자산에서 이체로 받은 값"
FROM transaction t, `user` u
WHERE t.transaction_type ='이체' 
	AND u.idx = 1 AND t.in_property_idx = 1
ORDER BY t.idx DESC;

# 특정 사용자(1번 사용자)의 전체 자산의 전체 수입
SELECT sum(
	(SELECT sum(t.money) "1번 자산의 수입 총합"
		FROM transaction t, `user` u
		WHERE t.transaction_type ='수입' 
			AND u.idx = 1
		ORDER BY t.idx DESC)
+
	(SELECT sum(t.money) "1번 자산에서 이체로 받은 값"
		FROM transaction t, `user` u
		WHERE t.transaction_type ='이체' 
			AND u.idx = 1 
			AND t.in_property_idx = 1
		ORDER BY t.idx DESC)
) AS "특정 사용자의 전체 자산의 전체 수입";


# 특정 사용자의 자산의 전체 지출
# 전체 지출 + 이체 받은값
SELECT sum(
	(SELECT sum(t.money) "1번 자산에서 이체로 나간 값"
		FROM transaction t, `user` u
		WHERE t.transaction_type ='이체' 
			AND u.idx = 1 
			AND t.property_idx =1
		ORDER BY t.idx DESC)
+
	(SELECT sum(t.money) "1번 자산에서 이체로 받은 값"
		FROM transaction t, `user` u
		WHERE t.transaction_type ='이체' 
			AND u.idx = 1 
			AND t.in_property_idx = 1
		ORDER BY t.idx DESC)
) AS "특정 사용자의 전체 자산의 전체 수입";
# 특정 사용자의 자산의 전체 합계