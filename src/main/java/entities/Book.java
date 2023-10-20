package entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends Catalog {
    @Column(name = "author")
    private String autore;
    @Column(name = "genre")
    private String genere;

    public Book() {
    }

    public Book(String titolo, String autore, String genere) {
        super(titolo);
        this.autore = autore;
        this.genere = genere;
    }

    public Book(String titolo, String autore, String genere, int annoDiPubblicazione, int numeroPagine, long codiceISBN) {
        super(titolo, annoDiPubblicazione, numeroPagine, codiceISBN);
        this.autore = autore;
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Book{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                super.toString() + "}";
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
