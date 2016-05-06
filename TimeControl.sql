-- Sequenc Drop

drop sequence memberStaySeq;
drop sequence memberSeq;
drop Sequence mateStaySeq;
drop sequence postCodeSeq;
drop sequence replySeq;
drop sequence reserveSeq;

-- Table Drop
drop table t_alert cascade constraints PURGE;
drop table t_category cascade constraints PURGE;
drop table t_category2 cascade constraints PURGE;
drop table t_category2Mate cascade constraints PURGE;
drop table t_check cascade constraints PURGE;
drop table t_hate cascade constraints PURGE;
drop table t_like cascade constraints PURGE;
drop table t_mate cascade constraints PURGE;
drop table t_mateGroup cascade constraints PURGE;
drop table t_mateStay cascade constraints PURGE;
drop table t_mateTag cascade constraints PURGE;
drop table t_member cascade constraints PURGE;
drop table t_memberState cascade constraints PURGE;
drop table t_memberStay cascade constraints PURGE;
drop table t_myPhoto cascade constraints PURGE;
drop table t_openLimited cascade constraints PURGE;
drop table t_post cascade constraints PURGE;
drop table t_postPhoto cascade constraints PURGE;
drop table t_reply cascade constraints PURGE;
drop table t_reserveMateTag cascade constraints PURGE;
drop table t_reservePost cascade constraints PURGE;
drop table t_reservePostPhoto cascade constraints PURGE;
drop table t_tableName cascade constraints PURGE;

-- Sequence Create

create sequence memberStaySeq;
create sequence memberSeq;
create Sequence mateStaySeq;
create sequence postCodeSeq;
create sequence replySeq;
create sequence reserveSeq;

-- Table Create

/* 친구분류 */
CREATE TABLE t_mateGroup (
	mateGroupCode NUMBER NOT NULL, /* 친구분류코드 */
	mateState VARCHAR2(12) NOT NULL /* 친구상태 */
);

ALTER TABLE t_mateGroup
	ADD
		CONSTRAINT PK_mateGroup_mateGroupCode
		PRIMARY KEY (
			mateGroupCode
		);

ALTER TABLE t_mateGroup
	ADD
		CONSTRAINT UK_mateGroup_mateState
		UNIQUE (
			mateState
		);

/* 친구 */
CREATE TABLE t_mate (
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	mateMemberCode NUMBER NOT NULL, /* 친구코드(멤버코드) */
	mateGroupCode NUMBER DEFAULT 0 NOT NULL /* 친구분류코드 */
);

/* 프로필사진 */
CREATE TABLE t_myPhoto (
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	photoURL VARCHAR2(100) NOT NULL /* 사진경로 */
);

/* 멤버상태 */
CREATE TABLE t_memberState (
	memberStateCode NUMBER NOT NULL, /* 멤버상태코드 */
	memberState VARCHAR2(9) NOT NULL /* 멤버상태 */
);

ALTER TABLE t_memberState
	ADD
		CONSTRAINT PK_memberState_memberStateCode
		PRIMARY KEY (
			memberStateCode
		);

ALTER TABLE t_memberState
	ADD
		CONSTRAINT UK_memberState_memberState
		UNIQUE (
			memberState
		);

/* 카테고리 */
CREATE TABLE t_category (
	memberCode NUMBER NOT NULL,
	ca2Check NUMBER DEFAULT 0 NOT NULL,
	categoryTitle VARCHAR2(30) DEFAULT '나의 친구들 소식' NOT NULL
);

ALTER TABLE t_category
	ADD
		CONSTRAINT UK_category_memberCode
		UNIQUE (
			memberCode
		);

/* 카테고리2 */
CREATE TABLE t_category2 (
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	categoryTitle VARCHAR2(30) DEFAULT '카테고리 2' NOT NULL /* 카테고리2제목 */
);

ALTER TABLE t_category2
	ADD
		CONSTRAINT PK_category2_memberCode
		PRIMARY KEY (
			memberCode
		);

/* 확인여부 */
CREATE TABLE t_check (
	checkCode NUMBER DEFAULT 0 NOT NULL, /* 확인코드 */
	checkState VARCHAR2(1) NOT NULL /* 확인상태 */
);

ALTER TABLE t_check
	ADD
		CONSTRAINT PK_check_checkCode
		PRIMARY KEY (
			checkCode
		);

