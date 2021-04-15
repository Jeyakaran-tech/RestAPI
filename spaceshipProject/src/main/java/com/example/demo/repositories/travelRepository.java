package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Location;
import com.example.demo.model.Travel;

@Repository
public interface travelRepository extends MongoRepository<Location,Long>
{


}

