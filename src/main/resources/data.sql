insert into tests (title, description, create_at,  max_timeout) values
('3xn','3xn을 만드시오.', '2021-04-16 21:00:00', 10),
('2xn','2xn을 만드시오.', '2021-04-16 21:00:00', 10),
('1xn','1xn을 만드시오.', '2021-04-16 21:00:00', 10)
;

insert into testcases(input, output, create_at, test_id) values
('[3]', '3','2021-04-16 21:00:00',1),
('[4]','4','2021-04-16 21:00:00',1)
;

insert into users(name, login_id, password) values
('심동훈','tinyhhj','admin'),
('이정재','lee','lee'),
('정우성','beat','beat')
;

insert into test_histories(user_id, test_id, error_message, pass, elapsed_time,create_at) values
(1,1,'timeout', false, 10000,'2021-04-16 21:00:00'),
(1,1,'', true, 1000,'2021-04-16 21:00:05')
;