ALTER TABLE t_check
	ADD
		CONSTRAINT UK_check_checkState
		UNIQUE (
			checkState
		);

/* 알림 */
CREATE TABLE t_alert (
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	tableNameCode NUMBER NOT NULL, /* 참조테이블코드 */
	codeName NUMBER NOT NULL, /* 참조내용코드 */
	alertDate DATE NOT NULL, /* 알림받은날짜 */
	confirm NUMBER DEFAULT 0 NOT NULL /* 알림확인코드(확인코드) */
);

/* 참조테이블 */
CREATE TABLE t_tableName (
	tableNameCode NUMBER NOT NULL, /* 참조테이블코드 */
	tableName VARCHAR2(40) NOT NULL /* 테이블이름 */
);

ALTER TABLE t_tableName
	ADD
		CONSTRAINT PK_tableName2_tableNameCode
		PRIMARY KEY (
			tableNameCode
		);

ALTER TABLE t_tableName
	ADD
		CONSTRAINT UK_tableName2_tableName
		UNIQUE (
			tableName
		);

/* 예약게시글 */
CREATE TABLE t_reservePost (
	reserveCode NUMBER NOT NULL, /* 예약코드 */
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	postContent VARCHAR2(4000) NOT NULL, /* 게시내용 */
	reserveDate DATE DEFAULT SYSDATE NOT NULL, /* 예약날짜 */
	pungCode NUMBER DEFAULT 0, /* 펑코드 */
	reserveTime DATE NOT NULL /* 예약시간 */
);

ALTER TABLE t_reservePost
	ADD
		CONSTRAINT PK_reservePost_reserveCode
		PRIMARY KEY (
			reserveCode
		);

/* 예약게시글사진 */
CREATE TABLE t_reservePostPhoto (
	reserveCode NUMBER NOT NULL, /* 예약코드 */
	photoURL VARCHAR2(100) NOT NULL /* 사진경로 */
);

/* 예약친구태그 */
CREATE TABLE t_reserveMateTag (
	reserveCode NUMBER NOT NULL, /* 예약코드 */
	memberCode NUMBER NOT NULL /* 멤버코드 */
);

/* 멤버 */
CREATE TABLE t_member (
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	email VARCHAR2(40) NOT NULL, /* 이메일 */
	name VARCHAR2(20) NOT NULL, /* 이름 */
	pw VARCHAR2(90) NOT NULL, /* 비밀번호 */
	memberStateCode NUMBER DEFAULT 0 NOT NULL, /* 멤버상태코드 */
	openLimitedCode NUMBER DEFAULT 0 NOT NULL, /* 공개범위코드 */
	myPhotoCheck NUMBER DEFAULT 0 NOT NULL /* 프로필사진(확인코드) */
);

ALTER TABLE t_member
	ADD
		CONSTRAINT PK_member_memberCode
		PRIMARY KEY (
			memberCode
		);

ALTER TABLE t_member
	ADD
		CONSTRAINT UK_member_email
		UNIQUE (
			email
		);

/* 공개범위 */
CREATE TABLE t_openLimited (
	openLimitedCode NUMBER NOT NULL, /* 공개범위코드 */
	range VARCHAR2(6) NOT NULL /* 공개범위 */
);

ALTER TABLE t_openLimited
	ADD
		CONSTRAINT PK_openLimited_openLimitedCode
		PRIMARY KEY (
			openLimitedCode
		);

ALTER TABLE t_openLimited
	ADD
		CONSTRAINT UK_openLimited_range
		UNIQUE (
			range
		);

/* 친구태그 */
CREATE TABLE t_mateTag (
	postCode NUMBER NOT NULL, /* 게시글코드 */
	memberCode NUMBER NOT NULL /* 멤버코드 */
);

/* 카테고리2친구 */
CREATE TABLE t_category2Mate (
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	mateSaveCode NUMBER NOT NULL /* 저장된친구(멤버코드) */
);

/* 친구대기 */
CREATE TABLE t_mateStay (
	mateStayCode NUMBER NOT NULL, /* 참조내용코드(친구대기코드) */
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	mateMemberCode NUMBER NOT NULL /* 친구코드(멤버코드) */
);

ALTER TABLE t_mateStay
	ADD
		CONSTRAINT PK_mateStay_mateStayCode
		PRIMARY KEY (
			mateStayCode
		);

