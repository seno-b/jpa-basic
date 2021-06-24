package jpql;

import jpql.DTO.MemberDTO;
import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;

import javax.persistence.*;
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


            List<Member> resultList = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();


            for (Member member1 : resultList) {

                System.out.println("member1 = " + member1);
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
