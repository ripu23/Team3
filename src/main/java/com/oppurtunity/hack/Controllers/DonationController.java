package com.oppurtunity.hack.Controllers;


import java.util.List;

import com.oppurtunity.hack.entities.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.oppurtunity.hack.service.DonationService;

@RestController
@RequestMapping("/donation")
public class DonationController {

	@Autowired
	private DonationService donationService;

	@RequestMapping("/post")
	public @ResponseBody
	Donation post(@RequestBody Donation donation) {

		return donationService.save(donation);

	}
	
	@RequestMapping("/getAll")
	public List<Donation> getAllDonations() {
		return donationService.getAll();
	}
}
