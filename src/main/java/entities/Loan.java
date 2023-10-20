package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "loaned_item_id")
    private Catalog loanedItem;
    @Column(name = "loan_start_date")
    private LocalDate loanStartDate;
    @Column(name = "expected_return_date")
    private LocalDate expectedReturnDate;
    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;
}
