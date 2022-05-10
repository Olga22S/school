select *
from student
where age > 20
  and age < 25;

select name
from student;

select *
from student
where name like '%o%';

select *
from student
where age < id;

select *
from student
order by age;