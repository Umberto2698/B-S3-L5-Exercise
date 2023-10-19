package dao;

import entities.Generic;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class GenericDAO {
    private final EntityManager em;

    public GenericDAO(EntityManager em) {this.em=em;}
    public void save(Generic p) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(p);
            transaction.commit();
            System.out.println("Event salvatao correttamente: " + p);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio dell'Evento."+ e);
            throw e;
        }
    }

    public Generic getById(long id) {
        return em.find(Generic.class, id);
    }

    public void delete(long id ) {
        Generic p = em.find(Generic.class, id);
        if (p != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(p);
                transaction.commit();
                System.out.println("Evento eliminato correttamente: " + p);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Errore durante l'eliminazione dell'Evento." + e);
                throw e;
            }
        }
    }
}
