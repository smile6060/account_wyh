-- 강사님은 table sql임

use kordb;

create table member (
    id int not null auto_increment,
    userid varchar(20),
    username varchar(20),
    password varchar(20),
    level int,
    primary key(id)
);

SELECT * FROM member;

--------------------------------------------------
-- grp 댓글 달 원본 글 값
-- depth 댓글 순서0


create table board(
    id int not null auto_increment,
    subject varchar(50),
    writer varchar(20),
    grp int,
    depth int,
    primary key(id)
);

SELECT * FROM board;


