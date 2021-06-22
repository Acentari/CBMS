
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import model.Track;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class Response {
    public static void respondTracks(HttpServletResponse response, String username, List<Track> tracks) throws Exception {
        JSONObject json1 = new JSONObject();
        JSONArray covers = new JSONArray();

        JSONArray array = new JSONArray();
        try {
            for (Track track: tracks) {
                Mp3File song = new Mp3File("C:/Users/kostasD/Downloads/cb/"+username+"/"+ track.getTrackName());
                array.put(track.getTrackName());
                if (song.hasId3v2Tag()){
                    ID3v2 id3v2tag = song.getId3v2Tag();
                    if (id3v2tag.getAlbumImage() != null) {
                        byte[] imageData = id3v2tag.getAlbumImage();
                        covers.put(imageData);
                    } else {
                        covers.put("none");
                    }
                } else {
                    covers.put("none");
                }
            }
        } catch (Exception e) {
            json1.put("error", e.toString());
        }

        json1.put("tracks", array);
        json1.put("covers", covers);

        response.setContentType("application/json");
        response.getWriter().write(json1.toString());
    }



    public static void respondOK(HttpServletResponse response) throws IOException {
        JSONObject json1 = new JSONObject();
        json1.put("response","ok");
        response.setContentType("application/json");
        response.getWriter().write(json1.toString());
    }

    public static void responderr(HttpServletResponse response,  String ex) throws IOException {
        JSONObject json1 = new JSONObject();
        json1.put("response",ex);
        response.setContentType("application/json");
        response.getWriter().write(json1.toString());
    }
}
