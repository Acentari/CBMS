import model.Encrypt;
import model.Track;
import model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Request {


   public static User getUserFromJsonRequest(HttpServletRequest request) throws IOException {
       User u = new User();
       BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
       String json = "";
       json = br.readLine();
       JSONObject jsonObject = new JSONObject(json);

       try {
           u.setUsername(jsonObject.getString("username"));
           Encrypt encrypt = new Encrypt(jsonObject.getString("password"));
           u.setPass(encrypt.getPass());
           u.setEmail(jsonObject.getString("email"));
           u.setFname(jsonObject.getString("fname"));
           u.setLname(jsonObject.getString("lname"));
       } catch (Exception e) {
//           u.setUsername(username);
           e.printStackTrace();
       }

       return u;
   }
}
