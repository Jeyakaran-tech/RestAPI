package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Location")
public class Location 
{
	
	@Id
	private long id;
	private String cityName;
	private String planetName;
	private int capacity;
	
	
	

	public Location( long id,String cityName, String planetName, int capacity) 
	{
		super();
		this.id = id;
		this.cityName = cityName;
		this.planetName = planetName;
		this.capacity = capacity;
	}


	public Location() {
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getPlanetName() {
		return planetName;
	}


	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	
	
	

}
