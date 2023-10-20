package entities;

import javax.persistence.*;
import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_s_b_n_code")
    private long codiceISBN;
    @Column(name = "title")
    private String titolo;
    @Column(name = "pubblication_year")
    private int annoDiPubblicazione;
    @Column(name = "pages_number")
    private int numeroPagine;

    @OneToOne(mappedBy = "loanedItem")
    private Loan loan;

    public Catalog() {
    }

    public Catalog(String titolo) {
        this.titolo = titolo;
        this.annoDiPubblicazione = new Random().nextInt(1500, 2023);
        this.numeroPagine = new Random().nextInt(50, 1000);
        this.codiceISBN = (new Random().nextLong(1000000000000L, 10000000000000L));
    }

    public Catalog(String titolo, int annoDiPubblicazione, int numeroPagine, long codiceISBN) {
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
        this.codiceISBN = codiceISBN;
    }

    public String getTitolo() {
        return this.titolo;
    }
}
