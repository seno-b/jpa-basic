package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();

    Member member = new Member();
    member.setName("신규생성1");
    member.setAge(12);

    Member member2 = new Member();
    member2.setName("신규생성2");
    member2.setAge(12);

    em.persist(member);
    em.persist(member2);

    tx.commit();

    em.close();
    emf.close();
  }

}
