create table orders (id bigint not null, orderNo varchar(255), totalAmount numeric(38,2), primary key (id));
alter table if exists orders drop constraint if exists UKqid14htug2oktfd0sft587nut;
alter table if exists orders add constraint UKqid14htug2oktfd0sft587nut unique (orderNo);
create sequence orders_SEQ start with 1 increment by 50;