/* 멤버가입대기 */
CREATE TABLE t_memberStay (
	memberStayCode NUMBER NOT NULL, /* 대기코드 */
	email VARCHAR2(40) NOT NULL, /* 이메일 */
	name VARCHAR2(20) NOT NULL, /* 이름 */
	pw VARCHAR2(90) NOT NULL /* 비밀번호 */
);

ALTER TABLE t_memberStay
	ADD
		CONSTRAINT PK_memberStay_memberStayCode
		PRIMARY KEY (
			memberStayCode
		);

ALTER TABLE t_memberStay
	ADD
		CONSTRAINT UK_memberStay_email
		UNIQUE (
			email
		);

/* 좋아요 */
CREATE TABLE t_like (
	postCode NUMBER NOT NULL, /* 게시글코드 */
	memberCode NUMBER NOT NULL /* 멤버코드 */
);

/* 싫어요 */
CREATE TABLE t_hate (
	postCode NUMBER NOT NULL, /* 게시글코드 */
	memberCode NUMBER NOT NULL /* 멤버코드 */
);

/* 게시글 */
CREATE TABLE t_post (
	postCode NUMBER NOT NULL, /* 게시글코드 */
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	postContent VARCHAR2(4000) NOT NULL, /* 게시내용 */
	postDate DATE DEFAULT SYSDATE NOT NULL, /* 게시날짜 */
	pungCode NUMBER DEFAULT 0, /* 펑코드 */
	likeCount NUMBER DEFAULT 0, /* 좋아요카운트 */
	hateCount NUMBER DEFAULT 0, /* 싫어요카운트 */
	mateCode NUMBER DEFAULT 0 /* 받는사람코드(멤버코드) */
);

ALTER TABLE t_post
	ADD
		CONSTRAINT PK_post_postCode
		PRIMARY KEY (
			postCode
		);

/* 게시글사진 */
CREATE TABLE t_postPhoto (
	postCode NUMBER NOT NULL, /* 게시글코드 */
	photoURL VARCHAR2(100) NOT NULL /* 사진 */
);

/* 댓글 */
CREATE TABLE t_reply (
	replyCode NUMBER NOT NULL, /* 댓글코드 */
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	postCode NUMBER NOT NULL, /* 게시글코드 */
	replyContent VARCHAR2(4000) NOT NULL, /* 댓글내용 */
	replyDate DATE DEFAULT SYSDATE NOT NULL /* 댓글시간 */
);

ALTER TABLE t_reply
	ADD
		CONSTRAINT PK_reply_replyCode
		PRIMARY KEY (
			replyCode
		);

ALTER TABLE t_mate
	ADD
		CONSTRAINT FK_mate_mateGroupCode
		FOREIGN KEY (
			mateGroupCode
		)
		REFERENCES t_mateGroup (
			mateGroupCode
		);

