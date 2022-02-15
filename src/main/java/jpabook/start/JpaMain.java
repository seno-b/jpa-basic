package jpabook.start;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpaMain {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();

    Member member = new Member();
    member.setId(1l);
    member.setName("신규생성1");
    member.setAge(12);

    // 영속 상태, 1차 캐시 저장, 쓰기지연 쿼리 생성
    em.persist(member);

    tx.commit();

    // 영속성 컨텍스트가 제거 된다. 더 이상 영속성이 유지되지 않는다.
    em.close();
    emf.close();
  }

}
