package entities;

import enums.MagazinePeriodicity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Random;

@Entity
public class Magazine extends Catalog {
    @Column(name = "periodicity")
    @Enumerated(EnumType.STRING)
    private MagazinePeriodicity periodicità;

    public Magazine() {
    }

    public Magazine(String titolo) {
        super(titolo);
        int n = new Random().nextInt(1, 3);
        switch (n) {
            case 1 -> this.periodicità = MagazinePeriodicity.SETTIMANALE;
            case 2 -> this.periodicità = MagazinePeriodicity.MENSILE;
            case 3 -> this.periodicità = MagazinePeriodicity.SEMESTRALE;
        }
    }

    public Magazine(String titolo, int annoDiPubblicazione, int numeroPagine, long codiceISBN, MagazinePeriodicity periodicità) {
        super(titolo, annoDiPubblicazione, numeroPagine, codiceISBN);
        this.periodicità = periodicità;
    }
}
