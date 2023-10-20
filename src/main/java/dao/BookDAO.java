package dao;

import entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.concurrent.TimeUnit;

public class BookDAO {
    private final EntityManager em;

    public BookDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Book book) throws InterruptedException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(book);
            transaction.commit();
            System.err.println("Libro salvato correttamente");
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(book);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio del libro." + e);
            throw e;
        }
    }

    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    public void delete(long id) throws InterruptedException {
        Book book = em.find(Book.class, id);
        if (book != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(book);
                transaction.commit();
                System.err.println("Libro eliminato correttamente");
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println(book);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Errore durante l'eliminazione del libro." + e);
                throw e;
            }
        }
    }
}
