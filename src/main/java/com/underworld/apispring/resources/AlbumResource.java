package com.underworld.apispring.resources;

import com.underworld.apispring.models.Album;
import com.underworld.apispring.repository.AlbumRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API SPRING BOOT")
@CrossOrigin(origins = "*")
public class AlbumResource {

    @Autowired
    AlbumRepository albumRepository;

    @GetMapping("/albums")
    @ApiOperation(value = "Return ALL Albums")
    public List<Album> albumListAll() {
        return albumRepository.findAll();
    }

    @GetMapping("album/{id}")
    @ApiOperation(value = "Return One Album")
    public Album albumListById(@PathVariable(value="id") long id) {
        return albumRepository.findById(id);
    }

    @PostMapping("/album")
    @ApiOperation(value = "Insert Album")
    public Album newArtist(@RequestBody Album newAlbum) {
        return albumRepository.save(newAlbum);
    }

    @PutMapping("/album/{id}")
    @ApiOperation(value = "Update Album")
    public Album updateAlbum(@RequestBody Album newAlbum, @PathVariable Long id) {
        return albumRepository.findById(id)
                .map(album -> {
                    album.setTitle(newAlbum.getTitle());
                    album.setArtistId(newAlbum.getArtistId());
                    album.setAlbumCover(newAlbum.getAlbumCover());
                    album.setNumberOfTracks(newAlbum.getNumberOfTracks());
                    album.setDuration(newAlbum.getDuration());
                    album.setReleaseYear(newAlbum.getReleaseYear());
                    return albumRepository.save(album);
                })
                .orElseGet(() -> {
                    newAlbum.setId(id);
                    return albumRepository.save(newAlbum);
                });
    }

    @DeleteMapping("/album/{id}")
    @ApiOperation(value = "DELETE Album")
    public void deleteAlbum(@PathVariable Long id) {
        albumRepository.deleteById(id);
    }
}