package tobyspring.hellospring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Component;

@Component
public class DataTemplate {
    private final EntityManagerFactory emf;

    public DataTemplate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Object o) {
        // entityManager 생성
        EntityManager em = emf.createEntityManager();
        // transaction 시작
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // 생성된 entityManager.persist로 영속화
            em.persist(o);
            em.flush();
            // transaction commit
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                // entityManager 종료
                em.close();
            }
        }
    }
}
