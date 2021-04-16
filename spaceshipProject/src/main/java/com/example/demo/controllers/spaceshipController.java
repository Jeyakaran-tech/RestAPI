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
	public spaceshipRepository spaceRepo;
	
	@Autowired
	public locationRepository locationRepo;
	

	
	@GetMapping (value = "/all")
	public List<Spaceship> getallSpaceships()
	{
		return spaceRepo.findAll();
		
	}
	
	
	@PostMapping (value = "/create")
	public String createSpaceship(@RequestBody Spaceship spaceship)
	{
		List<Location> location = new ArrayList<>(locationRepo.findAll());
		List<Spaceship> space = new ArrayList<>(spaceRepo.findAll());
		
		int count = 0;
		
		for(int i=0;i<space.size();i++)
		{
			
			Spaceship s = space.get(i);
			
			if(s.getId() == spaceship.getId())
			{
				return "Spaceship exists already";
			}
		}
		for(int i=0;i<location.size();i++)
		{
			 Location l = location.get(i);
			 
			if(spaceship.getlocation_Id() == l.getId())
			{
				spaceship.setLocation(l.getCityName()+" "+l.getPlanetName());
				
				Spaceship insertedship = spaceRepo.insert(spaceship);
				return "Spaceship created "+insertedship.getName();
			}
			
			else
			{
				return "Location was not found";
			}
		}
		return "";
		
		
	}
	
	@RequestMapping (value = "/delete/{Id}", method = RequestMethod.POST)
	public String deleteRecord(@PathVariable("Id") long Id) 
	{
		int count =0;
		List<Spaceship> space = new ArrayList<>(spaceRepo.findAll());

		for(int i=0;i<space.size();i++)
		{
			
			Spaceship s = space.get(i);
			
			if(s.getId() == Id)
			{
				count++;
			}
		}
		
		if(count == 0)
		{
			return "Cannot delete :( since Spaceship ID is not found";
		}
		 this.spaceRepo.deleteById(Id);
	    return "Successfully deleted";
	}
	
	@RequestMapping (value = "/update/{id}", method = RequestMethod.POST)
	public String updateRecord(@RequestBody Spaceship spaceship) 
	{
		
		int count = 0;
		List<Spaceship> space = new ArrayList<>(spaceRepo.findAll());

		for(int i=0;i<space.size();i++)
		{
			
			Spaceship s = space.get(i);
			
			if(s.getId() == spaceship.getId())
			{
				count++;
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
