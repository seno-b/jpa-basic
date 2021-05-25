package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HelloJpaMain {

    public static final Team TEAM = new Team();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabasic");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Team teamB = new Team();
            team.setName("TeamB");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("newTeamMember");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();
            Member findMember = em.find(Member.class, member.getId());

            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members){
                System.out.println(m.getUsername());
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
