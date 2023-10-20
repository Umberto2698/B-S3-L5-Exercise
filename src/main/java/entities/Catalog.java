package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "findFromISBNCode", query = "SELECT c FROM Catalog c WHERE c.codiceISBN = :code")
@NamedQuery(name = "findFromPubblicationYear", query = "SELECT c FROM Catalog c WHERE c.annoDiPubblicazione = :year")
@NamedQuery(name = "findFromAuthor", query = "SELECT c FROM Catalog c WHERE c.autore IN (SELECT b.autore FROM Book b WHERE LOWER(b.autore) LIKE LOWER(:author))")
@NamedQuery(name = "findFromTitle", query = "SELECT c FROM Catalog c WHERE LOWER(c.titolo) LIKE LOWER(CONCAT(:title, '%'))")
@NamedQuery(name = "getLoanedElementsFromUser", query = "SELECT c FROM Catalog c WHERE c.codiceISBN IN (SELECT l.loanedItem FROM Loan l WHERE l.actualReturnDate = null AND l.user.membershipNumber = :membershipNumber)")
public abstract class Catalog {
    @Id
    @Column(name = "i_s_b_n_code")
    private long codiceISBN;
    @Column(name = "title")
    private String titolo;
    @Column(name = "pubblication_year")
    private int annoDiPubblicazione;
    @Column(name = "pages_number")
    private int numeroPagine;

    @OneToMany(mappedBy = "loanedItem", cascade = CascadeType.REMOVE)
    private List<Loan> loans;

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

    @Override
    public String toString() {
        return ", codiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine;
    }

    public long getCodiceISBN() {
        return codiceISBN;
    }

    public void setCodiceISBN(long codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }
}
