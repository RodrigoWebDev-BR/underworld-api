package com.underworld.apispring.repository;

import com.underworld.apispring.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findById(long id);
}

