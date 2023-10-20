import com.github.javafaker.Faker;
import dao.CatalogDAO;
import dao.LoanDAO;
import dao.UserDAO;
import entities.Book;
import entities.Loan;
import entities.Magazine;
import entities.User;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        EntityManager em = JpaUtils.getEmf().createEntityManager();

        CatalogDAO cDAO = new CatalogDAO(em);
        UserDAO uDAO = new UserDAO(em);
        LoanDAO lDAO = new LoanDAO(em);

        Faker faker = new Faker(Locale.ITALY);
        Supplier<Book> bookSupplier = () -> new Book(faker.book().title(), faker.book().author(), faker.book().genre());
        Supplier<Magazine> magazineSupplier = () -> new Magazine(faker.book().title());
        Supplier<User> userSupplier = () -> new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Supplier<Loan> loanSupplier = () -> new Loan(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        Scanner input = new Scanner(System.in);
        try {
//            for (int i = 0; i < 5; i++) {
//                cDAO.save(bookSupplier.get());
//                cDAO.save(magazineSupplier.get());
//                uDAO.save(userSupplier.get());
//            }
//            User u1 = uDAO.getById(2856654481761L);
//            User u2 = uDAO.getById(5306475808095L);
//            User u3 = uDAO.getById(1810593753628L);
//            cDAO.getByPubblicationYear(1796).forEach(System.out::println);
//            Catalog c1 = cDAO.getByPubblicationYear(1796).get(0);
//           cDAO.getByAuthor("Orfeo Caruso").forEach(System.out::println);
//            Catalog c2 = cDAO.getByAuthor("Orfeo Caruso").get(0);

//            Loan loanFromSupplier1 = loanSupplier.get();
//            Loan l1 = new Loan(loanFromSupplier1.getId(), u1,c1, loanFromSupplier1.getLoanStartDate(), loanFromSupplier1.getActualReturnDate());
//            lDAO.save(l1);
//            Loan loanFromSupplier2 = loanSupplier.get();
//            Loan l2 = new Loan(loanFromSupplier2.getId(), u2, c2, loanFromSupplier2.getLoanStartDate(), loanFromSupplier2.getActualReturnDate());
//            lDAO.save(l2);
//
//            cDAO.getByTitle("T").forEach(System.out::println);
//            List<Catalog> catalogList = cDAO.getByTitle("T");
//            for (Catalog catalog : catalogList) {
//                Loan loanFromSupplier = loanSupplier.get();
//                Loan randomLoan = new Loan(loanFromSupplier.getId(), u3, catalog, LocalDate.now().minusDays(24), null);
//                lDAO.save(randomLoan);
//            }
            lDAO.getLoanedElementsByUser(1810593753628L).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
            JpaUtils.close();
            input.close();
        }
    }
}
