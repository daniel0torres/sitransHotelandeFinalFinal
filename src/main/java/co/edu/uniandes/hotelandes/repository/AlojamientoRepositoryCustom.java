package co.edu.uniandes.hotelandes.repository;

import java.util.List;

import org.bson.types.ObjectId;

import co.edu.uniandes.hotelandes.model.Alojamiento;


public interface AlojamientoRepositoryCustom {
    List<Alojamiento> findAlojamientoConReserva();
    Alojamiento findByReserva(ObjectId reservaId);
}
