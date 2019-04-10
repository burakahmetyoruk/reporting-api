insert into api_user(email,password) values ('test@email.com', '$2a$12$4r23uS7HL35SxNczFzuNJekse8huw45l5YUoFxiEFmSfmnDyY53wK');

insert into merchant(id, name) values (1,'merchant');

insert into customer_info(id, number, email, billing_first_name, billing_last_name,created_at,updated_at) values (1, '1XXX345', 'customer@email.com', 'first-name', 'last-name',sysdate(), sysdate());

insert into acquirer(id,name) values(1, 'acquirer');

insert into fx(id, amount,currency) values(1, 10, 'EUR');

insert into transaction (transaction_id,merchant_id, status,transaction_date,customer_info_id,acquirer_id,fx_id,reference_no) values ('b1f3efe3-0636-4360-9ef3-b89ad463dad7',1,'APPROVED','2019-04-10',1,1,1,'reference-no');