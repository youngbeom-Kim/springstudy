//멤버 테이블 생성 쿼리
drop table if exists member CASCADE;
SELECT * FROM MEMBER create table member
(
     id bigint generated by default as identity,
     name varchar(255),
     primary key (id)
);