package com.underworld.apispring.controllers;

import com.underworld.apispring.models.Song;
import com.underworld.apispring.repository.SongRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API SPRING BOOT")
@CrossOrigin(origins = "*")
public class SongController {

    @Autowired
    SongRepository songRepository;

    @GetMapping("/songs")
    @ApiOperation(value = "Return ALL Songs")
    public List<Song> songListAll() {
        return songRepository.findAll();
    }

    @GetMapping("/song/{id}")
    @ApiOperation(value = "Return One Song")
    public Song songListById(@PathVariable(value="id") long id) {
        return songRepository.findById(id);
    }

    @PostMapping("/artist/{artistId}/album/{albumId}/song")
    @ApiOperation(value = "Insert Song")
    public Song newSong(@RequestBody Song newSong) {
        return songRepository.save(newSong);
    }

    @PutMapping("/artist/{artistId}/album/{albumId}/song/{id}")
    @ApiOperation(value = "Update Song")
    public Song updateSong(@RequestBody Song newSong, @PathVariable Long id) {
        return songRepository.findById(id)
                .map(song -> {
                    song.setArtistId(newSong.getArtistId());
                    song.setAlbumId(newSong.getAlbumId());
                    song.setTrackName(newSong.getTrackName());
                    song.setAudio(newSong.getAudio());
                    song.setDuration(newSong.getDuration());
                    return songRepository.save(song);
                })
                .orElseGet(() -> {
                    newSong.setId(id);
                    return songRepository.save(newSong);
                });
    }

    @DeleteMapping("/song/{id}")
    @ApiOperation(value = "DELETE Song")
    public void deleteSong(@PathVariable Long id) {
        songRepository.deleteById(id);
    }
}
