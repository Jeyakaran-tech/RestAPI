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
	public locationRepository locationRepo;
	
	@Autowired
	public spaceshipRepository spaceRepo;
	
	
	
	
	@PostMapping (value = "/travel")
	public String travel(@RequestBody Travel travel)
	{
		List<Location> location = new ArrayList<>(locationRepo.findAll());
		List<Spaceship> space = new ArrayList<>(spaceRepo.findAll());
		
		for(int i=0;i<location.size();i++)
		{
			 Location l = location.get(i);
			 
			 for(int k=0;k<space.size();k++)
			 {
				 Spaceship m = space.get(k);
			 
				 if(l.getId() == travel.getDestID() && m.getId() == travel.getShipID())
				 {
				//	 System.out.println(m.getStatus());
					 if(l.getCapacity() > 0 && m.getStatus().equalsIgnoreCase("Operational"))
					 {
						 int cap = l.getCapacity();
						 cap-=1;
						 l.setCapacity(cap);
						 
						 for(int j=0;j<location.size();j++)
						 {
							 Location o = location.get(j);
							 
							 if(o.getId() == travel.getSourceID())
							 {
								 int cap1 = o.getCapacity();
								 cap1+=1;
								 o.setCapacity(cap1);
								 m.setLocation(l.getCityName()+" "+l.getPlanetName());
								 this.spaceRepo.save(m);
							 }
							 this.locationRepo.save(o);
						 }
						 


				 }

				 else
				 {
					 return "Check the capacity in the destination or the status of the ship is OPERATIONAL";
				 }

			 }

		}					


			 this.locationRepo.save(l);

		}
		return "travel success";
		
		

	}



}
