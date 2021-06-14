package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

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

            Member member = new Member();
            member.setUsername("방시노");
            member.setCreatedBy("sino");
            member.setCreatedDate(LocalDateTime.now());
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();


            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(findMember));
            printMemberAndTeam(findMember);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
    }

    private static void printMemberAndTeam(Member findMember) {

        System.out.println(findMember.getClass());
        Hibernate.initialize(findMember);

        System.out.println(findMember.getClass());
        if(Member.class == findMember.getClass())
            System.out.println("==");
        if(Member.class.isInstance(findMember))
            System.out.println("isInstance");
        if(findMember instanceof Member)
            System.out.println("instanceof");


    }
}
