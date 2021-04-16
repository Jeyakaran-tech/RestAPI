package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Spaceship")
public class Spaceship 
{
	Location loc = new Location();
	
	@Id
	private long id;
	private long location_ID;
	private String name;
	private String model;
	private String location;
	private String status;
	

	public Spaceship( long id,String name, String model, String status) 
	{
	
		this.id = id;
		this.location_ID = 1000;
		this.name = name;
	//	this.location = loc.getCityName()+" "+loc.getPlanetName();
		this.model = model;
		this.status = status;
	}

	public long getlocation_Id() {
		return location_ID;
	}




	public void setlocation_Id(long location_ID) {
		this.location_ID = location_ID;
	}



	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getModel() {
		return model;
	}




	public void setModel(String model) {
		this.model = model;
	}




	public String getLocation() {
	return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
	
	
	

}
