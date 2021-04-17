package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Location;
import com.example.demo.model.Spaceship;
import com.example.demo.model.Travel;
import com.example.demo.repositories.locationRepository;
import com.example.demo.repositories.spaceshipRepository;


@RestController
public class travelController 
{
	@Autowired
	public locationRepository locationRepo; //getting the object of Mongo Repository(Location)
	
	@Autowired
	public spaceshipRepository spaceRepo; //getting the object of Mongo Repository(Spaceship)
	
	
	
	//Travel functionality
	@PostMapping (value = "/travel")
	public String travel(@RequestBody Travel travel)
	{
		int count = 0;
		List<Location> location = new ArrayList<>(locationRepo.findAll()); //getting all the location into a list
		List<Spaceship> space = new ArrayList<>(spaceRepo.findAll()); //getting all the spacehsip into the list
		
		for(int i=0;i<space.size();i++)
		{
			Spaceship s = space.get(i);
			
			if(s.getlocation_Id() == travel.getDestID()) //checking if the ship is already in the destination location
			{
				return "The spaceship is already in the destined location. Change the destination";
			}
		}
		
		for(int i=0;i<location.size();i++)
		{
			
			Location l1 = location.get(i);
			if(l1.getId() == travel.getDestID())
			{
				count++; //counter to check the destination location is there or not
			}
			
			
		}
		
		if(count == 0)
		{
			return "No Destination location is found :(";
		}
		
		
		
		//main functionality
		for(int i=0;i<location.size();i++)
		{
			 Location l = location.get(i); //getting the current location
			 
			 for(int k=0;k<space.size();k++)
			 {
				 Spaceship m = space.get(k); //getting the current ship
			 
				 if(m.getId() == travel.getShipID() 
						 	&& travel.getSourceID() == m.getlocation_Id()) 	//condition to check whether the ship's source is correctly mentioned and shipID is correctly mentioned 
				 {
					 if(l.getCapacity() > 0 && m.getStatus().equalsIgnoreCase("Operational")) //checking whether the status is operational and capacity is greater than 0
					 {
						 int cap = l.getCapacity();
						 cap-=1;
						 l.setCapacity(cap); //decrementing the destination's capacity
						 
						 for(int j=0;j<location.size();j++)
						 {
							 Location o = location.get(j);
							 
							 if(o.getId() == travel.getSourceID())
							 {
								 int cap1 = o.getCapacity();
								 cap1+=1;
								 o.setCapacity(cap1);//incrementing the source location's capacity
								 m.setLocation(l.getCityName()+" "+l.getPlanetName()); //changing the name to destination location
								 m.setlocation_Id(travel.getDestID()); //changing the ID to destination location
								 this.spaceRepo.save(m); //saving the data to mongoDB
							 }
							 this.locationRepo.save(o);
						 }
						 


				 }

				 else
				 {
					 return "Check the capacity in the destination or the status of the ship is OPERATIONAL";
				 }

			 }
				 else
				 {
					 return "Travel is not possible";
				 }

		}					


			 this.locationRepo.save(l);

		}
		return "travel success";
		
		

	}






}
