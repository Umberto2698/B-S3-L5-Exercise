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
}
