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

            String query = "" +
                    "select " +
                        "case when m.age <= 10 then '학생요금' " +
                        "     when m.age >= 60 then '경로요금' " +
                        "     else '일반요금' "  +
                        "end "  +
                    "from Member m ";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String arg : resultList) {
                System.out.println("arg = " + arg);
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
