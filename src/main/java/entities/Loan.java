package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NamedQuery(name = "getExpiredLoans", query = "SELECT l FROM Loan l WHERE l.actualReturnDate = null AND l.expectedReturnDate < :today")
public class Loan {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_membership_number", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "loaned_item_id0", nullable = false)
    private Catalog loanedItem;
    @Column(name = "loan_start_date", nullable = false)
    private LocalDate loanStartDate;
    @Column(name = "expected_return_date", nullable = false)
    private LocalDate expectedReturnDate;
    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;

    public Loan() {
    }

    public Loan(LocalDate loanStartDate) {
        this.id = UUID.randomUUID().toString();
        this.loanStartDate = loanStartDate;
        this.expectedReturnDate = loanStartDate.plusDays(30);
        this.actualReturnDate = loanStartDate.plusDays(18);
    }

    public Loan(String id, User user, Catalog loanedItem, LocalDate loanStartDate, LocalDate actualReturnDate) {
        this.id = id;
        this.user = user;
        this.loanedItem = loanedItem;
        this.loanStartDate = loanStartDate;
        this.expectedReturnDate = loanStartDate.plusDays(30);
        this.actualReturnDate = actualReturnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", loanedItem=" + loanedItem +
                ", loanStartDate=" + loanStartDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Catalog getLoanedItem() {
        return loanedItem;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
}
