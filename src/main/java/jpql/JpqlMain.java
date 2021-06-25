package jpql;

import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jqpl");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Team team = new Team();
            team.setName("Team1");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("Team2");
            em.persist(team2);

            Team team3 = new Team();
            team3.setName("Team3");
            em.persist(team3);

            Member member = new Member();
            member.setUsername("Member1");
            member.setAge(15);
            member.setType(MemberType.USER);
            member.setTeam(team);

            Member member2 = new Member();
            member2.setUsername("Member2");
            member2.setAge(15);
            member2.setType(MemberType.USER);
            member2.setTeam(team);

            Member member3 = new Member();
            member3.setUsername("Member3");
            member3.setAge(15);
            member3.setType(MemberType.USER);
            member3.setTeam(team2);

            em.persist(member);
            em.persist(member2);
            em.persist(member3);


            em.flush();
            em.clear();
            List<Team> resultList = em.createQuery("select t from Team t join fetch t.members", Team.class)
                    .getResultList();


            for (Team team1 : resultList) {
                System.out.println("team = " + team1.getName() + ", members = " + team1.getMembers().toString());
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
