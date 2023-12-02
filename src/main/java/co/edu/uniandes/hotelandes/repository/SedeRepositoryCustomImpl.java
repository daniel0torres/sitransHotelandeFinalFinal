package co.edu.uniandes.hotelandes.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.hotelandes.model.Habitacion;
import co.edu.uniandes.hotelandes.model.Sede;

@Repository
public class SedeRepositoryCustomImpl implements SedeRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public Sede insertHabitacion(String sedeId, Habitacion habitacion) {

        Query query = new Query(Criteria.where("_id").is(sedeId));
        Sede sede = mongoTemplate.findOne(query, Sede.class);
        habitacion.setIdCustom();
        if (sede != null) {
            List<Habitacion> habitaciones = sede.getHabitaciones();
            habitaciones.add(habitacion);
            sede.setHabitaciones(habitaciones);

            return mongoTemplate.save(sede);
        }
        return null;
    }

    @Override
    public Habitacion findHabitacion(Sede sede, String numero) {
        if (sede.getHabitaciones() != null) {
            for (Habitacion habitacion : sede.getHabitaciones()) {
                if (numero.equals(habitacion.getNumero())) {
                    return habitacion; 
                }
            }
        }
        return null; 
    }


    @Override
    public void deleteHabitacion(ObjectId sedeId, String numero) {
        Query query = new Query(Criteria.where("_id").is(sedeId));
        Sede sede = mongoTemplate.findOne(query, Sede.class);

        List<Habitacion> habitaciones = sede.getHabitaciones();
        if (habitaciones != null) {
            habitaciones.removeIf(habitacion -> numero.equals(habitacion.getNumero()));
        }

        mongoTemplate.save(sede);
    }

    @Override
    public String findHabitacionById(ObjectId habitacionId, List<Sede> todasLasSedes) {
        for (Sede sede : todasLasSedes) {
            for (Habitacion habitacion : sede.getHabitaciones()) {
                if (habitacion.getId().equals(habitacionId)) {
                    return habitacion.getNumero();
                }
            }
        }
        return null; 
    }



}
