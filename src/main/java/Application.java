import com.github.javafaker.Faker;
import dao.CatalogDAO;
import dao.LoanDAO;
import dao.UserDAO;
import entities.*;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Random;
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
            for (int i = 0; i < 5; i++) {
                cDAO.save(bookSupplier.get());
                cDAO.save(magazineSupplier.get());
                uDAO.save(userSupplier.get());
            }
            for (int i = 0; i < 10; i++) {
                long rdn = new Random().nextLong(1, 5);
                User randomUser = uDAO.getById(rdn);

                long rdn2 = new Random().nextLong(1, 10);
                Catalog randomElem = cDAO.getById(rdn2);

                Loan loanFromSupplier = loanSupplier.get();
                Loan randomLoan = new Loan(loanFromSupplier.getId(), randomUser, randomElem, loanFromSupplier.getLoanStartDate(), loanFromSupplier.getActualReturnDate());

                lDAO.save(randomLoan);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
            JpaUtils.close();
            input.close();
        }
    }
}
