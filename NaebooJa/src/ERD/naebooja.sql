SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS user_authorities;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS file;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS transfer;
DROP TABLE IF EXISTS property;
DROP TABLE IF EXISTS user;




/* Create Tables */

CREATE TABLE authority
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(40) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (name)
);


CREATE TABLE board
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	subject varchar(200) NOT NULL,
	content longtext,
	viewcnt int DEFAULT 0,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (id)
);


CREATE TABLE comment
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	write_id int NOT NULL,
	content text NOT NULL,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (id)
);


CREATE TABLE file
(
	id int NOT NULL AUTO_INCREMENT,
	write_id int NOT NULL,
	-- OriginalName
	source varchar(100) NOT NULL COMMENT 'OriginalName',
	-- SaveFileName
	file varchar(100) NOT NULL COMMENT 'SaveFileName',
	PRIMARY KEY (id)
);


CREATE TABLE property
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	-- '현금', '카드'
	category enum('현금', '카드') NOT NULL COMMENT '''현금'', ''카드''',
	-- 사용자가 붙이는 자산의 이름
	name varchar(20) NOT NULL COMMENT '사용자가 붙이는 자산의 이름',
	-- 자산의 잔액
	rest_money int DEFAULT 0 NOT NULL COMMENT '자산의 잔액',
	PRIMARY KEY (id)
);


CREATE TABLE transaction
(
	id int NOT NULL AUTO_INCREMENT,
	-- 수입, 지출, 이체 시 제일 첫번째로 대상이 될 기본 자산의 번호
	property_id int NOT NULL COMMENT '수입, 지출, 이체 시 제일 첫번째로 대상이 될 기본 자산의 번호',
	user_id int NOT NULL,
	transaction_type enum('수입', '지출') CHARACTER SET utf8 NOT NULL,
	regdate datetime NOT NULL DEFAULT now(),
	money int DEFAULT 0 NOT NULL,
	-- '이체','월급','용돈','식비','교통비','쇼핑','기타'
	category enum('이체','월급','용돈','식비','교통비','쇼핑','기타') DEFAULT '기타' NOT NULL COMMENT '''이체'',''월급'',''용돈'',''식비'',''교통비'',''쇼핑'',''기타''',
	content text,
	PRIMARY KEY (id)
);


CREATE TABLE transfer
(
	id int NOT NULL,
	user_id int NOT NULL,
	in_property_id int NOT NULL,
	out_property_id int NOT NULL,
	money int NOT NULL,
	content text,
	regdate datetime DEFAULT NOW(), SYSDATE() NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE user
(
	id int NOT NULL AUTO_INCREMENT,
	username varchar(20) NOT NULL,
	password varchar(100) NOT NULL,
	name varchar(80) NOT NULL,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (id),
	UNIQUE (username)
);


CREATE TABLE user_authorities
(
	user_id int NOT NULL,
	authority_id int NOT NULL
);



/* Create Foreign Keys */

ALTER TABLE user_authorities
	ADD FOREIGN KEY (authority_id)
	REFERENCES authority (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE comment
	ADD FOREIGN KEY (write_id)
	REFERENCES board (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE file
	ADD FOREIGN KEY (write_id)
	REFERENCES board (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE transaction
	ADD FOREIGN KEY (property_id)
	REFERENCES property (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE transfer
	ADD FOREIGN KEY (in_property_id)
	REFERENCES property (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE transfer
	ADD FOREIGN KEY (out_property_id)
	REFERENCES property (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE board
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE comment
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE property
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE transaction
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE transfer
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_authorities
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;



