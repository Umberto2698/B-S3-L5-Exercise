package dao;

import entities.Catalog;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
            System.err.println("Errore durante il salvataggio dell'Evento." + e);
            throw e;
        }
    }

    public Catalog getById(long id) {
        System.out.println(em.find(Catalog.class, id));
        return em.find(Catalog.class, id);
    }

    public void delete(long id) throws InterruptedException {
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
