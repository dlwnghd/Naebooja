USE naeboojadb;

SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS user_authorities;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS file;
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS property;
DROP TABLE IF EXISTS `write`;
DROP TABLE IF EXISTS `user`;




/* Create Tables */

CREATE TABLE authority
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(40) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (name)
);


CREATE TABLE comment
(
	idx int NOT NULL AUTO_INCREMENT,
	user_idx int NOT NULL,
	write_idx int NOT NULL,
	content text NOT NULL,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (idx)
);


CREATE TABLE file
(
	idx int NOT NULL AUTO_INCREMENT,
	write_idx int NOT NULL,
	-- OriginalName
	source varchar(100) NOT NULL COMMENT 'OriginalName',
	-- SaveFileName
	file varchar(100) NOT NULL COMMENT 'SaveFileName',
	PRIMARY KEY (idx)
);


CREATE TABLE property
(
	idx int NOT NULL AUTO_INCREMENT,
	user_idx int NOT NULL,
	-- 현금, 카드
	`group` varchar(20) NOT NULL COMMENT '현금, 카드',
	-- 사용자가 붙이는 자산의 이름
	name varchar(20) NOT NULL COMMENT '사용자가 붙이는 자산의 이름',
	-- 자산의 잔액
	rest_money int DEFAULT 0 NOT NULL COMMENT '자산의 잔액',
	PRIMARY KEY (idx)
);


CREATE TABLE transaction
(
	idx int NOT NULL AUTO_INCREMENT,
	property_idx int NOT NULL,
	transaction_type enum('수입', '지출', '이체') CHARACTER SET utf8 NOT NULL,
	regdate datetime NOT NULL DEFAULT now(),
	money int DEFAULT 0 NOT NULL,
	classfication enum('이체','월급','용돈','식비','교통비','쇼핑','기타'),
	content text,
	-- 이체일때만
	fee int COMMENT '이체일때만',
	-- 이체일때 돈이 들어오는 자산의 이름
	in_property varchar(20) COMMENT '이체일때 돈이 들어오는 자산의 이름',
	-- 이체일때 돈이 나가는 자산의 이름
	out_property varchar(20) COMMENT '이체일때 돈이 나가는 자산의 이름',
	PRIMARY KEY (idx)
);


CREATE TABLE user
(
	idx int NOT NULL AUTO_INCREMENT,
	id varchar(20) NOT NULL,
	password varchar(100) NOT NULL,
	email varchar(50) NOT NULL,
	phonenum varchar(20) NOT NULL,
	PRIMARY KEY (idx),
	UNIQUE (id),
	UNIQUE (email)
);


CREATE TABLE user_authorities
(
	user_id int NOT NULL,
	authority_id int NOT NULL
);


CREATE TABLE `write`
(
	idx int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	subject varchar(200) NOT NULL,
	content longtext,
	viewcnt int DEFAULT 0,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (idx)
);



/* Create Foreign Keys */

ALTER TABLE user_authorities
	ADD FOREIGN KEY (authority_id)
	REFERENCES authority (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE transaction
	ADD FOREIGN KEY (property_idx)
	REFERENCES property (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE comment
	ADD FOREIGN KEY (user_idx)
	REFERENCES user (idx)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE property
	ADD FOREIGN KEY (user_idx)
	REFERENCES user (idx)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_authorities
	ADD FOREIGN KEY (user_id)
	REFERENCES user (idx)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE `write`
	ADD FOREIGN KEY (user_id)
	REFERENCES user (idx)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE comment
	ADD FOREIGN KEY (write_idx)
	REFERENCES `write` (idx)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE file
	ADD FOREIGN KEY (write_idx)
	REFERENCES `write` (idx)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;