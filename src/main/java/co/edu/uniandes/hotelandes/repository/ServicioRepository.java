package co.edu.uniandes.hotelandes.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.uniandes.hotelandes.model.Servicio;

public interface ServicioRepository extends MongoRepository<Servicio, ObjectId>{
    
    Servicio findBy_id(ObjectId id);
    
}
