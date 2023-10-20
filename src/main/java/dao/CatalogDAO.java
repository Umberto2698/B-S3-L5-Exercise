package dao;

import entities.Catalog;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogDAO {
    private final EntityManager em;

    public CatalogDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Catalog catalog) throws InterruptedException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(catalog);
            transaction.commit();
            System.err.println("Elemento salvato correttamente");
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(catalog);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio dell'elemento." + e);
            throw e;
        }
    }

    public List<Catalog> getFromISBNCode(long code) {
        TypedQuery<Catalog> getElements = em.createNamedQuery("findFromISBNCode", Catalog.class);
        getElements.setParameter("code", code);
        return getElements.getResultList();
    }

    public List<Catalog> getFromPubblicationYear(int year) {
        TypedQuery<Catalog> getElements = em.createNamedQuery("findFromPubblicationYear", Catalog.class);
        getElements.setParameter("year", year);
        return getElements.getResultList();
    }

    public List<Catalog> getFromAuthor(String author) {
        TypedQuery<Catalog> getElements = em.createNamedQuery("findFromAuthor", Catalog.class);
        getElements.setParameter("author", author);
        return getElements.getResultList();
    }

    public List<Catalog> getFromTitle(String title) {
        TypedQuery<Catalog> getElements = em.createNamedQuery("findFromTitle", Catalog.class);
        getElements.setParameter("title", title);
        return getElements.getResultList();
    }

    public List<Catalog> getLoanedElementsFomUser(long mebershipNumber) {
        TypedQuery<Catalog> getElements = em.createNamedQuery("getLoanedElementsFromUser", Catalog.class);
        getElements.setParameter("membershipNumber", mebershipNumber);
        return getElements.getResultList();
    }

    public void deletebyISBNCode(long id) throws InterruptedException {
        Catalog catalog = em.find(Catalog.class, id);
        if (catalog != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(catalog);
                transaction.commit();
                System.err.println("Elemento eliminato correttamente");
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println(catalog);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Errore durante l'eliminazione dell'elemento." + e);
                throw e;
            }
        }
    }
}
