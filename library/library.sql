create table student(
student_id varchar(20),
student_name varchar(5),
onelimit int,
total_number int,
constraint ck_num check(onelimit <=5),
primary key(student_id));

create table bookshelf(bookshelf_id int auto_increment,location varchar(20),subjects varchar(10),primary_key(bookshelf_id));
create table book(
book_id int auto_increment,
book_name varchar(20),
author varchar(10),
subjects varchar(15),
publisher varchar(10),
bookshelf_id int,
isbn varchar(20),
is_borrowed int,
primary key(book_id),
constraint fk_bookshelf foreign key
(bookshelf_id) references bookshelf(bookshelf_id));
create table borrow_return(
book_id int,
student_id varchar(20),
borrow_time datetime,
return_tiem datetime,
amerce float);
delimiter ||
create trigger compute_amerce after insert on borrow_return for 
each row 
begin
if new.return_time!=null then
if  DATEDIFF(DATE(new.borrow_time),DATE(new.return_time))>30 then
  update borrow_return set amerce=DATEDIFF(DATE(new.borrow_time),DATE(new.return_time))*0.01 where book_id=new.book_id and student_id=new.student_id and  borrow_time=new.borrow_timecompute_amerce;
end if;
end if;
end ||
delimiter ;