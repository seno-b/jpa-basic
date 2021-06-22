package jpql;

import jpql.DTO.MemberDTO;
import jpql.domain.Member;
import jpql.domain.Team;

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

            Team team = new Team();
            team.setName("join Test Team");
            em.persist(team);

            Member member = new Member();
            member.setUsername("join Test");
            member.setAge(15);
            member.setTeam(team);

            Member member2 = new Member();
            member2.setUsername("join Test");
            member2.setAge(20);

            em.persist(member);
            em.persist(member2);


            List<Member> resultList = em.createQuery("select m from Member m where m.age < (select MAX(m1.age) from Member m1)", Member.class).getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
                System.out.println("team = " + member1.getTeam().toString());

            }

            member2.changeTeam(team);

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
