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
    member.setName("어쩔티비");
    member.setAge(555);

    em.persist(member);
    em.flush();
    em.clear();

    Member findMember1 = em.find(Member.class, 1l);
    // 1차 캐시에서 바로 꺼내기 때문에 sql select query 한번만 실행
    Member findMember2 = em.find(Member.class, 1l);

    // 같은 트랜잭션 안에서 동일성 보장
    System.out.println(findMember1 == findMember2);

    tx.commit();
    em.close();
    emf.close();
  }

}
