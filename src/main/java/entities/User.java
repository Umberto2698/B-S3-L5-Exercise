package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_number")
    private long membershipNumber;
    private String name;
    private String surname;
    private Date birtday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Loan> loans;

    public User() {
    }

    public User(String name, String surname, Date birtday) {
        this.name = name;
        this.surname = surname;
        this.birtday = birtday;
    }
}
