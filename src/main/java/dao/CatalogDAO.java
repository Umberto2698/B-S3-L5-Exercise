package dao;

import entities.Catalog;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CatalogDAO {
    private final EntityManager em;

    public CatalogDAO(EntityManager em) {this.em=em;}
    public void save(Catalog catalog) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(catalog);
            transaction.commit();
            System.out.println("Elemento salvato correttamente: " + catalog);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio dell'Evento."+ e);
            throw e;
        }
    }

    public Catalog getById(long id) {
        return em.find(Catalog.class, id);
    }

    public void delete(long id ) {
        Catalog catalog = em.find(Catalog.class, id);
        if (catalog != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(catalog);
                transaction.commit();
                System.out.println("Elemento eliminato correttamente: " + catalog);
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
