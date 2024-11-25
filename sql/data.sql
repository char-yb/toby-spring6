select next value for order_seq from system_range(1,1000);

insert into orders (orderNo,totalAmount,id) values (100,100.0,1);