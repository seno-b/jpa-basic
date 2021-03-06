package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HelloJpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabasic");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Member> resultList = em.createQuery(
                    "select m from Member m where m.username like '%kim%'"
                    , Member.class
            ).getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member.getUsername());
            }

            em.createNativeQuery("select * from MEMBER", Member.class).getResultList();

            List<Member> resultList1 = em.createQuery("select m from Member m where m.username = 'kim'", Member.class).getResultList();

            for (Member member : resultList1) {
                System.out.println("member = " + member);
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
    }

}