ALTER TABLE t_mate
	ADD
		CONSTRAINT FK_mate_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_mate
	ADD
		CONSTRAINT FK_mate_mateMemberCode
		FOREIGN KEY (
			mateMemberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_myPhoto
	ADD
		CONSTRAINT FK_myPhoto_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_category
	ADD
		CONSTRAINT FK_category_ca2Code
		FOREIGN KEY (
			ca2Check
		)
		REFERENCES t_check (
			checkCode
		);

ALTER TABLE t_category
	ADD
		CONSTRAINT FK_category_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_alert
	ADD
		CONSTRAINT FK_alert_confirm
		FOREIGN KEY (
			confirm
		)
		REFERENCES t_check (
			checkCode
		);

ALTER TABLE t_alert
	ADD
		CONSTRAINT FK_alert_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_alert
	ADD
		CONSTRAINT FK_alert_codeName
		FOREIGN KEY (
			codeName
		)
		REFERENCES t_mateStay (
			mateStayCode
		);

ALTER TABLE t_alert
	ADD
		CONSTRAINT FK_alert_tableNameCode
		FOREIGN KEY (
			tableNameCode
		)
		REFERENCES t_tableName (
			tableNameCode
		);

ALTER TABLE t_reservePost
	ADD
		CONSTRAINT FK_reservePost_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_reservePostPhoto
	ADD
		CONSTRAINT FK_reservePPhoto_reserveCode
		FOREIGN KEY (
			reserveCode
		)
		REFERENCES t_reservePost (
			reserveCode
		);

ALTER TABLE t_reserveMateTag
	ADD
		CONSTRAINT FK_reserveMateTag_reserveCode
		FOREIGN KEY (
			reserveCode
		)
		REFERENCES t_reservePost (
			reserveCode
		);

ALTER TABLE t_reserveMateTag
	ADD
		CONSTRAINT FK_reserveMateTag_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_member
	ADD
		CONSTRAINT FK_member_openLimitedCode
		FOREIGN KEY (
			openLimitedCode
		)
		REFERENCES t_openLimited (
			openLimitedCode
		);

ALTER TABLE t_member
	ADD
		CONSTRAINT FK_member_memberStateCode
		FOREIGN KEY (
			memberStateCode
		)
		REFERENCES t_memberState (
			memberStateCode
		);

ALTER TABLE t_member
	ADD
		CONSTRAINT FK_member_myPhotoCheck
		FOREIGN KEY (
			myPhotoCheck
		)
		REFERENCES t_check (
			checkCode
		);

ALTER TABLE t_mateTag
	ADD
		CONSTRAINT FK_mateTag_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_mateTag
	ADD
		CONSTRAINT FK_mateTag_postCode
		FOREIGN KEY (
			postCode
		)
		REFERENCES t_post (
			postCode
		);

ALTER TABLE t_category2Mate
	ADD
		CONSTRAINT FK_category2Mate_mateSaveCode
		FOREIGN KEY (
			mateSaveCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_category2Mate
	ADD
		CONSTRAINT FK_category2Mate_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_category2 (
			memberCode
		);

ALTER TABLE t_mateStay
	ADD
		CONSTRAINT FK_mateStay_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_mateStay
	ADD
		CONSTRAINT FK_mateStay_mateMemberCode
		FOREIGN KEY (
			mateMemberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_like
	ADD
		CONSTRAINT FK_like_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_like
	ADD
		CONSTRAINT FK_like_postCode
		FOREIGN KEY (
			postCode
		)
		REFERENCES t_post (
			postCode
		);

ALTER TABLE t_hate
	ADD
		CONSTRAINT FK_hate_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_hate
	ADD
		CONSTRAINT FK_hate_postCode
		FOREIGN KEY (
			postCode
		)
		REFERENCES t_post (
			postCode
		);

ALTER TABLE t_post
	ADD
		CONSTRAINT FK_post_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_post
	ADD
		CONSTRAINT FK_post_mateCode
		FOREIGN KEY (
			mateCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_postPhoto
	ADD
		CONSTRAINT FK_postPhoto_postCode
		FOREIGN KEY (
			postCode
		)
		REFERENCES t_post (
			postCode
		);

ALTER TABLE t_reply
	ADD
		CONSTRAINT FK_reply_postCode
		FOREIGN KEY (
			postCode
		)
		REFERENCES t_post (
			postCode
		);

ALTER TABLE t_reply
	ADD
		CONSTRAINT FK_reply_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);
-----------------------------------------------------------------------------
--2014.12.11 t_reservePost 에 mateCode 컬럼 추가(혜미)
alter table t_reservePost add (mateCode NUMBER );

ALTER TABLE t_reservePost
	ADD
		CONSTRAINT FK_reservePost_mateCode
		FOREIGN KEY (
			mateCode
		)
		REFERENCES t_member (
			memberCode
		);
    
--2014.12.12 t_reservePost의 mateCode에 디폴트 값 세팅(혜미)
ALTER TABLE t_reservePost
	modify (mateCode default 0);
  
--2014.12.12 t_reserveMateTag의 reserveCode에 유니트 세팅(혜미)
alter table t_reserveMateTag
ADD CONSTRAINT UK_reserveMateTag_RESERVECODE UNIQUE(RESERVECODE);

-- 2014.12.12 t_alert의 codeName 참조키 설정 삭제(연규)
ALTER TABLE t_alert
	DROP
		CONSTRAINT FK_alert_codeName;

--2014.12.12 t_category2의 memberCode 참조키로 설정(혜미)
ALTER TABLE t_category2
ADD CONSTRAINT FK_category2_memberCode FOREIGN KEY(memberCode) REFERENCES t_category(memberCode);

--2014.12.12 t_category2Mate의 memberCode 참조키 테이블 t_ category 로 변경(혜미)
ALTER TABLE t_category2Mate
DROP CONSTRAINT FK_CATEGORY2MATE_MATESAVECODE;

ALTER TABLE t_category2Mate
ADD CONSTRAINT FK_CATEGORY2MATE_MATESAVECODE FOREIGN KEY(memberCode) REFERENCES t_category(memberCode);

----------------------------------------------------------------------------------------------------------
--2014.12.12 카테고리 관련 테이블 재생성(혜미)
drop table t_category cascade constraints PURGE;
drop table t_category2 cascade constraints PURGE;
drop table t_category2Mate cascade constraints PURGE;

--카테고리 테이블 재생성(혜미)
CREATE TABLE t_category (
	memberCode NUMBER NOT NULL,
	ca2Check NUMBER DEFAULT 0 NOT NULL,
	categoryTitle VARCHAR2(30) DEFAULT '나의 친구들 소식' NOT NULL
);

ALTER TABLE t_category
	ADD
		CONSTRAINT PK_category_memberCode
		PRIMARY KEY(
			memberCode
		);



ALTER TABLE t_category
	ADD
		CONSTRAINT FK_category_ca2Code
		FOREIGN KEY (
			ca2Check
		)
		REFERENCES t_check (
			checkCode
		);

ALTER TABLE t_category
	ADD
		CONSTRAINT FK_category_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_member (
			memberCode
		);
    
--카테고리2 테이블 재생성(혜미)
CREATE TABLE t_category2 (
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	categoryTitle VARCHAR2(30) DEFAULT '카테고리2 이용하기' NOT NULL /* 카테고리2제목 */
);

ALTER TABLE t_category2
	ADD
		CONSTRAINT FK_category2_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_category(
			memberCode
		);

--키테고리2친구 테이블 재생성(혜미)
CREATE TABLE t_category2Mate (
	memberCode NUMBER NOT NULL, /* 멤버코드 */
	mateSaveCode NUMBER NOT NULL /* 저장된친구(멤버코드) */
);

ALTER TABLE t_category2Mate
	ADD
		CONSTRAINT FK_category2Mate_mateSaveCode
		FOREIGN KEY (
			mateSaveCode
		)
		REFERENCES t_member (
			memberCode
		);

ALTER TABLE t_category2Mate
	ADD
		CONSTRAINT FK_category2Mate_memberCode
		FOREIGN KEY (
			memberCode
		)
		REFERENCES t_category (
			memberCode
		);
    
-----------------------------------------------------------------------------
--댓글 게시판에 삭제여부 컬럼 추가(혜미)
alter table t_reply
add (delHide NUMBER default 0);

ALTER TABLE t_reply
	ADD
		CONSTRAINT FK_reply_delHide
		FOREIGN KEY (
			delHide
		)
		REFERENCES t_check (
			checkCode
		);

--게시글 테이블에 삭제여부 컬럼 추가(혜미)
alter table t_post
add (delHide NUMBER default 0);

ALTER TABLE t_post
	ADD
		CONSTRAINT FK_post_delHide
		FOREIGN KEY (
			delHide
		)
		REFERENCES t_check (
			checkCode
		);

--예약게시글 테이블에 삭제여부 컬럼 추가(혜미)
alter table t_reservePost
add (delHide NUMBER default 0);

ALTER TABLE t_reservePost
	ADD
		CONSTRAINT FK_reservePost_delHide
		FOREIGN KEY (
			delHide
		)
		REFERENCES t_check (
			checkCode
		);
    
 --   게시글 테이블에 삭제여부 컬럼 드랍(혜미)
ALTER TABLE t_post
drop column delHide;

--알림 테이블에 삭제 여부 컬럼 추가
alter table t_alert
add (delHide NUMBER default 0);

ALTER TABLE t_alert
	ADD
		CONSTRAINT FK_alert_delHide
		FOREIGN KEY (
			delHide
		)
		REFERENCES t_check (
			checkCode
		);
-------------------------------------------------------
--게시글 테이블에 삭제여부 컬럼 재추가(혜미)
alter table t_post
add (delHide NUMBER default 0);

ALTER TABLE t_post
	ADD
		CONSTRAINT FK_post_delHide
		FOREIGN KEY (
			delHide
		)
		REFERENCES t_check (
			checkCode
		);
	