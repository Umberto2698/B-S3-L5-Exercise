import com.github.javafaker.Faker;
import dao.GenericDAO;
import entities.Generic;
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
        EntityManager em= JpaUtils.getEmf().createEntityManager();

        Faker faker =new Faker(Locale.ITALY);
        Supplier<Generic> genericSupplier=()->new Generic(faker.name().firstName());
        GenericDAO gDAO=new GenericDAO(em);

        Scanner input=new Scanner(System.in);
        try{
            for (int i = 0; i < 5; i++) {
                gDAO.save(genericSupplier.get());
            }

        }catch (Exception e){
            System.out.println(e);

        }finally {
            em.close();
            JpaUtils.close();
            input.close();
        }
    }

    public static void creaDisco(){
        File file = new File("src/listProducts.txt");
    }
    public static void salvaSuDisco(List<Generic> genericList) {
        File file = new File("src/listProducts.txt");
        for (Generic generic : genericList) {
            try {
                FileUtils.writeStringToFile(file, generic.getName() +System.lineSeparator(), StandardCharsets.UTF_8, true);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
