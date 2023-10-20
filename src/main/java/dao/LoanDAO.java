package dao;

import entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoanDAO {
    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Loan loan) throws InterruptedException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(loan);
            transaction.commit();
            System.err.println("Prestito salvato correttamente");
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(loan);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio del prestito." + e);
            throw e;
        }
    }

    public Loan getById(String id) {
        return em.find(Loan.class, id);
    }

    public List<Loan> getExpiredLoans() {
        TypedQuery<Loan> getElements = em.createNamedQuery("getExpiredLoans", Loan.class);
        LocalDate today = LocalDate.now();
        getElements.setParameter("today", today);
        return getElements.getResultList();
    }

    public void delete(long id) throws InterruptedException {
        Loan loan = em.find(Loan.class, id);
        if (loan != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(loan);
                transaction.commit();
                System.err.println("Prestito eliminato correttamente");
                TimeUnit.MILLISECONDS.sleep(1000);
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
