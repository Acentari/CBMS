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
import javax.persistence.Query;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@WebServlet(name = "LogInServlet", urlPatterns = {"/LogInServlet"})

public class LogInServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException {
        User u = null;

        try {
            u = Request.getUserFromJsonRequest(request);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            Response.responderr(response,e.toString());
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }

        try {
            assert u != null;
            Query q = Prop.getEm().createNamedQuery("User.getuser",User.class)
                    .setParameter("password",u.getPass()).setParameter("username",u.getUsername());
            int count = (int) q.getSingleResult();

            Response.responderr(response,q.getSingleResult().toString());
            if (count > 0) {
                response.setStatus(HttpServletResponse.SC_OK);
            }
            else {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }
        } catch (NullPointerException npe) {
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }
}