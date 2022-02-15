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

    em.persist(member);

    List<Member> select_m_from_member_m = em.createQuery("select m from Member m", Member.class)
        .getResultList();

    System.out.println("select_m_from_member_m = " + select_m_from_member_m);

    // flush 하지 않는다.
    Member member1 = em.find(Member.class, 2l);

    tx.commit();
    em.close();
    emf.close();
  }

}
