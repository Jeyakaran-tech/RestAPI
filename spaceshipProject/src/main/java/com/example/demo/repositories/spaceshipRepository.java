package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Spaceship;

@Repository
public interface spaceshipRepository extends MongoRepository<Spaceship,Long>
{

	

}



