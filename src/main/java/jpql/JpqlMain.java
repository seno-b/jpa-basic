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

            Member m = new Member();
            m.setUsername("Member1dd");
            m.setAge(30);

            Member m2 = new Member();
            m2.setUsername("Member2");
            m2.setAge(30);

            em.persist(m);
            em.persist(m2);

            Member findMember = em.createQuery("select m from Member m where username = :username", Member.class)
                    .setParameter("username", "Member1dd")
                    .getSingleResult();

            System.out.println("findMember = " + findMember.getId());

            findMember.setAge(500);

            em.flush();
            em.clear();

            List<MemberDTO> resultList = em.createQuery("select new jpql.DTO.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            for (MemberDTO o : resultList) {
                System.out.println("o = " + o.getUsername());
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
