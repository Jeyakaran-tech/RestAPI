package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Location;
import com.example.demo.model.Spaceship;
import com.example.demo.repositories.locationRepository;
import com.example.demo.repositories.spaceshipRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;



@RestController
public class spaceshipController 
{
	
	@Autowired
	public spaceshipRepository spaceRepo; //getting the object of Mongo Repository(Spaceship)
	
	@Autowired
	public locationRepository locationRepo; //getting the object of Mongo Repository(Location)
	

	
	// List out all the spaceships
	@GetMapping (value = "/all")
	public List<Spaceship> getallSpaceships()
	{
		return spaceRepo.findAll();
		
	}
	
	
	// Create a spaceship 
	@PostMapping (value = "/create")
	public String createSpaceship(@RequestBody Spaceship spaceship)
	{
		int c=0;
		List<Location> location = new ArrayList<>(locationRepo.findAll()); //pushing all the location into the array list
		List<Spaceship> space = new ArrayList<>(spaceRepo.findAll()); //pushing all the spaceship into the array list
		
		//condition to check the spaceship is there already or not
		for(int i=0;i<space.size();i++)
		{
			
			Spaceship s = space.get(i);
			
			if(s.getId() == spaceship.getId())
			{
				return "Spaceship exists already";
			}
		}
		
		//creating the spaceship
		for(int i=0;i<location.size();i++)
		{
			//getting the current location
			 Location l = location.get(i);
			 
			 
			if(spaceship.getlocation_Id() == l.getId())
			{
				c++; //just incrementing to check the location is there to place the spaceship
				if(l.getCapacity() > 0)
				{
					spaceship.setLocation(l.getCityName()+" "+l.getPlanetName()); //extracting the names from Location entity
					int cap = l.getCapacity(); //getting the capacity of the location
					cap-=1;
					l.setCapacity(cap); //reducing the capacity since the new spaceship is stationed there
					
					locationRepo.save(l); //saving the object to Mongo DB
				
					Spaceship insertedship = spaceRepo.insert(spaceship); //inserting the object into mongoDB
					return "Spaceship created "+insertedship.getName();
					
				}
				else
				{
					return "No capacity to station the Spaceship";
				}
			}
			
			
		}
		
		if(c==location.size())
		{
			return "Location not found";
		}
		return "";
		
		
	}
	
	
	//Method to delete the particular spaceship by giving the ID
	@RequestMapping (value = "/delete/{Id}", method = RequestMethod.POST)
	public String deleteRecord(@PathVariable("Id") long Id) 
	{
		int count =0;
		List<Spaceship> space = new ArrayList<>(spaceRepo.findAll()); //getting the list of all the spaceship in current repository

		for(int i=0;i<space.size();i++)
		{
			
			Spaceship s = space.get(i); //getting the current position of the pointer in spaceship 
			
			if(s.getId() == Id) 
			{
				count++; //incrementing the pointer to check the ID is there or not
			}
		}
		
		if(count == 0)
		{
			return "Cannot delete :( since Spaceship ID is not found";
		}
		 this.spaceRepo.deleteById(Id);
	    return "Successfully deleted";
	}
	
	
	//function to update the status 
	@RequestMapping (value = "/update/{id}", method = RequestMethod.POST)
	public String updateRecord(@RequestBody Spaceship spaceship) 
	{
		
		int count = 0;
		List<Spaceship> space = new ArrayList<>(spaceRepo.findAll()); //list of all the spaceships

		for(int i=0;i<space.size();i++)
		{
			
			Spaceship s = space.get(i); //getting the current position of the pointer in spaceship 
			
			if(s.getId() == spaceship.getId())
			{
				count++; //incrementing the pointer to check the ID is there or not
			}
		}
		
		if(count == 0)
		{
			return "Cannot update :( since Spaceship ID is not found";
		}
		
		String status = spaceship.getStatus();
		spaceship.setStatus(status);
		this.spaceRepo.save(spaceship);
		
		return "Successfully updated";
	}



	

}
