package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Location;
import com.example.demo.repositories.locationRepository;

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
public class locationController 
{
	
	@Autowired
	public locationRepository locationRepo;
	
	@GetMapping (value = "/allloc")
	public List<Location> getallLocation()
	{
		return locationRepo.findAll();
		
	}
	
	
	@PostMapping (value = "/createloc")
	public String createSpaceship(@RequestBody Location location)
	{
		
		Location insertedLocation = locationRepo.insert(location);
		return "Location created "+insertedLocation.getId();
	}
	
	@RequestMapping (value = "/deleteloc/{Id}", method = RequestMethod.POST)
	public String deleteRecord(@PathVariable("Id") long Id) 
	{
		
		 this.locationRepo.deleteById(Id);
	    return "Successfully deleted";
	}
	
	
	

}
