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
    member.setFirstName("방");
    member.setLastName("시노");

    Member member2 = new Member();
    member2.setFirstName("위");
    member2.setLastName("하늘");

    em.persist(member);
    em.persist(member2);

    em.flush();
    em.clear();

    Member member1 = em.find(Member.class, 1l);
    System.out.println("member1 = " + member1);

    tx.commit();

    em.close();
    emf.close();
  }

}
