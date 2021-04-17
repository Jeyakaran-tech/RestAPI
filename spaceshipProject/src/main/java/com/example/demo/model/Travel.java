package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Travel")
public class Travel 
{
	//instance variables
	@Id
	private long travelID;
	private long sourceID;
	private long destID;
	private long shipID;
	
	

//constructor for the class
	public Travel(long travelID, long sourceID, long destID, long shipID) {
		super();
		this.travelID = travelID;
		this.sourceID = sourceID;
		this.destID = destID;
		this.shipID = shipID;
	}


	//getters and setter for the class
	public long getTravelID() {
		return travelID;
	}


	public void setTravelID(long travelID) {
		this.travelID = travelID;
	}


	public long getSourceID() {
		return sourceID;
	}


	public void setSourceID(long sourceID) {
		this.sourceID = sourceID;
	}


	public long getDestID() {
		return destID;
	}


	public void setDestID(long destID) {
		this.destID = destID;
	}


	public long getShipID() {
		return shipID;
	}


	public void setShipID(long shipID) {
		this.shipID = shipID;
	}
	
	
	
	
	

}
