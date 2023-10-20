import com.github.javafaker.Faker;
import dao.CatalogDAO;
import dao.LoanDAO;
import dao.UserDAO;
import entities.Book;
import entities.Magazine;
import entities.User;
import utils.JpaUtils;

import javax.persistence.EntityManager;
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
        Supplier<User> userSupplier = () -> new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday());

        Scanner input = new Scanner(System.in);
        try {
//            for (int i = 0; i < 5; i++) {
//                cDAO.save(bookSupplier.get());
//                cDAO.save(magazineSupplier.get());
//                uDAO.save(userSupplier.get());
//            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
            JpaUtils.close();
            input.close();
        }
    }

//    public static void creaDisco() {
//        File file = new File("src/listProducts.txt");
//    }
//
//    public static void salvaSuDisco(List<Catalog> catalogList) {
//        File file = new File("src/listProducts.txt");
//        for (Catalog catalog : catalogList) {
//            try {
//                FileUtils.writeStringToFile(file, catalog.getTitolo() + System.lineSeparator(), StandardCharsets.UTF_8, true);
//            } catch (IOException e) {
//                System.err.println(e.getMessage());
//            }
//        }
//    }
}
