package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "membership_number")
    private UUID membershipNumber;
    private String name;
    private String surname;
    private LocalDate birtday;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Loan> loans;

    public User() {
    }

    public User(String name, String surname, LocalDate birtday) {
        this.name = name;
        this.surname = surname;
        this.birtday = birtday;
        this.membershipNumber = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "User{" +
                "membershipNumber=" + membershipNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birtday=" + birtday +
                '}';
    }

    public long getId() {
        return id;
    }

    public UUID getMembershipNumber() {
        return membershipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirtday() {
        return birtday;
    }

    public Set<Loan> getLoans() {
        return loans;
    }
}
