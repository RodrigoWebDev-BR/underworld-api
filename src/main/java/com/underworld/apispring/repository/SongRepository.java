package com.underworld.apispring.repository;

import com.underworld.apispring.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
    Song findById(long id);
}

