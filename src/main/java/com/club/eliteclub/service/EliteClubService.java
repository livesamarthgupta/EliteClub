package com.club.eliteclub.service;

import com.club.eliteclub.dao.EliteClubRepository;
import com.club.eliteclub.model.Billionaire;
import com.club.eliteclub.model.Club;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EliteClubService {

    private static final Logger LOG = LoggerFactory.getLogger(EliteClubService.class);
    @Autowired
    private EliteClubRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Club> getAll(int pageNumber) {
        Page<Club> pages = repository.findAll(PageRequest.of(pageNumber, 1));
        return pages.toList();
    }

    public List<Club> getAll() {
        return repository.findAll();
    }

    public void addClub(Club club) {
        repository.save(club);
    }

    public List<Club> searchClub(String searchTerm, Integer rating) {
        LOG.info("searching term {} with rating greater than {}", searchTerm, rating);
        return repository.findClubs(buildLikePattern(searchTerm), rating);
    }

    private String buildLikePattern(String searchTerm) {
        return searchTerm.toLowerCase() + "%";
    }

    public Club getClubById(Long id) {
        Optional<Club> optionalClub = repository.findById(id);
        return optionalClub.orElseThrow(() -> new RuntimeException("Club not found."));
    }


    public void deleteClub(Long clubIdToDelete) {
        repository.deleteById(clubIdToDelete);
    }


    public Club updateClub(Club updatedClub) {
        return repository.save(updatedClub);
    }

    public List<Billionaire> fetchPreview() {
        final ResponseEntity<Billionaire[]> billionaireResponseEntity = restTemplate.getForEntity("http://localhost:8080/BillionaireAppSpring/api/billionaires", Billionaire[].class);

        if(!billionaireResponseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Billionaire Service Down!");
        }

        return Arrays.stream(Objects.requireNonNull(billionaireResponseEntity.getBody())).collect(Collectors.toList());

    }
}
