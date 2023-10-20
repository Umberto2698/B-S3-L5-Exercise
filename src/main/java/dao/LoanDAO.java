package dao;

import entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LoanDAO {
    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Loan loan) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(loan);
            transaction.commit();
            System.err.println("Prestito salvato correttamente");
            System.out.println(loan);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio del prestito." + e);
            throw e;
        }
    }

    public Loan getById(long id) {
        return em.find(Loan.class, id);
    }

    public void delete(long id) {
        Loan loan = em.find(Loan.class, id);
        if (loan != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(loan);
                transaction.commit();
                System.err.println("Prestito eliminato correttamente");
                System.out.println(loan);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Errore durante l'eliminazione del prestotp." + e);
                throw e;
            }
        }
    }
}
