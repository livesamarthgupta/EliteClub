package com.club.eliteclub.dao;

import com.club.eliteclub.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EliteClubRepository extends JpaRepository<Club, Long> {

    @Query("SELECT x FROM Club x WHERE lower(x.clubName) LIKE :searchTerm and x.rating >= :rating ORDER BY x.rating DESC")
    List<Club> findClubs(@Param("searchTerm") String searchTerm, @Param("rating") Integer rating);
}
