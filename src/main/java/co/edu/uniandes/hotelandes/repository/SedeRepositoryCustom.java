package co.edu.uniandes.hotelandes.repository;

import java.util.List;

import org.bson.types.ObjectId;

import co.edu.uniandes.hotelandes.model.Habitacion;
import co.edu.uniandes.hotelandes.model.Sede;


public interface SedeRepositoryCustom {
    
    Sede insertHabitacion(String sedeId, Habitacion habitacion);
    void deleteHabitacion(ObjectId sedeId, String numero);
    Habitacion findHabitacion(Sede sede, String numero);
    String findHabitacionById(ObjectId habitacionId, List<Sede> sedes);
}
