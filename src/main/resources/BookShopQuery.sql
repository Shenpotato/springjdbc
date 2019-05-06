drop table administor;
create table administor(
aid varchar(20),
apassword varchar(20),
alevel int
)
select * from administor;
insert into administor(aid,apassword,alevel) values('00001','123456',3)
insert into administor(aid,apassword,alevel) values('00002','123456',4)

create table book(
isbn varchar(10),
book_name varchar(20),
price int
)

select * from book;
insert into book(isbn,book_name,price) values('1001','Java',100)
insert into book(isbn,book_name,price) values('1002','Oracle',70)

create table account(
username varchar(20),
balance int
)
truncate table account;
insert into account(username,balance) values ('AA',90);
select *from account

create table book_Stock(
isbn varchar(10),
stock int)
truncate table book_stock;
select *from book_Stock;
insert into book_Stock(isbn,stock) values('1001',4)
insert into book_Stock(isbn,stock) values('1002',4)

