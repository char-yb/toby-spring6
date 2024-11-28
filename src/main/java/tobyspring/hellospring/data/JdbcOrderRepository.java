// JdbcOrderRepository.java
package tobyspring.hellospring.data;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderRepository;

public class JdbcOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    void initDB() {
        jdbcClient.sql("""
			drop table if exists orders;
			""").update();
        jdbcClient
                .sql(
                        """
            create table if not exists orders (
                id bigint not null,
                orderNo varchar(255),
                totalAmount numeric(38,2),
                primary key (id)
            );
            """)
                .update();

        jdbcClient
                .sql(
                        """
            alter table if exists orders
            add constraint if not exists UKqid14htug2oktfd0sft587nut unique (orderNo);
            """)
                .update();

        jdbcClient
                .sql(
                        """
            create sequence if not exists orders_SEQ
            start with 1 increment by 50;
            """)
                .update();
    }

    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select next value for orders_SEQ").query(Long.class).single();
        order.setId(id);

        jdbcClient
                .sql("insert into orders (id, orderNo, totalAmount) values (?, ?, ?)")
                .params(order.getId(), order.getOrderNo(), order.getTotalAmount())
                .update();
    }
}
