package com.underworld.apispring.models;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
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

    @NotNull
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany
    @JoinTable(
            name = "TB_FOLLOWING",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> following;

    public Set<Artist> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Artist> following) {
        this.following = following;
    }

    @ManyToMany
    @JoinTable(
            name = "TB_PLAYLIST",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> playlist;

    public Set<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Set<Song> playlist) {
        this.playlist = playlist;
    }
}

