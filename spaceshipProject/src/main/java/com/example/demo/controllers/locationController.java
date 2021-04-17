package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Location;
import com.example.demo.model.Spaceship;
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

import java.util.ArrayList;
import java.util.List;



@RestController
public class locationController 
{
	
	@Autowired
	public locationRepository locationRepo;
	
	//retrieving all the location
	@GetMapping (value = "/allloc")
	public List<Location> getallLocation()
	{
		return locationRepo.findAll();
		
	}
	
	//creating the new location
	@PostMapping (value = "/createloc")
	public String createSpaceship(@RequestBody Location location)
	{
		int count = 0;
		
		List<Location> loc = new ArrayList<>(locationRepo.findAll()); //getting all the location into a list
		for(int i=0;i<loc.size();i++)
		{
			
			Location l = loc.get(i); //getting the pointer to current location
			
			if(l.getId() == location.getId()) //check whether the location exists already
			{
				return "Location exists already";
			}
		}
		
		Location insertedLocation = locationRepo.insert(location); //location was created and saved to mongoDB
		return "Location created "+insertedLocation.getId();
	}
	
	//deleting the location
	@RequestMapping (value = "/deleteloc/{Id}", method = RequestMethod.POST)
	public String deleteRecord(@PathVariable("Id") long Id) 
	{
		
		int count =0;
		List<Location> loc = new ArrayList<>(locationRepo.findAll()); //getting the list of all the spaceship in current repository

		for(int i=0;i<loc.size();i++)
		{
			
			Location s = loc.get(i); //getting the current position of the pointer in spaceship 
			
			if(s.getId() == Id) 
			{
				count++; //incrementing the pointer to check the ID is there or not
			}
		}
		
		if(count == 0)
		{
			return "Cannot delete :( since Location ID is not found";
		}
		
		 this.locationRepo.deleteById(Id);
	    return "Successfully deleted";
	}
	
	
	

}
