package hellojpa;


import javax.persistence.*;

@Entity
public class Locker {

    @GeneratedValue
    @Id
    @Column(name = "LOCKER_ID")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;

    public Locker() {

    }

    public Locker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
