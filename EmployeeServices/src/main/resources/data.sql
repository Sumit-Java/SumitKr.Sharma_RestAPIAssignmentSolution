
insert into roles (role_id, name) values(1,'ADMIN');

insert into roles (role_id, name) values(2,'CLERK');

insert into users (user_id, Password, UserName) values(1, '$2a$12$fwKZfSPi5O/PJVH1whsOEelRS5GcdVlwls9/mwvyihwhxAkV4MBre', 'Mukesh');

insert into users (user_id, Password, UserName) values(2, '$2a$12$fwKZfSPi5O/PJVH1whsOEelRS5GcdVlwls9/mwvyihwhxAkV4MBre', 'Sunil');

insert into users_roles (user_id, role_id) values(1,1);

insert into users_roles (user_id, role_id) values(2,2);

insert into Employees (Emp_id, First_name, Last_name, Email) values(1,'Sumit','Sharma','SS@google.com');
insert into Employees (Emp_id, First_name, Last_name, Email) values(2,'NK','Sharma','NK@gmail.com');
insert into Employees (Emp_id, First_name, Last_name, Email) values(3,'Bishan','Singh','BS@yahoomail.com');
insert into Employees (Emp_id, First_name, Last_name, Email) values(4,'Ankit','Sharma','AK@hotmail.com');
insert into Employees (Emp_id, First_name, Last_name, Email) values(5,'Pawan','Kumar','PK@hotmail.com');
insert into Employees (Emp_id, First_name, Last_name, Email) values(6,'Lalit','Arora','LA@hotmail.com');