import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
    private static EntityManager manager;

    public static EntityManager getEm() throws IOException{
        Properties props = new Properties();
        InputStream input = Prop.class.getClassLoader().getResourceAsStream("META-INF/persistence.xml");
        props.load(input);

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_persistence_unit", props);
            manager = factory.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manager;
    }
}
