
import model.User;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})

public class RegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request,response);
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        User u ;
        EntityManager manager;

        try {
            manager = Prop.getEm();
            u = Request.getUserFromJsonRequest(request);
            manager.getTransaction().begin();
            manager.persist(u);
            manager.getTransaction().commit();

            Response.respondOK(response);

            Path path = Paths.get("C:/Users/kostasD/Downloads/cb/"+u.getUsername());
            Files.createDirectory(path);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
