package tobyspring.hellospring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.order.Order;

public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        // entityManager 생성
        EntityManager em = emf.createEntityManager();

        // transaction 시작
        em.getTransaction().begin();

        // 생성된 entityManager.persist로 영속화
        Order order = new Order("100", BigDecimal.TEN);
        em.persist(order);

        System.out.println(order);

        // transaction commit
        em.getTransaction().commit();

        // entityManager 종료
        em.close();
    }
}
