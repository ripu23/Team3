package com.oppurtunity.hack.service;
import com.oppurtunity.hack.entities.User;
import com.oppurtunity.hack.repositories.DonationRepository;

import java.util.ArrayList;
import java.util.List;

import com.oppurtunity.hack.entities.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

	@Autowired
	private DonationRepository donationRepository;

	public List<Donation> getAll() {
		List<Donation> list = new ArrayList<>();
		list = (List<Donation>) donationRepository.findAll();
		return list;
	}

	public Donation save (Donation donation) {
		return donationRepository.save(donation);
	}
	
	
	public void addAllMovie(List<Donation> donations) {
		donationRepository.saveAll(donations);
	}
}
