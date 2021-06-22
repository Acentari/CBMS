import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import model.Encrypt;
import model.Track;
import model.User;
import org.apache.commons.net.nntp.Article;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Serv", urlPatterns = {"/Serv"})

public class Serv extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String username =  request.getParameter("username");
        List<Track> tracks;

        try {
            TypedQuery<Track> query = Prop.getEm().createNamedQuery("Track.findAll",Track.class)
                    .setParameter("username",username);
            tracks = query.getResultList();
            Response.respondTracks(response, username, tracks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Track> findAllTracks(String username) throws IOException {
        TypedQuery<Track> query = Prop.getEm().createNamedQuery("Track.findAll",Track.class)
                .setParameter("username",username);
        return query.getResultList();
    }
}


