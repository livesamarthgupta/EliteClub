package com.club.eliteclub;

import com.club.eliteclub.model.Club;
import com.club.eliteclub.service.EliteClubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class EliteClubApplication implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(EliteClubApplication.class);

	@Autowired
	private EliteClubService eliteClubService;

	public static void main(String[] args) {
		SpringApplication.run(EliteClubApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		eliteClubService.addClub(new Club("Billionaire", 5));
		eliteClubService.addClub(new Club("Environmentalist", 3));
		eliteClubService.addClub(new Club("Poker", 1));
	}
}
