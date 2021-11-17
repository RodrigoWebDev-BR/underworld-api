package com.underworld.apispring.resources;

import com.underworld.apispring.models.Artist;
import com.underworld.apispring.repository.ArtistRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API SPRING BOOT")
@CrossOrigin(origins = "*")
public class ArtistResource {

    @Autowired
    ArtistRepository artistRepository;

    @GetMapping("/artists")
    @ApiOperation(value = "Return ALL Artists")
    public List<Artist> artistListAll() {
        return artistRepository.findAll();
    }

    @GetMapping("artist/{id}")
    @ApiOperation(value = "Get One Artist by ID")
    public Artist artistListById(@PathVariable(value="id") long id) {
        return artistRepository.findById(id);
    }

    @PostMapping("/artist")
    @ApiOperation(value = "Insert Artist")
    public Artist newArtist(@RequestBody Artist newArtist) {
        return artistRepository.save(newArtist);
    }

    @PutMapping("/artist/{id}")
    @ApiOperation(value = "Update Artist")
    public Artist updateArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
        return artistRepository.findById(id)
                .map(artist -> {
                    artist.setArtistName(newArtist.getArtistName());
                    artist.setArtistPhoto(newArtist.getArtistPhoto());
                    return artistRepository.save(artist);
                })
                .orElseGet(() -> {
                    newArtist.setId(id);
                    return artistRepository.save(newArtist);
                });
    }

    @DeleteMapping("/artist/{id}")
    @ApiOperation(value = "DELETE Artist")
    public void deleteArtist(@PathVariable Long id) {
        artistRepository.deleteById(id);
    }
}
