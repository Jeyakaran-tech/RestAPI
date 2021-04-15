package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Spaceship")
public class Spaceship 
{
	
	
	@Id
	private long id;
	private String name;
	private String model;
	private String location;
	private String status;
	

	public Spaceship( long id,String name, String model, String status) 
	{
	
		this.id = id;
		this.name = name;
		this.location = "hangar somewhere";
		this.model = model;
		this.status = status;
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
