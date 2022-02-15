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
    em.flush();
    em.clear();

    // 영속 엔티티 조회
    Member findMember = em.find(Member.class, 1l);

    // 영속 엔티티 데이터 수정
    findMember.setAge(10);

    // dirty checking (변경감지)
    // jpa는 엔티티를 영속성 컨텍스트에 보관할 때, 최초 상태를 복사해서 저장해두는데 이것을 '스냅샷'이라 한다.
    // 그리고 플러시 시점에 스냅샷과 엔티티를 비교해서 변경된 엔티티를 찾는다.
    // 엔티티의 모든 필드를 업데이트하는 장단점.
    // 단점 : 전송량이 증가한다.
    // 장점 : 모든 필드를 사용하면 수정 쿼리가 항상 같다, 따라서 애플리케이션 로딩 시점에 수정 쿼리를 미리 생성해두고 재사용할 수 있다.
    //       데이터베이스에 동일한 쿼리를 보내면 데이터베이스는 이전에 한 번 파싱된 쿼리를 재사용할 수 있다.

    tx.commit();
    em.close();
    emf.close();
  }

}
