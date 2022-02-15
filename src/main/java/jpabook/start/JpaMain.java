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

    Member member2 = new Member();
    member.setId(2l);
    member.setName("신규생성2");
    member.setAge(22);

    em.persist(member);
    em.persist(member2);

    // 엔티티 매니저는 트랜잭션을 커밋하기 직전까지 데이터베이스에 엔티티를 저장하지 않고 내부 쿼리 저장소에 INSERT QUERY를 차곡차곡 저장해둔다
    // 그리고 트랜잭션을 커밋할 때 모아둔 쿼리를 데이터베이스에 보내는데 이것을 트랜잭션을 지원하는 쓰기지연(transactional write-behind) 라고 한다.
    // 커밋하는 순간 insert query 보낸다.
    tx.commit();
    em.close();
    emf.close();
  }

}
