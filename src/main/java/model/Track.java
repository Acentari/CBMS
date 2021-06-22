package model;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@NamedQuery(name="Track.findAll", query="SELECT t FROM Track t where t.username = :username")
@Table(name = "tracks")
@Entity
public class Track implements Serializable {
    @Column(name = "username")
    private String username;
    @Id
    private String trackName;

    public Track(){}

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getUsername() {
        return username;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
