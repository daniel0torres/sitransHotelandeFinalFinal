package co.edu.uniandes.hotelandes.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.uniandes.hotelandes.model.Sede;

public interface SedeRepository extends MongoRepository<Sede, ObjectId> {
    
}
