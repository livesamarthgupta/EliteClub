package com.club.eliteclub.controller;

import com.club.eliteclub.model.Billionaire;
import com.club.eliteclub.model.Club;
import com.club.eliteclub.model.SearchCriteria;
import com.club.eliteclub.service.EliteClubService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EliteClubController {

    @Autowired
    private EliteClubService clubService;

    @GetMapping(path = "/clubs/page/{pageNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Club> clubs(@PathVariable("pageNumber") Integer pageNumber) {
        return clubService.getAll(pageNumber);
    }

    @GetMapping(path = "/clubs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Club> clubs() {
        return clubService.getAll();
    }

    @PostMapping(path = "/club", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addClub(@RequestBody Club club) {
        clubService.addClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping(path = "/club/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Club> searchClub(@Valid SearchCriteria searchCriteria) {
        return clubService.searchClub(searchCriteria.getSearchTerm(), searchCriteria.getRating());
    }

    @GetMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Club getClubWithId(@PathVariable Long id) {
        return clubService.getClubById(id);
    }

    @DeleteMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteClub(@PathVariable("id") Long clubIdToDelete) {
        clubService.deleteClub(clubIdToDelete);
    }

    @PutMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Club updateClub(@PathVariable Long id, @RequestBody Club updatedClub) {
        updatedClub.setId(id);
        return clubService.updateClub(updatedClub);
    }

    @GetMapping(path = "/club/billionaire/preview", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Billionaire> preview() {
        return clubService.fetchPreview();
    }

}
