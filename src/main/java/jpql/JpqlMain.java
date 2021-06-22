package jpql;

import jpql.DTO.MemberDTO;
import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jqpl");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Team team = new Team();
            team.setName("join Test Team");
            em.persist(team);

            Member member = new Member();
            member.setUsername("join Test");
            member.setAge(15);
            member.setType(MemberType.USER);
            member.setTeam(team);

            em.persist(member);

            List<Object[]> result = em.createQuery("select 'HELLO', m.type, TRUE from Member m where m.type = :memberType", Object[].class)
                    .setParameter("memberType", MemberType.USER)
                    .getResultList();

            for (Object[] o : result) {
                System.out.println("o = " + Arrays.toString(o));
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
