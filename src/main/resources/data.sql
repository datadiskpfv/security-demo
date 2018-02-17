insert into role (name) values ('ADMIN');
insert into role (name) values ('SHOPPING');
insert into role (name) values ('USER');

insert into user (username, password) values ('pvalle', '$2a$10$JPXZlt2D0MdI3wUdrkBDA.uR3qkcCK7H7g93ixz3yNKaGEXEfR6eO');
insert into user (username, password) values ('lvalle', '$2a$10$JPXZlt2D0MdI3wUdrkBDA.uR3qkcCK7H7g93ixz3yNKaGEXEfR6eO');
insert into user (username, password) values ('dvalle', '$2a$10$JPXZlt2D0MdI3wUdrkBDA.uR3qkcCK7H7g93ixz3yNKaGEXEfR6eO');
insert into user (username, password) values ('jvalle', '$2a$10$JPXZlt2D0MdI3wUdrkBDA.uR3qkcCK7H7g93ixz3yNKaGEXEfR6eO');

insert into user_role (user_id, role_id) values (1,1);
insert into user_role (user_id, role_id) values (1,2);
insert into user_role (user_id, role_id) values (1,3);
insert into user_role (user_id, role_id) values (2,2);
insert into user_role (user_id, role_id) values (2,3);
insert into user_role (user_id, role_id) values (3,2);
insert into user_role (user_id, role_id) values (3,3);
insert into user_role (user_id, role_id) values (4,3);