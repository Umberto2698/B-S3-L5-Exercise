package dao;

import entities.Magazine;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MagazineDAO {
    private final EntityManager em;

    public MagazineDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Magazine magazine) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(magazine);
            transaction.commit();
            System.err.println("Rivista salvata correttamente");
            System.out.println(magazine);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio della rivista ." + e);
            throw e;
        }
    }

    public Magazine getById(long id) {
        return em.find(Magazine.class, id);
    }

    public void delete(long id) {
        Magazine magazine = em.find(Magazine.class, id);
        if (magazine != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(magazine);
                transaction.commit();
                System.err.println("Rivista eliminata correttamente:");
                System.out.println(magazine);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Errore durante l'eliminazione della rivista." + e);
                throw e;
            }
        }
    }
}
