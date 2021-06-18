package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabasic");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Address address = new Address("city", "street", "zipCode");

            Member member1 = new Member();
            member1.setUsername("UserA");
            member1.setHomeAddress(address);
            em.persist(member1);

            member1.getFavoriteFoods().add("육회");
            member1.getFavoriteFoods().add("연어");
            member1.getFavoriteFoods().add("하이볼");
            member1.getFavoriteFoods().add("치킨");

            member1.getAddressHistory().add(new AddressEntity("old1", "street", "zipCode"));
            member1.getAddressHistory().add(new AddressEntity("cirt2", "street", "zipCode"));

            em.flush();
            em.clear();

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
