import com.mysql.jdbc.Constants;
import model.Track;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@MultipartConfig
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Track track = new Track();
        String extName;
        EntityManager manager;
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
//                    if (item.isFormField()) {
//                        track.setUsername(item.getFieldName());
//                    }
                    track.setUsername(item.getFieldName());

                    if(!item.isFormField()){
                        track.setTrackName(new File(item.getName()).getName());
                        extName = getFileExtension(track.getTrackName());
                        if (extName.equals("mp3")) {
                            item.write(new File("C:/Users/kostasD/Downloads/cb/"+ track.getUsername() +"/"+ track.getTrackName()));
                        }
                    }
                }

            } catch (Exception ex) {
            }
        }

        try {
            manager = Prop.getEm();
            manager.getTransaction().begin();
            manager.persist(track);
            manager.getTransaction().commit();
        } catch (Exception ex) {
        }
    }

    public static String getFileExtension(String fullName) {
        checkNotNull(fullName);
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
