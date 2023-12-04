package co.edu.uniandes.hotelandes.repository;

import co.edu.uniandes.hotelandes.model.Cliente;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClienteRepository extends MongoRepository<Cliente, ObjectId> {

    @Query(value = "{ 'consumos.habitacion' : ?0")
    List<Cliente> findByConsumoHabitacion(String hab_id);
    Optional<Cliente> findById(String id);
}
