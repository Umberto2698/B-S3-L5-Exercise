import com.github.javafaker.Faker;
import dao.CatalogDAO;
import entities.Book;
import entities.Catalog;
import entities.Magazine;
import entities.User;
import org.apache.commons.io.FileUtils;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        //RICORDATI DI CAMBIARE IL PATH DEL DISCO
        creaDisco();
        EntityManager em = JpaUtils.getEmf().createEntityManager();

        Faker faker = new Faker(Locale.ITALY);
        Supplier<Book> bookSupplier = () -> new Book(faker.book().title(), faker.book().author(), faker.book().genre());
        Supplier<Magazine> magazineSupplier = () -> new Magazine(faker.book().title());
        Supplier<User> userSupplier = () -> new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday());
        CatalogDAO cDAO = new CatalogDAO(em);

        Scanner input = new Scanner(System.in);
        try {
            for (int i = 0; i < 5; i++) {
                cDAO.save(bookSupplier.get());
                cDAO.save(magazineSupplier.get());
            }

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            em.close();
            JpaUtils.close();
            input.close();
        }
    }

    public static void creaDisco() {
        File file = new File("src/listProducts.txt");
    }

    public static void salvaSuDisco(List<Catalog> catalogList) {
        File file = new File("src/listProducts.txt");
        for (Catalog catalog : catalogList) {
            try {
                FileUtils.writeStringToFile(file, catalog.getTitolo() + System.lineSeparator(), StandardCharsets.UTF_8, true);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
