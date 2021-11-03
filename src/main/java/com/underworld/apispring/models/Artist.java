package com.underworld.apispring.models;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_ARTIST")
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "id")
    private Set<Album> albums;

    @OneToMany(mappedBy = "id")
    private Set<Song> songs;

    @NotNull
    private String artistName;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @NotNull
    private String artistPhoto;

    public String getArtistPhoto() {
        return artistPhoto;
    }

    public void setArtistPhoto(String artistPhoto) {
        this.artistPhoto = artistPhoto;
    }

    @ManyToMany(mappedBy = "following")
    private Set<User> followers;

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }
}
