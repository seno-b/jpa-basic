package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {

  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

  public static void main(String[] args) {
    Member member = createMember(1l, "test1");

    member.setName("변경!");

    mergeMember(member);
  }

  private static void mergeMember(Member member) {
    EntityManager em2 = emf.createEntityManager();
    EntityTransaction tx2 = em2.getTransaction();

    tx2.begin();
    Member mergeMember = em2.merge(member);
//    member 를 다시 영속상태로 만들어주는 것이 안전하다.
//    member = em2.merge(member);
    tx2.commit();

    // 준영속 상태
    System.out.println("member = " + member);

    // 영속 상태
    System.out.println("mergeMember = " + mergeMember);

    System.out.println(em2.contains(member));
    System.out.println(em2.contains(mergeMember));

    em2.close();

  }

  static Member createMember(Long id, String name) {
    EntityManager em1 = emf.createEntityManager();
    EntityTransaction tx1 = em1.getTransaction();
    tx1.begin();

    Member member = new Member();
    member.setId(id);
    member.setName(name);

    em1.persist(member);
    tx1.commit();

    em1.close();

    return member;
  }

}
