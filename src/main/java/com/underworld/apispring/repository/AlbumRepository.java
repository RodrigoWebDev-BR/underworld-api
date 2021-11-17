package com.underworld.apispring.repository;

import com.underworld.apispring.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findById(long id);
}

