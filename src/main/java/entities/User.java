package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

@Entity
public class User {
    @Id
    @Column(name = "membership_number")
    private long membershipNumber;
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
        this.membershipNumber = (new Random().nextLong(1000000000000L, 10000000000000L));
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

    public long getMembershipNumber() {
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
