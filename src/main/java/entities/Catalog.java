package entities;

import javax.persistence.*;
import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "findByPubblicationYear", query = "SELECT c FROM Catalog c WHERE c.annoDiPubblicazione = :year")
@NamedQuery(name = "findByAuthor", query = "SELECT b FROM Book b WHERE b.autore LIKE :author")
@NamedQuery(name = "findByTitle", query = "SELECT c FROM Catalog c WHERE LOWER(c.titolo) LIKE LOWER(CONCAT(:title, '%'))")
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

    @OneToOne(mappedBy = "loanedItem", cascade = CascadeType.REMOVE)
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
