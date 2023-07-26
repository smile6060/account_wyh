use kordb;

CREATE TABLE config (
    code varchar(10), -- 게시판 테이블 이름
    title varchar(100),
    color varchar(10),
    primary key(code)
);

SELECT * FROM config;

-- 게시판 테이블 : kortb_코드이름
CREATE TABLE kortb_${code}(
    id int not null auto_increment,
    subject varchar(100),
    writer varchar(20),
    content text,
    grp int,
    depth int,
    primary key(id)
);

CREATE TABLE kortb_hrd(
    id int not null auto_increment,
    subject varchar(100),
    writer varchar(20),
    content text,
    grp int,
    depth int,
    primary key(id)
);