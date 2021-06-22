
import model.Track;
import org.json.JSONObject;

import javax.persistence.TypedQuery;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "Serv2", urlPatterns = {"/Serv2"})

public class Serv2 extends HttpServlet {
    private static final Logger log = Logger.getAnonymousLogger();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject;
        String username = request.getParameter("username");
        List<Track> tracks = new ArrayList<>();
        TypedQuery<Track> query = null;
        List<String> allTracks = new ArrayList<>();
        List<String> cached = new ArrayList<>();
        List<Track> notCached = new ArrayList<>();
        String trackName;

        log.info("request from br : "+request.getParameter("username"));
        if (request.getParameter("cached").equals("null")) {
            log.info("cache is null");
            try {
                query = Prop.getEm().createQuery("SELECT t FROM Track t where " +
                        "t.username = '"+username+"'", Track.class);
                tracks = query.getResultList();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Response.respondTracks(response,username,tracks);
        }
        else {
            jsonObject = new JSONObject(request.getParameter("cached"));
            for (int i = 0; i < jsonObject.length(); i++) {
                log.info("request: "+jsonObject.get(String.valueOf(i)));
                cached.add((String)jsonObject.get(String.valueOf(i)));
            }


            try {
                query = Prop.getEm().createQuery("SELECT t FROM Track t where " +
                        "t.username = '"+username+"'", Track.class);
                tracks = query.getResultList();
            } catch (IOException e) {
                e.printStackTrace();
            }


            for (Track track : tracks) {
                log.info("trackName : " + track.getTrackName());
                trackName = (track.getTrackName());
                allTracks.add(trackName);
            }

            allTracks.removeAll(cached);

            for (String track : allTracks) {
                Track t = new Track();
                t.setTrackName(track);
                notCached.add(t);
                log.info("not in cache:  " + track);
            }

            Response.respondTracks(response,username,notCached);
        }


    }
}

