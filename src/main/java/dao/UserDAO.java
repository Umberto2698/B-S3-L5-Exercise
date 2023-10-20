package dao;

import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {
    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.err.println("Utente salvato correttamente");
            System.out.println(user);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio dell'utente." + e);
            throw e;
        }
    }

    public User getById(long id) {
        return em.find(User.class, id);
    }

    public void delete(long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(user);
                transaction.commit();
                System.err.println("Utente eliminato correttamente");
                System.out.println(user);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Errore durante l'eliminazione dell'utente." + e);
                throw e;
            }
        }
    }
}
