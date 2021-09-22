/*
-- create session
INSERT INTO session_tbl (counter, restaurant, status) VALUES (10, 'outback', 'OPEN')

-- insert order
INSERT INTO order_tbl (name, description, value) VALUES('Bloomin’ Onion', 'Nossa famosa cebola gigante e dourada com o autêntico sabor do Outback. Acompanha nosso maravilhoso molho Bloom.', '44.90');
INSERT INTO order_tbl (name, description, value) VALUES('Ribs Coxinha', 'A coxinha com a cara e o Bold Flavour do Outback. No melhor Aussie Style, 8 coxinhas recheadas com a nossa suculenta Ribs on the Barbie, cream cheese e um toque de limão siciliano, em uma combinação incrível com nossos temperos secretos que finalizam a receita. Acompanha molho Barbecue Ranch.', '44.90');
INSERT INTO order_tbl (name, description, value) VALUES('Aussie Cheese Fries', 'Nossas fritas com uma cobertura irresistível com mix de queijos e bacon. O toque final fica por conta do molho Ranch. Transforme sua batata em veggie pedindo sem bacon.', '58.50');
INSERT INTO order_tbl (name, description, value) VALUES('Super Wings', 'Nossas famosas Kookaburra Wings numa porção ainda maior. São 15 sobreasas de frango servidas com o molho Blue Cheese (contém açúcar e lactose) e aipo crocante. Nas opções light, médio ou hot. Contém glúten.', '74.90');

-- insert session user
INSERT INTO session_user_tbl (user_id) VALUES (1)
INSERT INTO session_user_tbl (user_id) VALUES (2)
INSERT INTO session_user_tbl (user_id) VALUES (3)

-- add order to user
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (1, 1)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (1, 2)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (1, 2)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (1, 1)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (2, 1)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (2, 2)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (2, 2)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (2, 3)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (3, 3)
INSERT INTO session_user_tbl_order_list (session_user_tbl_id, order_list_id) VALUES (3, 1)

-- add order to session
insert into session_tbl_orders (session_tbl_id, orders_id) values (1, 1)
insert into session_tbl_orders (session_tbl_id, orders_id) values (1, 1)
insert into session_tbl_orders (session_tbl_id, orders_id) values (1, 2)
insert into session_tbl_orders (session_tbl_id, orders_id) values (1, 2)
insert into session_tbl_orders (session_tbl_id, orders_id) values (1, 3)

INSERT INTO CATEGORY_TBL (name) VALUES('Aperitivos');

INSERT INTO MENU_TBL (restaurant) VALUES('Outback');
*/

INSERT INTO USERS_TBL (name, cpf, email, secret, phone) VALUES('Gabriel Monteiro', '999.999.999-00', 'gabriel@partypay.com', '$2a$10$LOl4Ggr6c8D7Z3pC951kHeN/fyoBo24mt9hY1RG5w7eiXpTX1YL7a', '11 99191-9090');
INSERT INTO USERS_TBL (name, cpf, email, secret, phone) VALUES('Giovanni Enrico', '999.999.999-11', 'giovanni@partypay.com', '$2a$10$LOl4Ggr6c8D7Z3pC951kHeN/fyoBo24mt9hY1RG5w7eiXpTX1YL7a', '11 99191-8080');
INSERT INTO USERS_TBL (name, cpf, email, secret, phone) VALUES('Gabriel Bueno', '999.999.999-22', 'bueno@partypay.com', '$2a$10$LOl4Ggr6c8D7Z3pC951kHeN/fyoBo24mt9hY1RG5w7eiXpTX1YL7a', '11 99191-7070');
INSERT INTO USERS_TBL (name, cpf, email, secret, phone) VALUES('Giullio Emmanuel', '999.999.999-33', 'giullio@partypay.com', '$2a$10$LOl4Ggr6c8D7Z3pC951kHeN/fyoBo24mt9hY1RG5w7eiXpTX1YL7a', '11 99191-6060');
INSERT INTO USERS_TBL (name, cpf, email, secret, phone) VALUES('Rafael Barbosa', '999.999.999-44', 'rafael@partypay.com', '$2a$10$LOl4Ggr6c8D7Z3pC951kHeN/fyoBo24mt9hY1RG5w7eiXpTX1YL7a', '11 99191-5050');

