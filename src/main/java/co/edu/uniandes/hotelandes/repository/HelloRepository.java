package co.edu.uniandes.hotelandes.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.uniandes.hotelandes.model.Hello;

public interface HelloRepository extends MongoRepository<Hello, ObjectId> {

}