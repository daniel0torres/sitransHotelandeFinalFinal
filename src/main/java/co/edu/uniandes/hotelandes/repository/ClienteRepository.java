package co.edu.uniandes.hotelandes.repository;
import co.edu.uniandes.hotelandes.model.Cliente;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ClienteRepository extends MongoRepository<Cliente, ObjectId> {


}