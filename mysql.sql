-- 기본적인 용어
-- 스키마(Schema) - 데이터베이스, 프로젝트 하나의 저장소
-- 테이블(Table) - 데이터를 저장하는 표 (회원정보, 게시물 등등)
-- 행(Row, Tuple) - 테이블에 들어가는 한 줄의 데이터
-- 카디널리티(Cardinality) - 튜플의 갯수
-- 열(Column, Attribute) - 속성 하나 (id, 이름, 나이 등등)
-- 차수(Degree) - 속성의 갯수

-- DDL, DML, DCL

-- DDL (데이터 정의어)
-- CREATE DATABASE DB생성
-- CREATE TABLE 테이블 생성
-- ALTER TABLE 테이블 수정 (칼럼 추가, 수정 등)
-- DROP TABLE 테이블 삭제

-- DML (데이터 조작어)
-- INSERT INTO 데이터 추가
-- SELECT 데이터 조회

insert into
	user_tb (user_id, username, password, age, create_dt)
values
	(0, "yoon2", "1q2w3e4r", null, now());
    
select
	*
from
	user_tb
where
	username like "dong%"
    or username like "%2"
order by
	age asc;


-- todo_tb => todo_id, content, username, create_dt, update_dt
-- update_dt는 null가능
-- 테이블 만들고 데이터 5개 삽입하기 (3개는 여러분의 영문이름으로 username 넣기)
-- 전체 조회, 최신순 조회, 여러분의 영문이름 username만 조회
insert into
	todo_tb (todo_id, content, username, create_dt, update_dt)
values
	(0, "퇴근하기", "dongyoon", now(), null),
    (0, "밥먹기", "dongyoon", now(), null),
    (0, "공부하기", "tom", now(), null),
    (0, "자기", "dongyoon", now(), null),
    (0, "운동하기", "herry", now(), null);
    
select
	todo_id,
    content,
    username,
    create_dt,
    update_dt
from
	todo_tb;
    
select
	*
from
	todo_tb
order by
	content;
    
select
	*
from
	todo_tb
where
	username = "dongyoon";

-- =, !=, <, >, <=, >=