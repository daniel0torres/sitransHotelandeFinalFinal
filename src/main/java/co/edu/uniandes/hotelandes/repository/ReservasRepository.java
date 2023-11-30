package co.edu.uniandes.hotelandes.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.uniandes.hotelandes.model.Reservas;

public interface ReservasRepository extends MongoRepository<Reservas, ObjectId> {
    
}
