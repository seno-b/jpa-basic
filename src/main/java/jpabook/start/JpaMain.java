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

    // update
    member.setAge(20);

    Member findMember = em.find(Member.class, 1l);
    System.out.println("findMember = " + findMember);

    TypedQuery<Member> select_m_from_member_m = em.createQuery("select m from Member m",
        Member.class);

    List<Member> resultList = select_m_from_member_m.getResultList();
    System.out.println("resultList = " + resultList);

    tx.commit();
    em.close();
    emf.close();
  }

}
