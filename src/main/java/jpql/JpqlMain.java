package jpql;

import jpql.DTO.MemberDTO;
import jpql.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jqpl");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();


            for (int i = 1; i< 100 ; i++){

                Member m = new Member();
                m.setUsername("Member" + i);
                m.setAge(i);
                em.persist(m);

            }

            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(10)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
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
