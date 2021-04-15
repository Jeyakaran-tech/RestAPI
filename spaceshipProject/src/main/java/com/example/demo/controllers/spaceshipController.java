package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Spaceship;
import com.example.demo.repositories.spaceshipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;



@RestController
public class spaceshipController 
{
	
	@Autowired
	public spaceshipRepository spaceRepo;
	
	@GetMapping (value = "/all")
	public List<Spaceship> getallSpaceships()
	{
		return spaceRepo.findAll();
		
	}
	
	
	@PostMapping (value = "/create")
	public String createSpaceship(@RequestBody Spaceship spaceship)
	{
		
		Spaceship insertedship = spaceRepo.insert(spaceship);
		return "Spaceship created "+insertedship.getName();
	}
	
	@RequestMapping (value = "/delete/{Id}", method = RequestMethod.POST)
	public String deleteRecord(@PathVariable("Id") long Id) 
	{
		
		 this.spaceRepo.deleteById(Id);
	    return "Successfully deleted";
	}
	
	@RequestMapping (value = "/update/{id}", method = RequestMethod.POST)
	public String updateRecord(@RequestBody Spaceship spaceship) 
	{
		
		String status = spaceship.getStatus();
		spaceship.setStatus(status);
		this.spaceRepo.save(spaceship);
		
		return "Successfully updated";
	}



	

}
