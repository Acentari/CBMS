import model.Track;
import model.User;


import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Persist {
    private static EntityManager manager;

    public static void save(Object obj) throws IOException {
        manager = Prop.getEm();
        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
//        manager.close();
    }

    public static void getObject(String username, String password) throws Exception {
        manager = Prop.getEm();
        User user;
        TypedQuery<User> query = manager.createQuery("SELECT u FROM User u where u.username = '"+username+"' AND u.pass = '"+password+"'", User.class);
        user = query.getSingleResult();
//        return user;
    }

    public static List<Track> getTracks(String username) throws Exception {
        manager = Prop.getEm();
        List<Track> tracks;
        TypedQuery<Track> query = manager.createQuery("SELECT t FROM Track t where t.username = '"+username+"'", Track.class);
        tracks = query.getResultList();
        return tracks;
    }
}
