
import android.util.Log;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

@WebServlet(name = "Serv1", urlPatterns = {"/Serv1"})

public class Serv1 extends HttpServlet {
    private static final Logger log = Logger.getAnonymousLogger();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

        File file = new File("C:/Users/kostasD/Downloads/cb/"+
                request.getParameter("username"),  request.getParameter("trackname"));
        response.setHeader("Content-Type", getServletContext().getMimeType(request.getParameter("trackname")));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        try {
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

